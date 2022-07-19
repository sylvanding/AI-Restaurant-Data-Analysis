package com.jiang.service;

import com.jiang.pojo.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminLoginService {

    int login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    int getUserByCookie(HttpServletRequest request, HttpServletResponse response);
}
