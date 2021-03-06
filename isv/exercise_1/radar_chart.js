/**
 * Use RadarChart.draw to draw a circular radar chart with data of the form:
 * <pre>
 * [
 *   [
 *     {
 *       axis: "label",
 *       value: 1
 *     },
 *     {
 *       axis: "label2",
 *       value: 2
 *     },
 *   ],
 *   [
 *     {
 *       axis: "label",
 *       value: 3.1
 *     },
 *     {
 *       axis: "label2",
 *       value: 0.4
 *     },
 *   ],
 * ]
 * </pre>
 *
 * The axis acts as id the value is show at the axis. Calculations
 * depend highly on the use of polar coordinates.
 *
 * @see https://en.wikipedia.org/wiki/Polar_coordinate_system
 * @type {{draw: RadarChart.draw, toRadians: RadarChart.toRadians}}
 */
var RadarChart = {
    draw: function (id, d, options) {
        var config = {
            dotRadius: 5,
            dotLabelPadding: 4,
            dotOpacity: 1,
            width: 600,
            height: 600,
            factorLegend: .85,
            levels: 3,
            maxValue: 0,
            axisLabelRadius: 10,
            opacityArea: 0.3,
            opacityAreaHighlight: 0.7,
            CircleLabelX: 5,
            TranslateX: 80,
            TranslateY: 30,
            ExtraWidth: 100,
            ExtraHeight: 100,
            color: d3.schemeCategory20
        };

        if (d.length == 0) {
            var container = d3.select(id);
            container.select("svg").remove();
            container.append("svg")
                .append("text")
                .attr("y", "1.2em")
                .text("Keine Daten anzuzeigen");
            return;
        }

        if (typeof options !== 'undefined') {
            for (var i in options) {
                if (options.hasOwnProperty(i)) {
                    config[i] = options[i];
                }
            }
        }
        config.radians = 2 * Math.PI;

        // calculate the maximum value of config and all given values in d
        config.maxValue = Math.max(config.maxValue, d3.max(d, function (i) {
            return d3.max(i.map(function (o) {
                return o.value;
            }))
        }));
        // extract all axis elements of d objects
        var allAxis = (d[0].map(function (i) {
            return i.axis;
        }));
        var axisCount = allAxis.length;
        var radius = Math.min(config.width / 2, config.height / 2);
        d3.select(id).select("svg").remove();

        var svg = d3.select(id)
            .append("svg")
            .attr("width", config.width + config.ExtraWidth)
            .attr("height", config.height + config.ExtraHeight)
            .append("g")
            .attr("transform", "translate(" + config.TranslateX + "," + config.TranslateY + ")");

        // radar circles
        var circles = svg.selectAll(".radar-circle")
            .data(new Array(config.levels - 1))
            .enter()
            .append("circle")
            .attr("cx", config.width / 2)
            .attr("cy", config.height / 2)
            .attr("class", "radar-circle");

        circles.attr("r", function (d, i) {
            var circleRadius = radius * ((i + 1) / config.levels);
            return circleRadius;
        });

        // Text indicating at what value each level is
        for (var j = 0; j < config.levels - 1; j++) {
            var levelText = svg.selectAll(".level")
                .data([1]) // dummy data, that text can be entered
                .enter()
                .append("text")
                .attr("class", "radar-circle-label");

            levelText.text(Number((j + 1) * config.maxValue / config.levels).toFixed(2))
                .attr("x", function () {
                    return config.width / 2 + config.CircleLabelX;
                })
                .attr("y", function () {
                    var levelRadius = radius * ((j + 1) / config.levels);
                    return config.height / 2 - levelRadius;
                });
        }

        var series = 0;

        /*
         group that contains lines from center to edge and
         the text at the end of each line.
         */
        var axis = svg.selectAll(".axis")
            .data(allAxis)
            .enter()
            .append("g")
            .attr("class", "axis");

        // line from center to the edge
        axis.append("line")
            .attr("x1", config.width / 2)
            .attr("y1", config.height / 2)
            .attr("x2", function (d, i) {
                var x2 = radius + radius * Math.cos(RadarChart.toRadians(i, axisCount));
                return x2;
            })
            .attr("y2", function (d, i) {
                var y2 = radius + radius * Math.sin(RadarChart.toRadians(i, axisCount));
                return y2;
            })
            .attr("class", "line");

        // text at the end of each line
        var axisLabel = axis.append("text")
            .attr("class", "axis-label")
            .attr("id", function (d, i) {
                return "axis-label-" + i;
            })
            .attr("data-translate", function(d, i) {
                // calculate polar coordinates
                var angleRadians = RadarChart.toRadians(i, axisCount);

                var textRadius = radius + config.axisLabelRadius;
                var translateX = radius + textRadius * Math.cos(angleRadians);
                var translateY = radius + textRadius * Math.sin(angleRadians);
                return "(" + translateX + ", " + translateY + ")";
            })
            .attr("data-rotate", function(d, i) {
                var angle = i < axisCount / 2.0
                    ? 90 - i * (360.0 / axisCount) // left half circle
                    : 270 - i * (360.0 / axisCount); // right half circle
                return "(" + angle + ")";
            });
        axisLabel.attr("transform", function() {
            var translate = d3.select(this).attr("data-translate");
            var rotate = d3.select(this).attr("data-rotate");
            return "translate " + translate + " rotate " + rotate;
        });
        axisLabel.append("tspan")
            .attr("x", "0")
            .text(function (d) {
                return d;
            });
        axisLabel.append("tspan")
            .attr("x", "0")
            .attr("dy", "1.2em")
            .attr("data-axis-label-value-id", function (d, i) {
                return "axis-label-value-" + i;
            });

        // data areas for each object given at draw
        d.forEach(function (row) {
            dataValues = [];
            svg.selectAll(".area")
                .data(row, function (d, i) {

                    var angleRadians = RadarChart.toRadians(i, axisCount);

                    var radiusX = ((config.width / 2) / config.maxValue) * d.value;
                    var radiusY = ((config.height / 2) / config.maxValue) * d.value;

                    var x = config.width / 2 + radiusX * Math.cos(angleRadians);
                    var y = config.height / 2 + radiusY * Math.sin(angleRadians);

                    dataValues.push([x, y]);
                });
            // close the path
            dataValues.push(dataValues[0]);
            svg.selectAll(".area")
                .data([dataValues])
                .enter()
                .append("polygon")
                .attr("id", "radar-chart-serie-" + series)
                .attr("class", "radar-chart-serie")
                .style("stroke", config.color[series])
                .attr("points", function (d) {
                    var str = "";
                    for (var pti = 0; pti < d.length; pti++) {
                        str = str + d[pti][0] + "," + d[pti][1] + " ";
                    }
                    return str;
                })
                .style("fill", function () {
                    return config.color[series];
                })
                .style("fill-opacity", config.opacityArea)
            ;
            series++;
        });
        series = 0;

        d.forEach(function (row) {

            svg.selectAll(".dot-label-" + series)
                .data(row)
                .enter()
                .append("text")
                .attr("data-area-label-serie", "area-label-" + series)
                .attr("data-dot-label-id", function (d, i) {
                    return series + "-" + i;
                })
                .attr("class", "dot-label")
                .attr("x", function (d, i) {

                    var radiusX = ((config.width / 2) / config.maxValue) * d.value;
                    var x = config.width / 2 + radiusX * Math.cos(RadarChart.toRadians(i, axisCount)) + config.dotRadius;
                    return x;

                })
                .attr("y", function (d, i) {

                    var radiusY = ((config.height / 2) / config.maxValue) * d.value;
                    var y = config.height / 2 + radiusY * Math.sin(RadarChart.toRadians(i, axisCount)) - config.dotRadius;
                    return y;
                })
                .text(function (d) {
                    return Math.max(d.value, 0);
                })
            ;
            series++;
        });
        series = 0;

        // draw dot on each axis for each area
        d.forEach(function (row) {
            svg.selectAll(".dot")
                .data(row)
                .enter()
                .append("circle")
                .attr("class", "series-dot")
                .attr("data-serie", series)
                .attr("data-circle-id", function (d, i) {
                    return series + "-" + i;
                })
                .attr("r", config.dotRadius)
                .attr("title", function (d) {
                    return Math.max(d.value, 0)
                })
                .attr("cx", function (d, i) {

                    var radiusX = ((config.width / 2) / config.maxValue) * d.value;
                    var x = config.width / 2 + radiusX * Math.cos(RadarChart.toRadians(i, axisCount));
                    return x;

                })
                .attr("cy", function (d, i) {

                    var radiusY = ((config.height / 2) / config.maxValue) * d.value;
                    var y = config.height / 2 + radiusY * Math.sin(RadarChart.toRadians(i, axisCount));
                    return y;
                })
                .style("fill", config.color[series])
                .style("fill-opacity", config.dotOpacity)
                .on('mouseover.rc', function (d, i) {

                    var polygonId = "#radar-chart-serie-" + d3.select(this).attr("data-serie");
                    var series = d3.select(this).attr("data-serie");
                    svg.selectAll("polygon")
                        .transition(200)
                        .style("fill-opacity", config.opacityArea);
                    svg.selectAll(polygonId)
                        .transition(200)
                        .style("fill-opacity", config.opacityAreaHighlight);

                    svg.selectAll('circle[data-circle-id="' + series + '-' + i + '"]')
                        .transition(200)
                        .style("r", config.dotRadius * 2)
                        .style("fill-opacity", 1);

                    svg.select('text[id="axis-label-' + i + '"]')
                        .transition(200)
                        .attr("transform", function () {

                            var translate = d3.select(this).attr("data-translate");
                            var rotate = d3.select(this).attr("data-rotate");
                            return "translate " + translate + " rotate " + rotate + " scale(2)";
                        })
                    ;
                    svg.select('tspan[data-axis-label-value-id="axis-label-value-' + i + '"]')
                        .transition(200)
                        .text(d3.select(this).attr("title"));
                })
                .on('mouseout.rc', function (d, i) {
                    var series = d3.select(this).attr("data-serie");
                    svg.selectAll("polygon")
                        .transition(200)
                        .style("fill-opacity", config.opacityArea);

                    svg.selectAll('circle[data-circle-id="' + series + '-' + i + '"]')
                        .transition(200)
                        .style("r", config.dotRadius)
                        .style("fill-opacity", config.dotOpacity);

                    svg.select('text[id="axis-label-' + i + '"]')
                        .transition(200)
                        .attr("transform", function () {
                            var translate = d3.select(this).attr("data-translate");
                            var rotate = d3.select(this).attr("data-rotate");
                            return "translate " + translate + " rotate " + rotate;
                        })
                    ;
                    svg.select('tspan[data-axis-label-value-id="axis-label-value-' + i + '"]')
                        .transition(200)
                        .text("");
                });

            series++;
        });
    },

    /**
     * Returns the angle in radians with which the polar coordinates of an element
     * of the chart can be calculated. Polar coordinates are:
     *
     * x = r * cos(phi)
     * y = r * sin(phi)
     *
     * @param elementIndex  i = 0 for the element that is displayed at 90°
     * @param axisCount  count of all axis of the chart
     * @returns {number} phi
     */
    toRadians: function (elementIndex, axisCount) {
        var angleDegree = 90 + elementIndex * 360.0 / axisCount;
        var angleRadians = -angleDegree * Math.PI / 180.0;
        return angleRadians;
    }
};
