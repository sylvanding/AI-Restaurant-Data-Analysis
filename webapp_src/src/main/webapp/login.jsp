<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% if (session.getAttribute("username") != null) response.sendRedirect("index.jsp"); %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>AI-Restaurant Dashboard Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <!-- loginPage.css -->
    <link href="assets/css/loginPage.css" rel="stylesheet" type="text/css" media="all">
</head>

<body>

<!-- main -->
<div class="main-w3layouts wrapper">
    <div class="main-agileinfo">
        <div class="agileits-top">
            <form action="UserLogin" method="post">
                <label>
                    <input class="text" type="text" name="username" placeholder="Username" required="">
                    <input class="text" type="password" name="password" placeholder="Password" required="">
                </label>
                <div class="wthree-text">
                    <ul>
                        <%
                            String msg = (String) session.getAttribute("msg");
                            if (msg != null) {
                        %>
                        <li><a href=""><%= msg %>
                        </a></li>
                        <%
                                session.removeAttribute("msg");
                            }
                        %>
                    </ul>
                    <div class="clear"></div>
                </div>
                <input type="submit" value="Login">
            </form>
            <p><a href="">2022© 金鹰智慧食堂数据分析系统.</a></p>
        </div>
    </div>

    <!-- //copyright -->
    <ul class="w3lsg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>
<!-- //main -->

</body>
</html>