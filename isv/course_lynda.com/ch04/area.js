var dataArray = [];
var dataYears = []
var year = 2000;
for (var i = 0; i < 17; i++) {
    dataArray.push(Math.random() * 100);
    dataYears.push(year);
    year++;
}

var height = 200;
var width = 500;

var area = d3.area()
    .x(function (d, i) {
        return i * 20;
    })
    .y0(height)
    .y1(function (d, i) {
        return height - d;
    })
    // .curve(d3.curveCardinal)
;

var svg = d3.select('#d3container').append('svg')
    .attr('height', window.innerHeight)
    .attr('width', '100%')
    // .attr('fill', 'none')
    // .attr('stroke', '#ff9800')
;

svg.append('path').attr('d', area(dataArray));