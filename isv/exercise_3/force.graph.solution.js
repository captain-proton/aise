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
            count += meta.keywordsArr.indexOf(args[i]) >= 0 ? 1 : 0;
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

function calculateLinkCount(node, graph) {
    var links = graph.links.filter(function (link) {
        return link.source == node || link.target == node;
    });
    return links.length;
}

function showSelectedNodeInfo(keyword, publicationCount) {

    $('#node_info').detach();

    var html =
        '<section id="node_info">' +
            '<span class="keyword">' + keyword + '</span>' +
            '<span class="publication_count">' + publicationCount + '</span>' +
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

function handleNodeClick(d, i) {
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

    if (selectedNode !== undefined) {
        selectedNode.attr('class', 'nodes');
    }
    selectedNode = d3.select(this);
    selectedNode.attr('class', 'nodes selected');

    showSelectedNodeInfo(selectedNode.select('title').text(), publications.length);
    showPublications(publications);
}

addKeywordsArray();
var nodes = createNodes();
var links = createLinks($.map(nodes, function(node) {
    return node.id;
}));

var graphSettings = {
    minCircleRadius: 3,
    maxCircleRadius: 8,
    getRadius: function(minLinks, maxLinks, links) {

        // factor to get the share how much percent the link count takes from minLinks to maxLinks
        var linkFactor = 100 / (maxLinks - minLinks);

        // 0 <= share <= 1: how much percentage does the link count take from minLinks to maxLinks
        var share = (linkFactor * (links - minLinks)) / 100;

        /*
        max radius minus min radius contains the space between.
        multiplied with the share is equal to radius needed if min radius was zero.
        add min radius to get the final radius of the circle
         */
        var radius = (this.maxCircleRadius - this.minCircleRadius) * share + this.minCircleRadius;
        return radius;
    }
};

/* Graph */
var graph = {
    nodes: nodes,
    links: links
};

var minLinks = $.map(graph.nodes, function (node) {
    return calculateLinkCount(node.id, graph);
}).reduce(function (a, b) {
    return Math.min(a, b);
});

var maxLinks = $.map(graph.nodes, function (node) {
    return calculateLinkCount(node.id, graph);
}).reduce(function (a, b) {
    return Math.max(a, b);
});

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

var selectedNode;
var node = svg.append("g")
    .attr("class", "nodes")
    .selectAll("circle")
    .data(graph.nodes)
    .enter()
    .append("circle")
    .attr("r", function (d) {
        return graphSettings.getRadius(minLinks, maxLinks, calculateLinkCount(d.id, graph));
    })
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
