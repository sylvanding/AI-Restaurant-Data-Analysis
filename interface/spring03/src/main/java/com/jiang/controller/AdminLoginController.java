package com.jiang.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AdminLoginController {

    @Autowired
    @Qualifier("AdminLoginServiceImpl")
    private AdminLoginService adminLoginService;
    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/Login")
    public String adminLogin(String username, String password, HttpServletRequest request, HttpServletResponse response, Model model) throws JsonProcessingException {
        model.addAttribute("msg",adminLoginService.login(username,password,request,response));
        return objectMapper.writeValueAsString(model);

    }

    @RequestMapping("/isLogin")
    public String testCookie(HttpServletRequest request, HttpServletResponse response,Model model) throws JsonProcessingException {
        model.addAttribute("msg",adminLoginService.getUserByCookie(request,response));
        return objectMapper.writeValueAsString(model);
    }
}
