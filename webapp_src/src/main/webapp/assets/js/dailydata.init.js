const address = "http://175.178.233.192:8080/";

function set_daily_up_or_down(block_name, rate) {
    if (rate >= 0)
        $(block_name).attr("class", "mdi mdi-arrow-up text-success me-2");
    else
        $(block_name).attr("class", "mdi mdi-arrow-down text-danger me-2");
}

$.ajax({
    url: address + "spring/GetDay/totalData?day=29",
    type: "GET",
    success: function (data) {
        const moneyRate = parseFloat(data["moneyRate"]);
        $("#daily-earnings-amount").text(parseFloat(data["totalMoney"]).toFixed(0));
        $("#daily-earnings-percentage").text((moneyRate * 100).toFixed(2) + "%");
        set_daily_up_or_down("#daily-earnings-mark", moneyRate);

        const orderRate = parseFloat(data["orderRate"]);
        $("#daily-orders-amount").text(parseFloat(data["totalOrder"]).toFixed(0));
        $("#daily-orders-percentage").text((orderRate * 100).toFixed(2) + "%");
        set_daily_up_or_down("#daily-orders-mark", orderRate);

        const visitorRate = parseFloat(data["visitorRate"]);
        $("#daily-visitors-amount").text(parseFloat(data["totalVisitor"]).toFixed(0));
        $("#daily-visitors-percentage").text((visitorRate * 100).toFixed(2) + "%");
        set_daily_up_or_down("#daily-visitors-mark", visitorRate);

        const markRate = parseFloat(data["markRate"]);
        $("#daily-rating-amount").text(parseFloat(data["dayAvgMark"]).toFixed(2));
        $("#daily-rating-percentage").text((markRate * 100).toFixed(2) + "%");
        set_daily_up_or_down("#daily-rating-mark", markRate);
    },
    dataType: "json"
});
