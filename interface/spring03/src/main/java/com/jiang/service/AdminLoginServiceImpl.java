package com.jiang.service;

import com.jiang.mapper.AdminLoginMapper;
import com.jiang.pojo.Admin;
import com.jiang.utils.CookieUtil;
import com.jiang.utils.MD5util;
import com.jiang.utils.RedisUtil;
import com.jiang.utils.UUIDUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginServiceImpl implements AdminLoginService{


    @Resource
    private RedisUtil redisUtil;
    private AdminLoginMapper adminLoginMapper;

    public void setAdminLoginMapper(AdminLoginMapper adminLoginMapper) {
        this.adminLoginMapper = adminLoginMapper;
    }

    @Override
    public int login(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        //根据用户名获取用户
        Admin admin = adminLoginMapper.getAdminByUsername(username);
        if(null == admin){
            //用户不存在
            return 0;
        }
        //密码校验
        if(!MD5util.formPassToDBPass(password,admin.getSalt()).equals(admin.getPassword())){
            //密码错误
            return 1;
        }
        //生成Cookie
        String ticket = UUIDUtil.uuid();
        //Session存入redis
        if(redisUtil.hasKey(username)){
            //单点登录需删除用户已有Session
            redisUtil.del((String) redisUtil.get(username));
            redisUtil.del(username);
        }
        redisUtil.set(ticket,username,2000);
        redisUtil.set(username,ticket,2000);
        //写入Cookie
        CookieUtil.setCookie(request,response,"ticket",ticket,-1);
        //登陆成功
        return 2;
    }

    @Override
    public int getUserByCookie(HttpServletRequest request, HttpServletResponse response) {
        String Cookie = CookieUtil.getCookieValue(request,"ticket");
        if(redisUtil.hasKey(Cookie)){
            //重置缓存失效时间
            redisUtil.expire(Cookie,2000);
            redisUtil.expire((String) redisUtil.get(Cookie),2000);
            //0表示已登录状态
            return 0;
        }
        //1表示未登录状态，前端需重定向登录页
        return 1;
    }
}
