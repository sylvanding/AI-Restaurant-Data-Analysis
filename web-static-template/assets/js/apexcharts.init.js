// start query
var daily_income = [1000, 1500, 1400, 2000, 1800, 1600, 2100];
var daily_orders = [500, 400, 100, 150, 300, 600, 400];
var daily_visitors = [60, 40, 100, 20, 90, 30, 10];
var daily_rating = [9.5, 8.5, 4.5, 9, 2.5, 3.5, 8];
var daily_cat = ['Jan-1', 'Jan-2', 'Jan-3', 'Jan-4', 'Jan-5', 'Jan-6', 'Jan-7'];
// end query

generateAreaChart();

function generateAreaChart() {
    var daily_series = [daily_income, daily_orders, daily_visitors, daily_rating];
    var transaction_min = [];
    var transaction_max = [];
    daily_series.forEach(function (item) {
        transaction_min.push(Math.min(...item));
        transaction_max.push(Math.max(...item));
    });

    var daily_series_scale = [];
    for (var i = 0; i < daily_series.length; i++) {
        var scaled = [];
        daily_series[i].forEach(function (value) {
            scaled.push(((value - transaction_min[i]) / (transaction_max[i] - transaction_min[i])));
        });
        daily_series_scale.push(scaled);
    }

    var options = {
        chart: {height: 380, type: "area", zoom: {enabled: !1}, toolbar: {show: !1}},
        colors: ["#00b894", "#74b9ff", "#fab1a0", "#ffeaa7"],
        dataLabels: {enabled: !1},
        stroke: {width: [3, 3, 3, 3], curve: "smooth"},
        fill: {type: "gradient"},
        series: [
            {name: "Daily Income", data: daily_series_scale[0]},
            {name: "Daily Orders", data: daily_series_scale[1]},
            {name: "Daily Visitors", data: daily_series_scale[2]},
            {name: "Daily Rating", data: daily_series_scale[3]}
        ],
        grid: {row: {colors: ["transparent", "transparent"], opacity: 0.2}, borderColor: "#f1f1f1"},
        markers: {style: "inverted", size: 6},
        yaxis: {
            show: false,
            labels: {
                formatter: function (val, index) {
                    if (index !== undefined) {
                        var i = index.seriesIndex;
                        return (val * (transaction_max[i] - transaction_min[i]) + transaction_min[i]).toFixed(2);
                    }
                }
            }
        },
        xaxis: {categories: daily_cat, title: {text: "Date"}, tooltip: {enabled: false}},
        legend: {position: "top", horizontalAlign: "right", floating: !0, offsetX: -5},
        tooltip: {followCursor: true},
        responsive: [{breakpoint: 600, options: {chart: {toolbar: {show: !1}}, legend: {show: !1}}}]
    }, chart = new ApexCharts(document.querySelector("#daily-transaction-events-Area-Chart"), options);
    chart.render();
}


const average = (...args) => args.reduce((a, b) => a + b) / args.length;

// start query
var nutritions = [[400, 13.5, 27.4, 3.2], [400, 13.5, 27.4, 3.2], [400, 13.5, 27.4, 3.2]];
var profit_nutritions = [450, 23, 35, 3.5];
// end query

setTotalDataBar();
generateRadialCharts();

function generateRadialCharts() {
    series_array = [];
    nutrition_module_name = ['breakfasts', 'lunches', 'dinners'];

    for (var i = 0; i < nutrition_module_name.length; i++) {
        $("#" + nutrition_module_name[i] + "_calorie").text(nutritions[i][0]);
        $("#" + nutrition_module_name[i] + "_fat").text(nutritions[i][1]);
        $("#" + nutrition_module_name[i] + "_protein").text(nutritions[i][2]);
        $("#" + nutrition_module_name[i] + "_vitamin").text(nutritions[i][3]);
    }

    nutritions.forEach(function (nutrition) {
        var series = [];
        for (var i = 0; i < nutrition.length; i++) {
            series.push(100 * nutrition[i] / profit_nutritions[i]);
        }
        series_array.push(series);
    });

    options = {
        chart: {height: 350, type: "radialBar"},
        plotOptions: {
            radialBar: {
                hollow: {size: '35%'},
                dataLabels: {
                    name: {fontSize: "22px"},
                    value: {
                        fontSize: "16px",
                        formatter: function (val) {
                            if (val[-1] !== '%') {
                                return parseFloat(val).toFixed(2) + "%";
                            }
                        }
                    },
                    total: {
                        show: !0, label: "Breakfast",
                        formatter: function (val) {
                            return 0;
                        }
                    }
                }
            }
        },
        series: [],
        labels: ["Calorie", "Fat", "Protein", "Vitamin"],
        colors: ["#5664d2", "#fcb92c", "#1cbb8c", "#ff3d60"]
    };

    const capitalize = str => str.charAt(0).toUpperCase() + str.slice(1);
    for (var i = 0; i < nutrition_module_name.length; i++) {
        options.series = series_array[i];
        options.plotOptions.radialBar.dataLabels.total.label = capitalize(nutrition_module_name[i]);
        options.plotOptions.radialBar.dataLabels.total.formatter = function (val) {
            return average(...options.series).toFixed(2) + "%";
        }
        chart = new ApexCharts(document.querySelector("#radial_chart_" + nutrition_module_name[i]), options);
        chart.render();
    }
}

function setTotalDataBar() {
    $("#total-income").text(average(...daily_income).toFixed(2));
    $("#total-orders").text(average(...daily_orders).toFixed(2));
    $("#total-visitors").text(average(...daily_visitors).toFixed(2));
    $("#avg-rating").text(average(...daily_rating).toFixed(2));
}

