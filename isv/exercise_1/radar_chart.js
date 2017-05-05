var RadarChart = {
    draw: function (id, d, options) {
        var config = {
            nodeRadius: 5,
            width: 600,
            height: 600,
            factorLegend: .85,
            levels: 3,
            maxValue: 0,
            radians: 2 * Math.PI,
            opacityArea: 0.3,
            ToRight: 5,
            TranslateX: 80,
            TranslateY: 30,
            ExtraWidth: 100,
            ExtraHeight: 100,
            color: d3.scaleSequential(d3.interpolateRainbow)
                .domain([0, d.length])
        };

        if (typeof options !== 'undefined') {
            for (var i in options) {
                if (options.hasOwnProperty(i)) {
                    config[i] = options[i];
                }
            }
        }
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

        var g = d3.select(id)
            .append("svg")
            .attr("width", config.width + config.ExtraWidth)
            .attr("height", config.height + config.ExtraHeight)
            .append("g")
            .attr("transform", "translate(" + config.TranslateX + "," + config.TranslateY + ")");

        // radar circles
        var circles = g.selectAll(".radar-circle")
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
            var levelRadius = radius * ((j + 1) / config.levels);
            var levelText = g.selectAll(".level")
                .data([1]) // dummy data, that text can be entered
                .enter()
                .append("text")
                .attr("class", "radar-circle-label");

            levelText.text(Number((j + 1) * config.maxValue / config.levels).toFixed(2))
                .attr("x", function () {
                    return config.width / 2 + config.ToRight;
                })
                .attr("y", function () {
                    return config.height / 2 - levelRadius;
                });
        }

        var series = 0;

        /*
         group that contains lines from center to edge and
         the text at the end of each line.
         */
        var axis = g.selectAll(".axis")
            .data(allAxis)
            .enter()
            .append("g")
            .attr("class", "axis");

        // line from center to the edge
        axis.append("line")
            .attr("x1", config.width / 2)
            .attr("y1", config.height / 2)
            .attr("x2", function (d, i) {
                return config.width / 2 * (1 - Math.sin(i * config.radians / axisCount));
            })
            .attr("y2", function (d, i) {
                return config.height / 2 * (1 - Math.cos(i * config.radians / axisCount));
            })
            .attr("class", "line");

        // text at the end of each line
        axis.append("text")
            .attr("class", "axis-label")
            .text(function (d) {
                return d;
            })
            .attr("transform", function(d, i) {
                var rotate = i < axisCount / 2.0
                    ? 90 - i * (360.0 / axisCount) // left half circle
                    : 270 - i * (360.0 / axisCount); // right half circle

                // calculate polar coordinates
                var angleDegree = 90 + i * 360.0 / axisCount;
                var angleRadians = -angleDegree * Math.PI / 180.0;

                var translateX = radius + radius * Math.cos(angleRadians);
                var translateY = radius + radius * Math.sin(angleRadians);

                return "translate(" + translateX + ", " + translateY + ") rotate(" + rotate + ")";
            })
        ;


        d.forEach(function (y) {
            dataValues = [];
            g.selectAll(".node")
                .data(y, function (d, i) {

                    // var angleDegree = 90 + i * 360.0 / axisCount;
                    // var angleRadians = -angleDegree * Math.PI / 180.0;
                    //
                    // var x = config.width / 2 + d.value * Math.cos(angleRadians);
                    // var y = config.height / 2 + d.value * Math.sin(angleRadians);
                    //
                    // y = config.width / 2 * (1 - (parseFloat(Math.max(d.value, 0)) / config.maxValue) * Math.sin(i * config.radians / axisCount));
                    // x = config.height / 2 * (1 - (parseFloat(Math.max(d.value, 0)) / config.maxValue) * Math.cos(i * config.radians / axisCount));

                    dataValues.push([
                        config.width / 2 * (1 - (parseFloat(Math.max(d.value, 0)) / config.maxValue) * Math.sin(i * config.radians / axisCount)),
                        config.height / 2 * (1 - (parseFloat(Math.max(d.value, 0)) / config.maxValue) * Math.cos(i * config.radians / axisCount))
                    ]);
                });
            dataValues.push(dataValues[0]);
            g.selectAll(".area")
                .data([dataValues])
                .enter()
                .append("polygon")
                .attr("id", "radar-chart-serie-" + series)
                .attr("class", "radar-chart-serie")
                .style("stroke", config.color(series))
                .attr("points", function (d) {
                    var str = "";
                    for (var pti = 0; pti < d.length; pti++) {
                        str = str + d[pti][0] + "," + d[pti][1] + " ";
                    }
                    return str;
                })
                .style("fill", function () {
                    return config.color(series);
                })
                .style("fill-opacity", config.opacityArea)
                .on('mouseover', function () {
                    var polygonId = "#" + d3.select(this).attr("id");
                    g.selectAll("polygon")
                        .transition(200)
                        .style("fill-opacity", 0.1);
                    g.selectAll(polygonId)
                        .transition(200)
                        .style("fill-opacity", .7);
                })
                .on('mouseout', function () {
                    g.selectAll("polygon")
                        .transition(200)
                        .style("fill-opacity", config.opacityArea);
                });
            series++;
        });
        series = 0;


        d.forEach(function (y, x) {
            g.selectAll(".node")
                .data(y)
                .enter()
                .append("circle")
                .attr("data-polygon-id", "radar-chart-serie-" + series)
                .attr("r", config.nodeRadius)
                .attr("title", function (j) {
                    return Math.max(j.value, 0)
                })
                .attr("cx", function (j, i) {
                    dataValues.push([
                        config.width / 2 * (1 - (parseFloat(Math.max(j.value, 0)) / config.maxValue) * Math.sin(i * config.radians / axisCount)),
                        config.height / 2 * (1 - (parseFloat(Math.max(j.value, 0)) / config.maxValue) * Math.cos(i * config.radians / axisCount))
                    ]);
                    return config.width / 2 * (1 - (Math.max(j.value, 0) / config.maxValue) * Math.sin(i * config.radians / axisCount));
                })
                .attr("cy", function (j, i) {
                    return config.height / 2 * (1 - (Math.max(j.value, 0) / config.maxValue) * Math.cos(i * config.radians / axisCount));
                })
                .style("fill", config.color(series))
                .style("fill-opacity", .9)
                .on('mouseover', function () {

                    var polygonId = "#" + d3.select(this).attr("data-polygon-id");
                    g.selectAll("polygon")
                        .transition(200)
                        .style("fill-opacity", 0.1);
                    g.selectAll(polygonId)
                        .transition(200)
                        .style("fill-opacity", .7);
                })
                .on('mouseout', function () {
                    g.selectAll("polygon")
                        .transition(200)
                        .style("fill-opacity", config.opacityArea);
                })
                .append("title")
                .text(function (j) {
                    return Math.max(j.value, 0)
                });

            series++;
        });
    }
};
