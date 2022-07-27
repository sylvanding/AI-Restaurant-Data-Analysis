<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% if (session.getAttribute("username") == null) response.sendRedirect("login.jsp"); %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.demo.MostPopularDishes" %>
<%@ page import="com.example.demo.LeastPopularDishes" %>
<%@ page import="com.example.demo.OrdersData" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>AI-Restaurant Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- DataTables -->
    <link href="assets/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <!-- Bootstrap Css -->
    <link href="assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css">
    <!-- Icons Css -->
    <link href="assets/css/icons.min.css" rel="stylesheet" type="text/css">
    <!-- App Css-->
    <link href="assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css">
    <!-- My Css-->
    <link href="assets/css/myCss.css" rel="stylesheet" type="text/css">
</head>

<body>
<!-- Begin layout-wrapper -->
<div id="layout-wrapper">
    <!-- begin main content-->
    <div class="main-content">
        <div class="page-content">
            <div class="container-fluid">
                <!-- start page title -->
                <div class="row">
                    <div class="col-sm-6 col-lg-4">
                        <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                            <h4 class="web-name mb-sm-0" onclick="location.reload();"><i class="fas fa-chart-line"></i>
                                AI-Restaurant Dashboard</h4>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-8">
                        <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                            <%
                                Date d = new Date();
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String now = df.format(d);
                            %>
                            <marquee class="alert alert-danger">欢迎 <%= session.getAttribute("username") %> ! | 丁纪翔
                                1925102007 | 骆华钦 1925103027 | 陈鑫 1925103005 | 梁恺恒
                                1925103021 | 姜宇晨 1925102018 | <%= now %>
                            </marquee>
                        </div>
                    </div>
                </div>
                <!-- end page title -->
                <!-- start card only with number -->
                <div class="row">
                    <!-- start Daily Earnings -->
                    <div class="col-sm-6 col-lg-3">
                        <div class="card text-center">
                            <div class="card-body">
                                <h4 class="card-title text-muted">Daily Earnings</h4>
                                <h2 class="mt-3 mb-2"><i class="mdi mdi-arrow-down text-danger me-2"
                                                         id="daily-earnings-mark"></i>
                                    <b>$</b> <b id="daily-earnings-amount">8952</b>
                                </h2>
                                <p class="text-muted mb-0 mt-3"><b id="daily-earnings-percentage">48%</b> From Last
                                    Day</p>
                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Daily Earnings</b> shows total earnings on a given day and compares it to the past day's.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end Daily Earnings -->
                    <!-- start Daily Orders -->
                    <div class="col-sm-6 col-lg-3">
                        <div class="card text-center">
                            <div class="card-body">
                                <h4 class="card-title text-muted">Daily Orders</h4>
                                <h2 class="mt-3 mb-2"><i class="mdi mdi-arrow-up text-success me-2"
                                                         id="daily-orders-mark"></i>
                                    <b id="daily-orders-amount">430</b>
                                </h2>
                                <p class="text-muted mb-0 mt-3"><b id="daily-orders-percentage">56%</b> From
                                    Last Day</p>
                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Daily Orders</b> shows total amount of orders on a given day and compares it to the past day's.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end Daily Orders -->
                    <!-- start Daily Visitors -->
                    <div class="col-sm-6 col-lg-3">
                        <div class="card text-center">
                            <div class="card-body">
                                <h4 class="card-title text-muted">Daily Visitors</h4>
                                <h2 class="mt-3 mb-2"><i class="mdi mdi-arrow-down text-danger me-2"
                                                         id="daily-visitors-mark"></i>
                                    <b id="daily-visitors-amount">320</b>
                                </h2>
                                <p class="text-muted mb-0 mt-3"><b id="daily-visitors-percentage">21%</b> From Last
                                    24 Hours</p>
                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Daily Visitors</b> shows total amount of visitors on a given day and compares it to the past 24 hours'.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end Daily Visitors -->
                    <!-- start Daily Avg. Rating -->
                    <div class="col-sm-6 col-lg-3">
                        <div class="card text-center">
                            <div class="card-body">
                                <h4 class="card-title text-muted">Daily Avg. Rating</h4>
                                <h2 class="mt-3 mb-2"><i class="mdi mdi-arrow-up text-success me-2"
                                                         id="daily-rating-mark"></i>
                                    <b id="daily-rating-amount">4.3</b>
                                </h2>
                                <p class="text-muted mb-0 mt-3"><b id="daily-rating-percentage">20%</b> From
                                    Last 24 Hours</p>
                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Daily Avg. Rating</b> shows average rating for all dishes on a given day and compares it to the past 24 hours'.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end Daily Avg. Rating -->
                </div>
                <!-- end card only with number -->

                <!-- start card with Area Chart -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 card-title">Daily Transaction Events</h4>
                                <ul class="list-inline d-flex justify-content-around mt-3">
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><i class="fas fa-dollar-sign"></i> <b
                                                id="total-income">3654</b></h5>
                                        <p class="text-muted mb-0">Total Income</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><i class="fas fa-receipt"></i> <b
                                                id="total-orders">954</b>
                                        </h5>
                                        <p class="text-muted mb-0">Total Orders</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><i class="fas fa-user"></i> <b
                                                id="total-visitors">8462</b>
                                        </h5>
                                        <p class="text-muted mb-0">Total Visitors</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><i class="fas fa-star"></i> <b
                                                id="avg-rating">4.9</b></h5>
                                        <p class="text-muted mb-0">Avg. Rating</p>
                                    </li>
                                </ul>
                                <div class="apex-charts" dir="ltr" id="daily-transaction-events-Area-Chart"></div>
                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Daily Transaction Events</b> shows total income, amount of orders, number of visitors and average rating for all dishes during this month. Then it renders Area Chart with the normalized datasets, which illustrates the trend during the period.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end card with Area Chart -->

                <!-- start Multiple Radial Bar Charts -->
                <div class="row">
                    <!-- start card with Multiple Radial Bar Charts for breakfasts -->
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 card-title"><i class="fas fa-coffee"></i> Breakfasts Nutrition Scores
                                </h4>
                                <ul class="list-inline d-flex justify-content-around mt-3">
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="breakfasts_calorie">0.44</b> Cal</h5>
                                        <p class="mb-0 text-center nutrition-label Calorie-label">Calorie</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="breakfasts_fat">0.56</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Fat-label">Fat</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="breakfasts_protein">0.78</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Protein-label">Protein</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="breakfasts_vitamin">0.12</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Vitamin-label">Vitamin</p>
                                    </li>
                                </ul>
                                <div id="radial_chart_breakfasts" class="apex-charts" dir="ltr"
                                     style="height: 300px"></div>
                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Breakfasts Nutrition Scores</b> shows average nutrition intake for breakfast and compares it to the optimal intake by Multiple Radial Bar Chart.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end card with Multiple Radial Bar Charts for breakfasts -->

                    <!-- start card with Multiple Radial Bar Charts for lunches -->
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 card-title"><i class="fas fa-sun"></i> Lunches Nutrition Scores</h4>
                                <ul class="list-inline d-flex justify-content-around mt-3">
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="lunches_calorie">0.44</b> Cal</h5>
                                        <p class="mb-0 text-center nutrition-label Calorie-label">Calorie</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="lunches_fat">0.56</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Fat-label">Fat</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="lunches_protein">0.78</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Protein-label">Protein</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="lunches_vitamin">0.12</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Vitamin-label">Vitamin</p>
                                    </li>
                                </ul>
                                <div id="radial_chart_lunches" class="apex-charts" dir="ltr"
                                     style="height: 300px"></div>
                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Lunches Nutrition Scores</b> shows average nutrition intake for lunch and compares it to the optimal intake by Multiple Radial Bar Chart.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end card with Multiple Radial Bar Charts for lunches -->

                    <!-- start card with Multiple Radial Bar Charts for dinners -->
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 card-title"><i class="fas fa-moon"></i> Dinners Nutrition Scores</h4>
                                <ul class="list-inline d-flex justify-content-around mt-3">
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="dinners_calorie">0.44</b> Cal</h5>
                                        <p class="mb-0 text-center nutrition-label Calorie-label">Calorie</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="dinners_fat">0.56</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Fat-label">Fat</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="dinners_protein">0.78</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Protein-label">Protein</p>
                                    </li>
                                    <li class="list-inline-item">
                                        <h5 class="text-center"><b id="dinners_vitamin">0.12</b> g</h5>
                                        <p class="mb-0 text-center nutrition-label Vitamin-label">Vitamin</p>
                                    </li>
                                </ul>
                                <div id="radial_chart_dinners" class="apex-charts" dir="ltr"
                                     style="height: 300px"></div>
                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Dinners Nutrition Scores</b> shows average nutrition intake for dinner and compares it to the optimal intake by Multiple Radial Bar Chart.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end card with Multiple Radial Bar Charts for dinners -->
                </div>
                <!-- end Multiple Radial Bar Charts -->

                <!-- start Rating Progress Bars -->
                <div class="row">
                    <!-- start Rating Progress Bars: Dishes with Popularity -->
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mb-4 mt-0 card-title"><i class="fas fa-heart"></i> Dishes with Popularity
                                </h4>

                                <%-- start query --%>
                                <%
                                    int score_int;
                                    MostPopularDishes popuarDishes = new MostPopularDishes();
                                    String[] most_popular_dishes_names = popuarDishes.getMost_popular_dishes_names();
                                    String[] most_popular_dishes_scores = popuarDishes.getMost_popular_dishes_scores();
                                %>
                                <%-- end query --%>
                                <%
                                    for (int i = 0; i < most_popular_dishes_names.length; i++) {
                                        score_int = (int) Float.parseFloat(most_popular_dishes_scores[i]);
                                %>
                                <p class="font-600 mb-1"><%= most_popular_dishes_names[i] %><span
                                        class="text-primary float-end"><b><%= most_popular_dishes_scores[i] %>%</b></span>
                                </p>
                                <div class="progress  mb-3">
                                    <div class="progress-bar progress-bar-striped progress-bar-primary progress-bar-animated"
                                         role="progressbar"
                                         aria-valuenow="<%= score_int %>" aria-valuemin="0" aria-valuemax="100"
                                         style="width: <%= score_int %>%;"></div>
                                </div>
                                <% } %>

                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Dishes with Popularity</b> shows 10 dishes purchased most and their respectively high ratings.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end Rating Progress Bars: Dishes with Popularity -->

                    <!-- start Rating Progress Bars: Least Popular Dishes -->
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mb-4 mt-0 card-title"><i class="fas fa-heart-broken"></i> Least Popular
                                    Dishes</h4>

                                <%-- start query --%>
                                <%
                                    LeastPopularDishes nonPopuarDishes = new LeastPopularDishes();
                                    String[] least_popular_dishes_names = nonPopuarDishes.getLeast_popular_dishes_names();
                                    String[] least_popular_dishes_scores = nonPopuarDishes.getLeast_popular_dishes_scores();
                                %>
                                <%-- end query --%>
                                <%
                                    for (int i = 0; i < least_popular_dishes_names.length; i++) {
                                        score_int = (int) Float.parseFloat(most_popular_dishes_scores[i]);
                                %>
                                <p class="font-600 mb-1"><%= least_popular_dishes_names[i] %><span
                                        class="text-danger float-end"><b><%= least_popular_dishes_scores[i] %>%</b></span>
                                </p>
                                <div class="progress  mb-3">
                                    <div class="progress-bar progress-bar-striped progress-bar-primary bg-danger progress-bar-animated"
                                         role="progressbar"
                                         aria-valuenow="<%= least_popular_dishes_scores[i] %>" aria-valuemin="0"
                                         aria-valuemax="100"
                                         style="width: <%= least_popular_dishes_scores[i] %>%;"></div>
                                </div>
                                <% } %>

                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Least Popular Dishes</b> shows 10 dishes rated worst and their respectively low ratings.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end Rating Progress Bars: Least Popular Dishes -->
                </div>
                <!-- end Rating Progress Bars -->

                <!-- start Order Data Table -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title mb-3"><i class="fas fa-list-ul"></i> Recent Orders</h4>
                                <table id="alternative-page-datatable" class="table dt-responsive nowrap w-100">
                                    <thead>
                                    <tr>
                                        <th>UID</th>
                                        <th>Order ID</th>
                                        <th>Contents</th>
                                        <th>Time</th>
                                        <th>Consumption</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <%-- start query --%>
                                    <%
                                        OrdersData ordersData = new OrdersData();
                                        String[][] orders_data = ordersData.getOrders_data();
                                    %>
                                    <%-- end query --%>

                                    <% for (String[] data : orders_data) { %>
                                    <tr>
                                        <td><%= data[0] %>
                                        </td>
                                        <td><%= data[1] %>
                                        </td>
                                        <td><%= data[2] %>
                                        </td>
                                        <td><%= data[3] %>
                                        </td>
                                        <td><%= data[4] %>
                                        </td>
                                    </tr>
                                    <% } %>

                                    </tbody>
                                </table>

                                <div class="position-absolute top-0 end-0 mt-1 me-1" data-bs-toggle="tooltip"
                                     data-bs-placement="top" data-bs-html="true"
                                     title="<b>Recent Orders</b> shows 60 latest orders. This module also provides search box and sorting function.">
                                    <i class="fas fa-info-circle info-module text-info"></i>
                                </div>
                            </div><!-- end card body-->
                        </div><!-- end card -->
                    </div><!-- end col-->
                </div>
                <!-- end Order Data Table -->

                <!-- start footer -->
                <div class="row">
                    <div class="col text-center">
                        <span class="m-1" style="cursor: pointer;"
                              onclick="location.reload();">2022© 金鹰智慧食堂数据分析系统.</span>
                        <span class="m-1"> | <%= session.getAttribute("username") %> | </span>
                        <span class="m-1" style="cursor: pointer; text-decoration: underline"
                              onclick="location.href='Logout';">Logout!</span>
                        <%-- <div>DJX LOVE ZDX 2022.07.16</div> --%>
                    </div>
                </div>
                <!-- end footer -->
            </div>
        </div><!-- End Page-content -->
    </div>
    <!-- end main content-->
</div>
<!-- END layout-wrapper -->

<!-- jquery js -->
<script src="assets/js/jquery.min.js"></script>

<!--  bootstrap bundle js -->
<script src="assets/js/bootstrap.bundle.min.js"></script>

<!-- Required datatable js -->
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTables.bootstrap4.min.js"></script>

<!-- tooltips init -->
<script src="assets/js/tooltips.init.js"></script>

<!-- daily data init -->
<script src="assets/js/dailydata.init.js"></script>

<!-- apexcharts -->
<script src="assets/js/apexcharts.min.js"></script>
<!-- apexcharts init -->
<script src="assets/js/apexcharts.init.js"></script>

<!-- datatables init -->
<script src="assets/js/datatables.init.js"></script>
</body>

</html>