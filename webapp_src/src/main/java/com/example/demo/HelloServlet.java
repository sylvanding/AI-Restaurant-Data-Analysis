package com.example.demo;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "丁纪翔 1925102007 | 骆华钦 1925103027 | 陈鑫 1925103005 | 梁恺恒 1925103021 | 姜宇晨 1925102018";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> AI-Restaurant Dashboard </h1>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}