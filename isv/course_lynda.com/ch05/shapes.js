var dataset = [18, 7, 13];
var dataDays = ['Mon', 'Wed', 'Fri'];

/*
 scaleSequential takes a interpolator as parameter. it divides
 the interpolator into a number of buckets taken from the domain
 and takes the color at the start of each bucket.

 https://github.com/d3/d3-scale/blob/master/README.md#interpolateRainbow
 */
var rainbow = d3.scaleSequential(d3.interpolateRainbow)
    .domain([0, 10])
;

var rainbow2 = d3.scaleSequential(d3.interpolateRainbow)
    .domain([0, 3])
;

// var x = d3.scaleOrdinal()
//     .domain(dataDays)
//     // 3 * 50 + 2 * 10 = 3 bars + 2 spacers
//     .range([0, 170]);
// var x = d3.scalePoint()
//     .domain(dataDays)
//     // 3 * 50 + 2 * 10 = 3 bars + 2 spacers
//     .range([0, 170]);
var x = d3.scaleBand()
    .domain(dataDays)
    .range([0, 170])
    // 11.76% of the chart is dedicated to white space
    .paddingInner(0.1176);
var xAxis = d3.axisBottom(x);

var svg = d3.select('#d3container').append('svg')
    .attr('height', window.innerHeight + 'px')
    .attr('width', '100%');

var cat20 = d3.schemeCategory20;
console.log(cat20);

svg.selectAll('rect').data(dataset)
    .enter()
    .append('rect')
    // d = data, i = index
    .attr('height', function (d) {
        return d * 5;
    })
    .attr('width', '50')
    .attr('x', function (d, i) {
        return 60 * i;
    })
    .attr('y', function (d) {
        return 400 - d * 5;
    })
    // .attr('fill', '#ff9800')
    .attr('fill', function (d, i) {
        return rainbow(i);
    })
;
svg.append('g')
    .attr('class', 'axis x hidden')
    .attr('transform', 'translate(0, 400)')
    .call(xAxis);

var newX = 200;
svg.selectAll('circle.first').data(dataset)
    .enter()
    .append('circle')
    .attr('class', 'first')
    .attr('cx', function (d, i) {
        newX += d * 3 + i * 30;
        return newX;
    })
    .attr('cy', '100')
    .attr('r', function (d, i) {
        return d * 2;
    })
    .attr('fill', function (d, i) {
        return rainbow2(i);
    })
;

var newX = 500;
svg.selectAll('ellipse').data(dataset)
    .enter()
    .append('ellipse')
    .attr('cx', function (d, i) {
        newX += d * 3 + i * 30;
        return newX;
    })
    .attr('cy', '100')
    .attr('rx', function (d, i) {
        return d * 2;
    })
    .attr('ry', '20')
    .attr('fill', function (d, i) {
        return cat20[i];
    })
;

newX = 200;
svg.selectAll('line').data(dataset)
    .enter()
    .append('line')
    /* see css
     * 1. style
     * 2. css
     * 3. attr
     */
    .attr('stroke', '#00c853')
    // .style('stroke', '#607d8b')
    .attr('stroke-width', '.5')
    .attr('x1', newX)
    .attr('y1', function (d, i) {
        return 200.5 + (i * 20);
    })
    .attr('x2', function (d) {
        return newX + d * 12;
    })
    .attr('y2', function (d, i) {
        return 200.5 + (i * 20);
    })
;

newX = 300;
var textArray = ['start', 'middle', 'end']
svg.append('text')
    .attr('x', newX)
    .attr('y', 300)
    .selectAll('tspan')
    .data(textArray)
    .enter().append('tspan')
    .attr('x', newX)
    .attr('y', function (d, i) {
        return 300 + (i * 32);
    })
    .attr('font-size', '32')
    .attr('fill', 'none')
    .attr('stroke', '#64b5f6')
    .attr('stroke-width', 1)
    .attr('text-anchor', 'start')
    .attr('dominant-baseline', 'middle')
    .text(function (d) {
        return d;
    });
svg.append('line')
    .attr('x1', newX)
    .attr('x2', newX)
    .attr('y1', 300)
    .attr('y2', 360);
