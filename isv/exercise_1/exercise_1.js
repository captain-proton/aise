var data = [
    [2.52, 6.21, 2.10, 8.17, 4.23, 3.60, 3.62, 7.27, 1.21, 1.77, 5.33, 1.79, 2.21, 6.23, 1.94],
    [5.00, 8.56, 8.73, 3.62, 7.79, 3.77, 3.12, 2.88, 3.58, 2.23, 4.50, 4.42, 3.04, 8.00, 3.79],
    [1.44, 8.08, 4.27, 4.87, 5.48, 7.04, 5.17, 7.17, 1.52, 2.15, 4.17, 3.12, 3.08, 7.10, 3.00],
    [2.56, 8.52, 4.92, 2.58, 8.44, 2.29, 2.54, 3.96, 1.67, 2.50, 3.25, 2.29, 3.21, 7.13, 1.96],
    [7.94, 8.42, 7.71, 7.00, 8.13, 5.63, 5.40, 7.77, 3.06, 5.00, 5.06, 7.42, 5.21, 8.10, 6.92],
    [1.38, 3.29, 2.38, 2.85, 1.38, 1.77, 3.52, 3.58, 0.62, 1.42, 1.92, 1.71, 3.13, 2.60, 1.33],
    [1.94, 8.46, 1.08, 4.85, 1.73, 0.75, 1.31, 2.48, 1.04, 1.21, 1.83, 1.48, 1.37, 5.88, 1.65],
    [5.58, 8.19, 4.75, 3.38, 4.83, 1.46, 4.96, 4.81, 1.46, 2.81, 4.08, 3.54, 3.71, 7.40, 4.88],
    [2.46, 4.98, 6.21, 2.73, 7.48, 4.08, 4.13, 1.73, 1.37, 2.58, 1.71, 2.31, 7.15, 7.94, 2.42],
    [1.96, 8.25, 5.17, 5.38, 7.67, 2.90, 6.21, 4.71, 1.90, 5.04, 4.31, 3.75, 3.44, 8.23, 4.13],
    [1.63, 7.40, 4.79, 3.04, 5.10, 1.31, 5.12, 4.48, 1.58, 2.54, 2.58, 2.12, 3.48, 6.77, 1.73],
    [2.83, 7.25, 2.81, 3.46, 2.35, 2.83, 5.04, 4.75, 1.77, 5.12, 3.48, 3.65, 4.79, 5.90, 3.52],
    [6.15, 8.58, 8.52, 8.29, 7.94, 8.85, 7.67, 8.58, 4.25, 6.81, 7.52, 6.73, 8.00, 8.17, 6.44],
    [4.40, 7.88, 6.54, 7.73, 7.19, 6.08, 5.50, 8.56, 2.40, 4.00, 4.88, 4.58, 3.88, 7.75, 3.60],
    [4.12, 8.08, 5.08, 4.56, 8.04, 2.98, 5.23, 3.69, 2.04, 3.85, 4.98, 7.12, 4.31, 7.90, 7.94]
];
var rowLabels = ["Class", "Date", "Bus", "Family dinner", "Park", "Church", "Job interview", "Sidewalk", "Movies",
    "Bar", "Elevator", "Restroom", "Own room", "Dorm lounge", "Football game"];
var columnLabels = ["Run", "Talk", "Kiss", "Write", "Eat", "Sleep", "Mumble", "Read", "Fight", "Belch", "Argue",
    "Jump", "Cry", "Laugh", "Shout"];

var width = 640;
var height = 640;

var colorscale = d3.schemeCategory20b;

var chartData = [];
for (var row = 0; row < data.length; row++) {
    var values = [];
    for (var column = 0; column < data[row].length; column++) {
        values.push({
            axis: columnLabels[column],
            value: data[row][column]
        });
    }
    chartData.push(values);
}

var config = {
    w: width,
    h: height,
    maxValue: 9.0,
    levels: 9.0,
    ExtraWidth: 480,
    ExtraHeight: 128,
    opacityArea: 0,
    factorLegend: 1,
    dotRadius: 4,
    dotLabelPadding: 4,
    color: colorscale
};

RadarChart.draw("#diagram", chartData, config);

var svg = d3.select('#content')
    .selectAll("svg")
    .append("svg")
    .attr("width", width + config.ExtraWidth)
    .attr("height", height + config.ExtraHeight);

//Create the title for the legend
var text = svg.append("text")
    .attr("class", "legend-title")
    .attr("transform", "translate(200,0)")
    .attr("x", width - 70)
    .attr("y", 10)
    .text("How appropriate is a behaviour in a location")
;

//Initiate Legend
var legend = svg.append("g")
    .attr("height", 100)
    .attr("width", 200)
    .attr('transform', 'translate(200,20)')
;
//Create colour squares
legend.selectAll('rect')
    .data(rowLabels)
    .enter()
    .append("rect")
    .attr("x", width - 65)
    .attr("y", function (d, i) {
        return i * 20;
    })
    .attr("width", 10)
    .attr("height", 10)
    .style("fill", function (d, i) {
        return colorscale[i];
    })
;
//Create text next to squares
legend.selectAll('text')
    .data(rowLabels)
    .enter()
    .append("text")
    .attr("class", "legend")
    .attr("x", width - 52)
    .attr("y", function (d, i) {
        return i * 20 + 9;
    })
    .text(function (d) {
        return d;
    }).on('mouseover', function (d, i) {
        d3.select("#radar-chart-serie-" + i)
            .transition(200)
            .style("fill-opacity", .7);

        d3.selectAll('text[data-area-label-serie="area-label-' + i + '"]')
            .transition(200)
            .style("fill-opacity", 1)
            .style("stroke-opacity", 1)
        ;
    }).on('mouseout', function (d, i) {
        d3.select("#radar-chart-serie-" + i)
            .transition(200)
            .style("fill-opacity", 0);

        d3.selectAll('text[data-area-label-serie="area-label-' + i + '"]')
            .transition(200)
            .style("fill-opacity", 0)
            .style("stroke-opacity", 0);
    })
;