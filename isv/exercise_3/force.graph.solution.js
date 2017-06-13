var graphSettings = {

    // standard settings
    minCircleRadius: 3,
    maxCircleRadius: 8,
    getRadius: function(start, end, x) {

        // factor to get the share how much percent the link count takes from minLinks to maxLinks
        var factor = 100 / (end - start);

        // 0 <= share <= 1: how much percentage does the link count take from minLinks to maxLinks
        var share = (factor * (x - start)) / 100;

        /*
         max radius minus min radius contains the space between.
         multiplied with the share is equal to radius needed if min radius was zero.
         add min radius to get the final radius of the circle
         */
        var radius = (this.maxCircleRadius - this.minCircleRadius) * share + this.minCircleRadius;
        return radius;
    },

    // settings are used inside the improved layout
    edgeWeightMinimum: 0.5,
    minKeywordCount: 3
};


function splitKeywords(meta) {
    return $.map(meta.keywords.split(","), $.trim);
}

function createNodes() {
    var nodes = [];
    $.each(bib, function(title, meta) {
        $.each(meta.keywordsArr, function(i, keyword) {
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
        var count = 0;
        for (var i = 0; i < args.length; i++) {
            // increase count if this entry keywords contain the argument
            count += meta.keywordsArr.indexOf(args[i]) >= 0 ? 1 : 0;
        }
        /*
        if the count is equal to the arguments, this entry contains all keywords
        given in arguments
         */
        if (count == args.length) {
            publications.push({
                title: title,
                meta: meta
            });
        }
    });
    return publications;
}

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

function addKeywordsArray() {
    $.each(bib, function(title, meta) {
        var keywords = splitKeywords(meta);
        bib[title].keywordsArr = keywords;
    })
}

function getLinks(node) {
    var links = graph.links.filter(function (link) {
        /*
        the first two comparisons are valid before the graph is rendered.
        d3 updates the links that the id has to be compared after graph rendering
         */
        return link.source == node
            || link.target == node
            || link.source.id == node
            || link.target.id == node;
    });
    return links;
}

function showSelectedNodeInfo(keyword, publicationCount) {

    $('#node_info').detach();

    var html =
        '<section id="node_info">' +
            '<h4 class="keyword">' + keyword + '</h4>' +
            '<h4 class="publication_count">' + publicationCount + '</h4>' +
        '</section>'
    ;
    $('#selection_details').append(html);
}

function createPublicationHtml(publication) {
    var html =
        '<section class="publication">' +
            '<dl>'+
                '<dt>Title</dt>' +
                '<dd>' + publication.meta.title + '</dd>' +
                '<dt>Author</dt>' +
                '<dd>' + publication.meta.author + '</dd>' +
                '<dt>Year</dt>' +
                '<dd>' + publication.meta.year + '</dd>' +
            '</dl>' +
        '</section>'
    ;
    return html;
}

function showPublications(publications) {

    $('#publications').detach();

    var html = '<ul id="publications">';
    publications.forEach(function (publication) {
        html = html + '<li>' + createPublicationHtml(publication) + '</li>';
    });
    html = html + "</ul>";
    $('#selection_details').append(html);
}

function getConnectedNodes(node) {
    var links = getLinks(node);
    return links.map(function(link) {
        return link.source === node ? link.target : link.source;
    });
}

function handleNodeClick(d) {
    var publications = [];
    $.each(bib, function(title, meta) {
        if (meta.keywordsArr.indexOf(d.id) >= 0) {
            var publication = {
                title: title,
                meta: meta
            };
            publications.push(publication);
        }
    });

    // highlight selected node
    if (graphSettings.selectedNode !== undefined) {
        graphSettings.selectedNode.attr('class', 'nodes');
    }
    graphSettings.selectedNode = d3.select(this);
    graphSettings.selectedNode.attr('class', 'nodes selected');

    var keyword = graphSettings.selectedNode.select('title').text();
    showSelectedNodeInfo(keyword, publications.length);
    showPublications(publications);
}

function calcEdgeWeight(nodeA, nodeB) {
    var targetsA = getConnectedNodes(nodeA);
    var targetsB = getConnectedNodes(nodeB);

    var intersection = targetsA.filter(function (target) {
        return targetsB.indexOf(target) >= 0;
    });

    var nominator = intersection.length;
    var denominator = Math.min(targetsA.length, targetsB.length);

    return nominator / denominator;
}

function calcNodeRadius(d) {
    return graphSettings.getRadius(nodeRadiusIntervalStart, nodeRadiusIntervalEnd, keywords[d.id]);
}

function restart() {

    // Apply the general update pattern to the nodes.
    node = node.data(nodes, function (d) {
        return d.id;
    });
    node.exit().remove();
    node = node.enter()
        .append("circle")
        .attr("r", calcNodeRadius)
        .call(d3.drag()
            .on("start", dragstarted)
            .on("drag", dragged)
            .on("end", dragended))
        .on('click', handleNodeClick)
        .merge(node);

    node.append("title")
        .text(function (d) {
            return d.id;
        });

    // Apply the general update pattern to the links.
    link = link.data(links, function (d) {
        return d.source.id + "-" + d.target.id;
    });
    link.exit().remove();
    link = link.enter()
        .append("line")
        .merge(link);

    // Update and restart the simulation.
    simulation.nodes(nodes);
    simulation.force("link").links(links);
    simulation.alpha(1).restart();
}

function showImprovedLayout() {

    unimportantNodes.forEach(function(node) {
        nodes.splice(nodes.indexOf(node), 1);
    });
    unimportantLinks.forEach(function(link) {
        links.splice(links.indexOf(link), 1);
    });
    restart();
}

function showNormalLayout() {

    unimportantNodes.forEach(function(node) {
        nodes.push(node);
    });
    unimportantLinks.forEach(function(link) {
        links.push(link);
    });
    restart();
}

addKeywordsArray();
var nodes = createNodes();
var links = createLinks($.map(nodes, function(node) {
    return node.id;
}));

/* Graph */
var graph = {
    nodes: nodes,
    links: links
};

var unimportantNodes = nodes.filter(function(node) {
    var count = 0;
    $.each(bib, function(title, meta) {
        count += meta.keywordsArr.indexOf(node.id) >= 0 ? 1 : 0;
    });
    return count < graphSettings.minKeywordCount;
});
var unimportantLinks = links.filter(function(link) {
    var weight = calcEdgeWeight(link.source, link.target);
    var unimportantNode = unimportantNodes.filter(function(node) {
        return node.id === link.source
            || node.id === link.target;
    });
    return weight < graphSettings.edgeWeightMinimum
        || unimportantNode.length > 0;
});

var keywords = {};
$.each(bib, function(title, meta) {
    meta.keywordsArr.forEach(function(keyword) {
        if (!keywords.hasOwnProperty(keyword)) {
            keywords[keyword] = 0;
        }
        keywords[keyword] += 1;
    });
});

var nodeRadiusIntervalStart = Infinity;
$.each(keywords, function (keyword, count) {
    nodeRadiusIntervalStart = count < nodeRadiusIntervalStart
        ? count
        : nodeRadiusIntervalStart;
});
$('#minKeywordCount').text(nodeRadiusIntervalStart);
var nodeRadiusIntervalEnd = 0;
$.each(keywords, function (keyword, count) {
    nodeRadiusIntervalEnd = count > nodeRadiusIntervalEnd
        ? count
        : nodeRadiusIntervalEnd;
});
$('#maxKeywordCount').text(nodeRadiusIntervalEnd);

$('#toggle-advanced-layout')
    .on('click', function() {
        var checkbox = $(this)[0];
        if (checkbox.checked) {
            showImprovedLayout();
        } else {
            showNormalLayout();
        }
});

/* Force-directed graph layout based on D3js example (see sources)*/
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
    .enter()
    .append("line")
    .attr('id', function(d) {
        return d.source.index + '-' + d.target.index;
    });

var node = svg.append("g")
    .attr("class", "nodes")
    .selectAll("circle")
    .data(graph.nodes)
    .enter()
    .append("circle")
    .attr("r", calcNodeRadius)
    .call(d3.drag()
        .on("start", dragstarted)
        .on("drag", dragged)
        .on("end", dragended))
    .on('click', handleNodeClick);

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
