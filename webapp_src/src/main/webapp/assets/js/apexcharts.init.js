var daily_income = [];
var daily_orders = [];
var daily_visitors = [];
var daily_rating = [];
var daily_cat = [];

// start query
$.ajax({
    url: address + "spring/GetDay/MonthEvrData",
    type: "GET",
    success: function (data) {
        var j = 1;
        data.forEach(function (item) {
            daily_income.push(parseInt(item["dayTotal"]));
            daily_orders.push(parseInt(item["dayOrder"]));
            daily_visitors.push(parseInt(item["dayVisitor"]));
            daily_rating.push(parseFloat(item["dayGrade"]));
            daily_cat.push(j + "");
            j++;
        })

        generateAreaChart();
    },
    dataType: "json"
});

// end query

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


var nutritions = [[]];
var profit_nutritions = [450, 23, 35, 3.5];

const average = (...args) => args.reduce((a, b) => a + b) / args.length;

// start query
$.ajax({
    url: address + "spring/GetDay/AvgNutrition",
    type: "GET",
    success: function (data) {
        var j = 0;
        for (var i = 0; i < data.length; i++) {
            data[i]["fat"] = data[i]["fat"].toFixed(2);
            data[i]["protein"] = data[i]["protein"].toFixed(2);
            data[i]["vitamin"] = data[i]["vitamin"].toFixed(2);
            data[i]["calorie"] = data[i]["calorie"].toFixed(0);
            switch (data[i]["timePeriod"]) {
                case "morning":
                    j = 0;
                    break;
                case "afternoon":
                    j = 1;
                    break;
                case "evening":
                    j = 2;
                    break;
                default:
                    return;
            }
            nutritions[j] = [data[i]["calorie"], data[i]["fat"], data[i]["protein"], data[i]["vitamin"]];
        }
        setTotalDataBar();
        generateRadialCharts();
    },
    dataType: "json"
});

// end query

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

