function splitKeywords(meta) {
    return $.map(meta.keywords.split(","), $.trim);
}

// function keywordsEqual(metaA, metaB) {
//     var keywordsA = splitKeywords(metaA);
//     var keywordsB = splitKeywords(metaB);
//
//     if (keywordsA.length != keywordsB.length) return false;
//
//     $.each(keywordsA, function (i) {
//         var idx = keywordsB.indexOf(keywordsA[i]);
//         if (idx !== -1) {
//             keywordsB.splice(idx, 1);
//         }
//     })
//     return keywordsB.length === 0;
// }

function createNodes() {
    var nodes = [];
    $.each(bib, function(title, meta) {
        var keywords = splitKeywords(meta);
        $.each(keywords, function(i, keyword) {
            if (nodes.indexOf(keyword) < 0) {
                nodes.push(keyword);
            }
        });
    });

    return $.map(nodes, function (node) {
        return {id: node};
    });
}

function filterPublications() {
    var publications = [];
    var args = arguments;
    $.each(bib, function(title, meta) {
        var keywords = splitKeywords(meta);
        var count = 0;
        for (var i = 0; i < args.length; i++) {
            count += keywords.indexOf(args[i]) >= 0 ? 1 : 0;
        }
        if (count == args.length) {
            publications.push({
                title: title,
                meta: meta
            });
        }
    });
    return publications;
}

// function createLinks(nodes) {
//     var links = [];
//     for (var i = 0; i < nodes.length; i++) {
//
//         var nodeA = nodes[i];
//         var otherNodes = $.merge([], nodes);
//         otherNodes.splice(nodes.indexOf(nodeA), 1);
//         for (var j = 0; j < otherNodes.length; j++) {
//             var nodeB = otherNodes[j];
//
//             // filter bibliography for publications that contain both keywords
//             var publications = filterPublications(nodeA, nodeB);
//
//             // if more than one publication is found the keywords may be connected
//             if (publications.length > 1) {
//
//                 for (var k = 0; k < publications.length; k++) {
//
//                     var publicationA = publications[k];
//                     var otherPublications = $.merge([], publications);
//                     otherPublications.splice(publications.indexOf(publicationA), 1);
//
//                     for (var l = 0; l < otherPublications.length; l++) {
//
//                         var otherPublication = otherPublications[l];
//                         if (keywordsEqual(publicationA.meta, otherPublication.meta)) {
//
//                             var link = {
//                                 source: nodeA,
//                                 target: nodeB
//                             }
//                             if (links.indexOf(link) === -1) {
//                                 links.push(link);
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//     }
//     return links;
// }


function containsLink(link, links) {
    return links.find(function (_link) {
        return (_link.source === link.source && _link.target === link.target)
            || (_link.target === link.source && _link.source === link.target);
    })
}

function createLinks(nodes) {
    var links = [];
    for (var i = 0; i < nodes.length; i++) {

        var nodeA = nodes[i];
        var otherNodes = $.merge([], nodes);
        otherNodes.splice(nodes.indexOf(nodeA), 1);
        for (var j = 0; j < otherNodes.length; j++) {
            var nodeB = otherNodes[j];

            // filter bibliography for publications that contain both keywords
            var publications = filterPublications(nodeA, nodeB);

            // if more than one publication is found the keywords are connected
            if (publications.length > 0) {

                var link = {
                    source: nodeA,
                    target: nodeB
                };
                if (!containsLink(link, links)) {
                    links.push(link);
                }
            }
        }
    }
    return links;
}

var nodes = createNodes();
var links = createLinks($.map(nodes, function(node) {
    return node.id;
}));

/* Graph */
// var graph = {nodes: [{id: "a"}, {id: "b"}], links: [{source: "a", target: "b"}]};
var graph = {
    nodes: nodes,
    links: links
};

/* Force-directed graph layout based on D3js example (see sources)*/
var linkColor = d3.scaleLinear()
    .domain([0, 1])
    .range(["#eee", "#000"]);

var svg = d3.select("svg"),
    width = +svg.attr("width"),
    height = +svg.attr("height");

var simulation = d3.forceSimulation()
    .force("link", d3.forceLink().id(function (d) {
        return d.id;
    }))
    .force("charge", d3.forceManyBody())
    .force("center", d3.forceCenter(width / 2, height / 2));

var link = svg.append("g")
    .attr("class", "links")
    .selectAll("line")
    .data(graph.links)
    .enter().append("line")
    .attr("stroke", "#aaa");

var node = svg.append("g")
    .attr("class", "nodes")
    .selectAll("circle")
    .data(graph.nodes)
    .enter().append("circle")
    .attr("r", 5)
    .call(d3.drag()
        .on("start", dragstarted)
        .on("drag", dragged)
        .on("end", dragended));

node.append("title")
    .text(function (d) {
        return d.id;
    });

simulation
    .nodes(graph.nodes)
    .on("tick", ticked);

simulation.force("link")
    .links(graph.links);


function ticked() {
    link
        .attr("x1", function (d) {
            return d.source.x;
        })
        .attr("y1", function (d) {
            return d.source.y;
        })
        .attr("x2", function (d) {
            return d.target.x;
        })
        .attr("y2", function (d) {
            return d.target.y;
        });

    node
        .attr("cx", function (d) {
            return d.x;
        })
        .attr("cy", function (d) {
            return d.y;
        });
}

function dragstarted(d) {
    if (!d3.event.active) simulation.alphaTarget(0.3).restart();
    d.fx = d.x;
    d.fy = d.y;
}

function dragged(d) {
    d.fx = d3.event.x;
    d.fy = d3.event.y;
}

function dragended(d) {
    if (!d3.event.active) simulation.alphaTarget(0);
    d.fx = null;
    d.fy = null;
}
