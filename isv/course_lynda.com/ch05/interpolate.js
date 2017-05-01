var dataArray = [{x: 6, y: 8}, {x: 13, y: 20}, {x: 23, y: 15}, {x: 35, y: 23}, {x: 42, y: 5}];
var interpolateTypes = [d3.curveLinear, d3.curveNatural, d3.curveStep, d3.curveBasis, d3.curveBundle, d3.curveCardinal];

var svg = d3.select('#d3container').append('svg')
    .attr('height', window.innerHeight)
    .attr('width', '100%');

for (var i in interpolateTypes) {
    /*
     d3 generator that produces x and y values that are used
     in the svg
     */
    var line = d3.line()
        /*
        d is the data object later used to create shapes
        i is the index of the object
         */
        .x(function (d, i) {
            return d.x * 6;
        })
        .y(function (d, i) {
            return d.y * 4;
        })
        .curve(interpolateTypes[i]);

    var shiftX = 0;
    var shiftY = i * 150;
    var chartGroup = svg.append('g')
        .attr('class', 'group' + i)
        .attr('transform', 'translate(' + shiftX + ', ' + shiftY + ')');

    chartGroup.append('path')
        .attr('fill', 'none')
        .attr('stroke', '#f44336')
        .attr('d', line(dataArray));

    chartGroup.selectAll('circle.grp' + i)
        .data(dataArray)
        .enter()
        .append('circle')
        .attr('class', function (d, i) {
            return 'grp' + i;
        })
        .attr('cx', function (d, i) {
            return d.x * 6;
        })
        .attr('cy', function (d, i) {
            return d.y * 4;
        })
        .attr('r', '2');
}

