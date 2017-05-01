var dataArray = [];
var dataYears = [];
var year = 2000;
for (var i = 0; i < 18; i++) {
    dataArray.push(Math.random().toFixed(2) * 100);
    dataYears.push(year);
    year++;
}
console.log(dataArray);
console.log(dataYears);

var parseDate = d3.timeParse('%Y');
console.log(d3.extent(dataYears, function (d) {
    return parseDate(d);
}));

var height = 200;
var width = 480;

var margin = {
    left: 50,
    right: 50,
    top: 40,
    bottom: 40
};

var y = d3.scaleLinear()
/*
 domain contains range of values that should
 be displayed along the y axis. the two values
 contain the minimum and maximum value
 */
    .domain([0, d3.max(dataArray)])
    /*
     the range contains the space that should be
     used to display the values of the domain.
     for example a value of 75 inside the domain
     [0, 100] is mapped to 150 inside the range
     [0, 200].
     invert range so that nought is at the bottom
     and the highest value is at the top. this
     has to be done because (0, 0) is at the top
     left of the web page.
     */
    .range([height, 0]);

/*
 create a axis to the left that uses the linear
 scale created before. axis may be left, right,
 top or bottom. the number of ticks is not! the
 number of ticks used by d3 but more a suggestion
 how much should be used.
 */
var yAxis = d3.axisLeft(y)
    .ticks(4)
    .tickPadding(10)
    .tickSize(10)
;

var x = d3.scaleTime()
/*
 domain contains dates with the previously
 created year. months and days etc. are not
 of any concern. the extent function creates
 a two dimensional array with the min and
 max values of dataYears.
 */
    .domain(d3.extent(dataYears, function (d) {
        return parseDate(d);
    }))
    .range([0, width]);
var xAxis = d3.axisBottom(x);

/*
 map the data previously created to an d3 area
 */
var area = d3.area()
        .x(function (d, i) {
            /*
             x(date) returns the correct position. do use
             d here, because this is the y point in the chart
             */
            // return x(parseDate(dataYears[i]));
            // <=>
            return i * width / (dataYears.length - 1);
        })
        .y0(height)
        .y1(function (d) {
            return y(d);
        })
;

// append the svg element to the container
var svg = d3.select('#d3container').append('svg')
    .attr('height', window.innerHeight)
    .attr('width', '100%')
;

/*
 append a group to the svg, where elements like
 axis and path are put into
 */
var chartGroup = svg.append('g')
    .attr('transform', 'translate(' + margin.left + ', ' + margin.top + ')');

// append the path to the group
chartGroup.append('path').attr('d', area(dataArray))
    .attr('fill', '#ffe0b2')
    .attr('stroke', '#fb8c00');

// append the y axis to the group
chartGroup.append('g')
    .attr('class', 'axis y')
    .call(yAxis);

// append the x axis to the group
chartGroup.append('g')
    .attr('class', 'axis x')
    .attr('transform', 'translate(0, ' + height + ')')
    .call(xAxis);