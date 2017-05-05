var parseDate = d3.timeParse('%Y-%m-%d');
d3.csv('collections.csv')
    .row(function (d) {
        return {
            day: parseDate(d.day),
            collections: parseInt(d.collections)
        };
    })
    .get(function (error, data) {

        var height = 480;
        var width = 640;

        var max = d3.max(data, function (d) {
            return d.collections;
        });
        var minDate = d3.min(data, function (d) {
            return d.day;
        });
        var maxDate = d3.max(data, function (d) {
            return d.day;
        });

        var y = d3.scaleLinear()
            .domain([ 0, max ])
            .range([ height, 0 ]);

        var x = d3.scaleTime()
            .domain([ minDate, maxDate ])
            .range([ 0, width ]);

        var yAxis = d3.axisLeft(y);
        var xAxis = d3.axisBottom(x);

        var svg = d3.select('#d3container')
            .append('svg')
            .attr('height', '100%')
            .attr('width', '100%');

        var margin = {left: 50, right: 50, top: 40, bottom: 0};

        var chartGroup = svg.append('g').attr('transform', 'translate(' + margin.left + ',' + margin.top + ')');

        var line = d3.line()
            .x(function (d) {
                return x(d.day);
            })
            .y(function (d) {
                return y(d.collections);
            });
        chartGroup.append('path').attr('d', line(data));
        chartGroup.append('g')
            .attr('class', 'x axis')
            .attr('transform', 'translate(0, ' + height + ')')
            .call(xAxis);
        chartGroup.append('g')
            .attr('class', 'y axis')
            .call(yAxis);
    });

