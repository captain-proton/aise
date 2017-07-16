/**
 * Created by nils on 30.06.17.
 */

var Config = {
    color: d3.schemeCategory20c,
    nodeCount: graph.nodes.length,
    allLinkTypes: ["CIG", "CUG", "CAG"],
    allVectorBuildings: [
        {id: "binary-clustering", func: buildBinaryNodeVectors},
        {id: "counted-clustering", func: buildLinkTypeCountNodeVectors}
    ],
    selectedLinkTypes: [],
    buildNodeVectors: function () {
        var selectedVectorType = $('input[name="vector-type"]:checked').val();
        var building = Config.allVectorBuildings.filter(function (b) {
            return b.id === selectedVectorType;
        });
        return building[0].func(Config.selectedLinkTypes);
    }
};

var Node = {
    transform: function (d) {
        return "rotate(" + (d.x - 90) + ") translate(" + (d.y + 8) + ",0)" + (d.x < 180 ? "" : "rotate(180)");
    },
    textAnchor: function (d) {
        return d.x < 180 ? "start" : "end";
    }
};

var Link = {
    connect: function (d) {
        d.source = d[0];
        d.target = d[d.length - 1];
    }
};

function getSelectedLinkTypes() {
    return Config.allLinkTypes.filter(function (type) {
        return $("#" + type).prop("checked");
    });
}

function getSelectedVectorBuilding() {
    return Config.allVectorBuildings.filter(function (type) {
        return $("#" + type.id).prop("checked");
    });
}

function getClusterCount() {
    return +$('#nClusters').val();
}

function buildBinaryNodeVectors(types) {

    var vectors = [];
    graph.nodes.forEach(function (node, i) {
        var vector = [];
        graph.nodes.forEach(function (inherited, j) {
            var links = graph.links.filter(function (link) {
                return types.indexOf(link.type) >= 0
                    && link.source === node.id
                    && link.target === inherited.id;
            });
            if (!vector[j]) {
                vector[j] = links.length > 0 ? 1 : 0;
            }
        });
        vectors[i] = vector;
    });
    return vectors;
}

/**
 * Builds vectors of this graph nodes with respect to the number of links one class
 * has of one type. For each given node the outgoing links are count. The order of the count
 * in the resulting array is the same given as the order of types.
 *
 * @param types
 * @returns {Array} an array of the size of this graph.nodes.length. Each element is an array
 *      with types.length elements containing numeric values.
 */
function buildLinkTypeCountNodeVectors(types) {
    var vectors = [];
    var indices = [];

    types.forEach(function (type, j) {
        indices[type] = j;
    });

    graph.nodes.forEach(function (node, i) {
        var vector = [];
        types.forEach(function (type) {
            vector[indices[type]] = 0;
        });

        graph.links.forEach(function (link) {
            if (link.source === node.id && types.indexOf(link.type) >= 0) {
                vector[indices[link.type]] += 1;
            }
        });
        vectors[i] = vector;
    });
    return vectors;
}

function buildKmeansCluster(vectors) {
    return figue.kmeans(Config.clusterCount, vectors);
}

function buildHierarchy(cluster) {

    // root node
    var map = {
        name: "",
        children: []
    };

    /*
     each centroid represents one cluster -> add one child to
     the root node
     */
    cluster.centroids.forEach(function (centroid, i) {
        map.children.push({
            name: "cluster_" + i,
            children: []
        })
    });
    // assign each node to the appropriate cluster
    cluster.assignments.forEach(function (assignment, i) {
        var clazz = graph.nodes[i].id;
        map.children[assignment].children.push({
            key: clazz,
            name: clazz.substring(clazz.lastIndexOf(".") + 1)
        });
    });
    return d3.hierarchy(map);
}

function getLinkTypes(source, target) {
    var links = graph.links.filter(function (link) {
        return link.source === source.data.key
            && link.target === target.data.key;
    });
    return links.map(function (link) {
        return link.type
    });
}

function buildLinks(nodes, types) {
    var map = {};
    var links = [];

    // For each link, construct a link from the source to target node.
    nodes.forEach(function (node) {
        map[node.data.key] = node;
    });

    graph.links.forEach(function (link) {
        if (types.indexOf(link.type) >= 0) {
            var source = map[link.source];
            var target = map[link.target];
            var path = source.path(target);
            path.types = getLinkTypes(source, target);
            links.push(path);
        }
    });

    return links;
}

function linkClassed(datum, link, cluster, selectedClusters) {

    var isSource = link.source === datum;
    var isOfType = link.types.indexOf(cluster) >= 0;
    var isClusterSelected = selectedClusters.indexOf(cluster) >= 0;
    if (isSource && isOfType && isClusterSelected) {
        link.target.targetType = cluster;
        return link.source.isSource = true;
    }
}

function mouseovered(d) {
    var nodes = node.selectAll(".node");
    var links = link.selectAll(".link");

    nodes.each(function (n) {
        n.targetType = "";
        n.isSource = false;
    });

    links.classed("link--inheritance", function (l) {
        return linkClassed(d, l, "CIG", Config.selectedLinkTypes);
    }).classed("link--aggregation", function (l) {
        return linkClassed(d, l, "CAG", Config.selectedLinkTypes);
    }).classed("link--usage", function (l) {
        return linkClassed(d, l, "CUG", Config.selectedLinkTypes);
    })
    ;

    nodes.classed("node--source", function (n) {
        return n.isSource;
    });
    nodes.attr("class", function (d) {
        return d.targetType
            ? "node node-" + d.targetType
            : "node";
    });

    d3.select("#fullClassName").text(function () {
        return d.data.key;
    });
}

function mouseouted(d) {
    var links = link.selectAll(".link");
    var nodes = node.selectAll(".node");

    links.classed("link--inheritance", false)
        .classed("link--aggregation", false)
        .classed("link--usage", false);

    nodes.classed("node--source", false);

    Config.selectedLinkTypes.forEach(function (cluster) {
        nodes.classed("node-" + cluster, false);
    });

    d3.select("#fullClassName").text(function () {
        return "";
    });
}

function updateNodes() {

    var vectors = Config.buildNodeVectors();
    var kmeansCluster = buildKmeansCluster(vectors);

    if (!kmeansCluster)
        throw "Could not build kmeans cluster, please update your settings";

    hierarchy = buildHierarchy(kmeansCluster);

    var cluster = d3.cluster()
        .size([360, Config.innerRadius]);
    cluster(hierarchy);
    var leaves = hierarchy.leaves();

    var nodes = node.selectAll(".node")
        .data(leaves);

    // update existing nodes
    nodes.attr("transform", Node.transform)
        .attr("text-anchor", Node.textAnchor)
        .text(function (d) {
            return d.data.name;
        });

    // enter new nodes
    nodes.enter()
        .append("text")
        .attr("class", "node")
        .attr("dy", "0.4em")
        .attr("transform", Node.transform)
        .attr("text-anchor", Node.textAnchor)
        .text(function (d) {
            return d.data.name;
        })
        .on("mouseenter", mouseovered)
        .on("mouseout", mouseouted)
    ;

    /*
     remove missing, normally not necessary as not nodes are
     removed in this application
     */
    nodes.exit().remove();
}

function updateLinks() {

    var leaves = hierarchy.leaves();
    var data = buildLinks(leaves, Config.selectedLinkTypes);

    // remove all links
    link.selectAll(".link")
        .data([])
        .exit()
        .remove();

    // does not work! wrong links are displayed, but correct nodes are highlighted
    // var links = link.selectAll(".link")
    //     .data(data)
    //     .each(Link.connect)
    // ;

    // reenter all links that should be shown
    link.selectAll(".link")
        .data(data)
        .enter()
        .append("path")
        .each(Link.connect)
        .attr("class", "link")
        .attr("d", line);

    // normally this would be done after a successful update
    // links.exit().remove();
}

function showError(msg) {
    d3.select("svg")
        .append("rect")
        .attr("id", "clusterOverlay")
        .attr("width", 800)
        .attr("height", 600);

    d3.select("svg")
        .append("text")
        .attr("id", "clusterOverlayText")
        .attr("x", 400)
        .attr("y", 300)
        .text(msg);
}

function removeErrorMsg() {
    $("#clusterOverlay").detach();
    $("#clusterOverlayText").detach();
}

Array.prototype.equals = function (other) {
    return (this.length == other.length) && this.every(function (element, index) {
            return element === other[index];
        });
};

$("#cluster").click(function () {
    var clusterCount = getClusterCount();
    var selectedLinkTypes = getSelectedLinkTypes();
    var selectedVectorBuilding = getSelectedVectorBuilding();

    if (selectedLinkTypes.length === 0) {
        showError("Without selected link types clusters can not be build.");
        return;
    } else {
        removeErrorMsg();
    }

    if (Config.clusterCount != clusterCount
        || !Config.selectedLinkTypes.equals(selectedLinkTypes)
        || !Config.selectedVectorBuilding.equals(selectedVectorBuilding)) {

        Config.clusterCount = clusterCount;
        Config.selectedLinkTypes = selectedLinkTypes;
        Config.selectedVectorBuilding = selectedVectorBuilding;

        try {

            updateNodes();
            updateLinks();
        } catch (err) {
            showError(err);
        }
    }
});

var svg = d3.select("svg");
Config.width = svg.attr("width");
Config.height = svg.attr("height");
Config.diameter = Math.min(Config.width, Config.height);
Config.innerRadius = Config.diameter / 2 - 120;
Config.clusterCount = getClusterCount();
Config.selectedLinkTypes = getSelectedLinkTypes();
Config.selectedVectorBuilding = getSelectedVectorBuilding();

var line = d3.radialLine()
    .curve(d3.curveBundle.beta(0.85))
    .radius(function (d) {
        return d.y;
    })
    .angle(function (d) {
        return d.x / 180 * Math.PI;
    });

svg.append("text")
    .attr("id", "fullClassName")
    .attr("class", "label")
    .attr("transform", "translate(10, 20)");

svg = svg.append("g")
    .attr("transform", "translate(" + Config.width / 2 + "," + Config.height / 2 + ")");

var link = svg.append("g");
var node = svg.append("g");
var hierarchy = undefined;

updateNodes();
updateLinks();