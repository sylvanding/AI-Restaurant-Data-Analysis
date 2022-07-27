package com.example.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLogin", value = "/UserLogin")
public class UserLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null)
            response.sendRedirect("login.jsp");
        UserDao userDao = new UserDao(username, password);
        int valid = userDao.isValid();
        if (valid == 2) {
            session.setAttribute("username", username);
            response.sendRedirect("index.jsp");
        } else {
            switch (valid) {
                case 0:
                    session.setAttribute("msg", "User doesn't exist!");
                    break;
                case 1:
                    session.setAttribute("msg", "Your password is wrong!");
                    break;
                default:
                    session.setAttribute("msg", "ERROR!");
            }
            response.sendRedirect("login.jsp");
        }
    }
}
