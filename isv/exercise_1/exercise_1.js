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

var workingData = {
    data: data.slice(),
    rows: rowLabels.slice(),
    columns: columnLabels.slice(),
    visibleRows: new Array(rowLabels.length).fill(true),
    visibleColumns: new Array(columnLabels.length).fill(true),
};

$("#toggle_axis").click(function () {
    var temp = workingData.rows;
    workingData.rows = workingData.columns;
    workingData.columns = temp;
    workingData.data = transpose(workingData.data);

    temp = workingData.visibleRows;
    workingData.visibleRows = workingData.visibleColumns;
    workingData.visibleColumns = temp;

    drawChart();
    drawLegend();
});

var config = {
    width: 640,
    height: 640,
    maxValue: 9.0,
    levels: 9.0,
    ExtraWidth: 128,
    ExtraHeight: 96,
    opacityArea: 0,
    factorLegend: 1,
    dotRadius: 4,
    dotLabelPadding: 4,
    dotOpacity: 1,
    TranslateX: 64,
    TranslateY: 64,
    color: d3.schemeCategory20b
    // color: ["#f44336", "#e91e63", "#9c27b0", "#673ab7", "#3f51b5", "#2196f3", "#03a9f4", "#00bcd4", "#009688", "#4caf50", "#8bc34a", "#cddc39", "#ffeb3b", "#ffc107", "#ff9800", "#ff5722", "#795548", "#9e9e9e", "#607d8b"]
};

function drawChart() {

    var svg = d3.select("#chart");
    var chartData = [];
    for (var row = 0; row < workingData.data.length; row++) {
        var values = [];
        var columnPointer = 0;

        for (var column = 0; column < workingData.data[row].length; column++) {
            while (!workingData.visibleColumns[columnPointer]
                    && columnPointer < workingData.columns.length) {
                columnPointer++;
            }
            values.push({
                axis: workingData.columns[columnPointer],
                value: workingData.data[row][column]
            });
            columnPointer++;
        }
        chartData.push(values);
    }

    var _config = $.extend({}, config);
    /*
     only push colors, which labels are enabled otherwise
     colors that are used inside the legend do not match colors
     used for the areas.
     */
    _config.color = [];
    for (var i = 0; i < config.color.length; i++) {
        if (workingData.visibleRows[i])
            _config.color.push(config.color[i]);
    }

    RadarChart.draw("#chart", chartData, _config);

    svg.exit().remove();
}

function drawLegend() {

    var legendContainer = d3.select("#legend");
    legendContainer.select("svg").remove();

    legendContainer = d3.select("#legend")
        .append("svg")
        .attr("height", config.height + config.ExtraHeight)
    ;

    // Initiate Legend
    var legend = legendContainer.append("g")
        .attr("height", 100)
        .attr("width", 200)
    ;
    // create colour squares
    legend.selectAll('rect')
        .data(workingData.rows)
        .enter()
        .append("rect")
        .attr("y", function (d, i) {
            return i * 24;
        })
        .attr("width", 10)
        .attr("height", 10)
        .style("fill", function (d, i) {
            return config.color[i];
        })
    ;
    // create text next to squares
    legend.selectAll('text')
        .data(workingData.rows)
        .enter()
        .append("text")
        .attr("class", "legend")
        .attr("data-legend-serie", function (d, i) {
            return "legend-serie-" + i;
        })
        .attr("text-decoration", function (d, i) {
            return workingData.visibleRows[i]
                ? "none"
                : "line-through";
        })
        .attr("x", 15)
        .attr("y", function (d, i) {
            return i * 24 + 9;
        })
        .style("font-size", "12px")
        .text(function (d) {
            return d;
        }).on('mouseover', function (d, i) {

            d3.select(this)
                .transition(200)
                .style("fill", config.color[i])
                .style("font-size", "16px");
            if (workingData.visibleRows[i]) {

                var serie = getChartSerieIndex(i);
                d3.select("#radar-chart-serie-" + serie)
                    .transition(200)
                    .style("fill-opacity", .7);

                d3.selectAll('circle[data-serie="' + serie + '"]')
                    .transition(200)
                    .style("r", config.dotRadius * 2)
                    .style("fill-opacity", 1);
                // show data value at the axis label
                for (var column = 0; column < workingData.columns.length; column++) {
                    d3.select('tspan[data-axis-label-value-id="axis-label-value-' + column + '"]')
                        .text(workingData.data[serie][column]);
                }
            }
        }).on('mouseout', function (d, i) {
            d3.select(this)
                .transition(200)
                .style("fill", "#616161")
                .style("font-size", "12px");
            if (workingData.visibleRows[i]) {

                var serie = getChartSerieIndex(i);
                d3.select("#radar-chart-serie-" + serie)
                    .transition(200)
                    .style("fill-opacity", 0);

                d3.selectAll('circle[data-serie="' + serie + '"]')
                    .transition(200)
                    .style("r", config.dotRadius)
                    .style("fill-opacity", config.dotOpacity);

                for (var column = 0; column < workingData.columns.length; column++) {
                    d3.select('tspan[data-axis-label-value-id="axis-label-value-' + column + '"]')
                        .text("");
                }
            }
        });

    d3.selectAll(".series-dot")
        .on("mouseover.example", function () {
            var serie = d3.select(this).attr("data-serie");
            serie = getLegendSerieIndex(parseInt(serie));
            d3.select('.legend[data-legend-serie="legend-serie-' + serie + '"]')
                .transition(200)
                .style("font-size", "16px");
        })
        .on("mouseout.example", function () {

            var serie = d3.select(this).attr("data-serie");
            serie = getLegendSerieIndex(parseInt(serie));
            d3.select('.legend[data-legend-serie="legend-serie-' + serie + '"]')
                .transition(200)
                .style("font-size", "11px");
        });

    d3.selectAll('text[class="legend"]')
        .on("click", function (d, i) {
            var textDecoration;
            var realTarget = getChartSerieIndex(i);

            // hide
            if (workingData.visibleRows[i]) {
                textDecoration = "line-through";
                workingData.data.splice(realTarget, 1);
            } else {
                // show
                textDecoration = "none";
                workingData.data.splice(realTarget, 0, data[i]);
            }
            d3.select(this).style("text-decoration", textDecoration);
            workingData.visibleRows[i] = !workingData.visibleRows[i];
            drawChart();
        });
}

function getChartSerieIndex(estimated) {

    var realTarget = 0;
    for (var j = 0; j < estimated; j++) {
        realTarget += workingData.visibleRows[j] ? 1 : 0;
    }
    return realTarget;
}

function getLegendSerieIndex(estimated) {

    var serie = 0;
    while (serie < workingData.visibleRows.length
            && (!workingData.visibleRows[serie] || serie < estimated)) {

        if (!workingData.visibleRows[serie]) {
            estimated++;
        }
        serie++;
    }
    return serie;
}

function transpose(data) {
    var rows = data.length;
    var columns = data[0].length;

    var row, column, result = [];

    // Loop through every item in the outer array (rows)
    for (row = 0; row < columns; row++) {

        // Insert a new row (array)
        result[row] = [];

        // Loop through every item per item in outer array (width)
        for (column = 0; column < rows; column++) {

            // Save transposed data.
            result[row][column] = data[column][row];
        }
    }

    return result;
}

function sizeDiagramElements() {
    $("#chart").width($("#chart svg").width());
    $("#legend").width($("#legend svg").width());
}

// init materialize modals
$(document).ready(function(){
    $('.modal').modal();
});

drawChart();
drawLegend();
sizeDiagramElements();
