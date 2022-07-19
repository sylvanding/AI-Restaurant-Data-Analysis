package com.jiang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.utils.RedisUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class testLink {

    @Resource
    private RedisUtil redisUtil;
    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/hello")
    public String hello(int a,int b,Model model){
        int c = a + b;
        model.addAttribute("老王",c);
        return model.toString();
    }

    @RequestMapping("/test")
    public String test(@RequestParam("xyz") int a, Model model) throws JsonProcessingException {
        String s = "姜宇晨" + a;
        model.addAttribute("aa",s);
        return objectMapper.writeValueAsString(model);
    }

    //redis set测试
    @RequestMapping("/redis")
    public String testRedis(){
        if(!redisUtil.hasKey("motherFucker")){
            redisUtil.set("motherFucker","安倍晋三",1000);
            return "0";
        }
        return "1";
    }

    //redis get测试
    @RequestMapping("/getRedis")
    public String getRedis() throws JsonProcessingException {
        return objectMapper.writeValueAsString(redisUtil.get("motherFucker"));
    }

}
