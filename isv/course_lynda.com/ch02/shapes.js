var dataset = [22, 7, 13];

window.addEventListener('resize', function () {
    d3.select('#d3container').attr('height', window.innerHeight);
}, true);

var svg = d3.select('#d3container').append('svg')
    .attr('height', window.innerHeight + 'px')
    .attr('width', '100%');

svg.selectAll('rect').data(dataset)
    .enter()
    .append('rect')
    // d = data, i = index
    .attr('height', function (d, i) {
        return d * 5;
    })
    .attr('width', '50')
    .attr('x', function (d, i) {
        return 60 * i;
    })
    .attr('y', function (d, i) {
        return 400 - d * 5
    })
    .attr('fill', '#ff9800')
;