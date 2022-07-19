package com.jiang.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.pojo.AvgNutritionVo;
import com.jiang.pojo.DayData;
import com.jiang.service.DayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/GetDay")
public class DayDataController {

    @Autowired
    @Qualifier("DayDataServiceImpl")
    private DayDataService dayDataService;
    ObjectMapper objectMapper = new ObjectMapper();

    //返回某天总营业额
    @RequestMapping("/totalMoney")
    public String getTotalMoney(int day, Model model) throws JsonProcessingException {
        if(day < 1 || day > 30){
            return "参数错误";
        }
        DayData res = dayDataService.getDayTotal(day);
        model.addAttribute("totalMoney",res.getDayTotal());
        return objectMapper.writeValueAsString(model);
    }

    //返回某天总人流
    @RequestMapping("/totalVisitor")
    public String getTotalVisitor(int day, Model model) throws JsonProcessingException {
        if(day < 1 || day > 30){
            return "参数错误";
        }
        DayData res = dayDataService.getDayTotal(day);
        model.addAttribute("totalVisitor",res.getDayVisitor());
        return objectMapper.writeValueAsString(model);
    }

    //返回某天总订单数
    @RequestMapping("/totalOrder")
    public String getTotalOrder(int day, Model model) throws JsonProcessingException {
        if(day < 1 || day > 30){
            return "参数错误";
        }
        DayData res = dayDataService.getDayTotal(day);
        model.addAttribute("totalOrder",res.getDayOrder());
        return objectMapper.writeValueAsString(model);
    }

    //返回某天平均评分
    @RequestMapping("/DayMark")
    public String getDayMark(int day,Model model) throws JsonProcessingException {
        if(day < 1 || day > 30){
            return "参数错误";
        }
        double res = dayDataService.getDayMark(day);
        model.addAttribute("dayAvgMark",res);
        return objectMapper.writeValueAsString(model);
    }

    //该接口已被耦合
    /*//返回30天总营业额
    @RequestMapping("/MonthMoney")
    public String getMonthTotalMoney(Model model) throws JsonProcessingException {
        model.addAttribute("MonthMoney",dayDataService.getMonthTotalMoney());
        return objectMapper.writeValueAsString(model);
    }*/

    //按前端要求耦合所有数据为一个接口
    @RequestMapping("/totalData")
    public String getTotalData(int day, Model model) throws JsonProcessingException {
        DayData data1 = dayDataService.getDayTotal(day);
        model.addAttribute("totalMoney",data1.getDayTotal());
        model.addAttribute("totalVisitor",data1.getDayVisitor());
        model.addAttribute("totalOrder",data1.getDayOrder());
        model.addAttribute("dayAvgMark",dayDataService.getDayMark(day));
        if(day == 1){
            model.addAttribute("moneyRate",1);
            model.addAttribute("orderRate",1);
            model.addAttribute("visitorRate",1);
            model.addAttribute("markRate",1);
            return objectMapper.writeValueAsString(model);
        }
        DayData data2 = dayDataService.getDayTotal(day-1);
        double rate1 = (double) data1.getDayTotal()/data2.getDayTotal()-1;
        double rate2 = (double) data1.getDayOrder()/data2.getDayOrder()-1;
        double rate3 = (double) data1.getDayVisitor()/data2.getDayVisitor()-1;
        double rate4 =  dayDataService.getDayMark(day)/dayDataService.getDayMark(day-1)-1;
        model.addAttribute("moneyRate",rate1);
        model.addAttribute("orderRate",rate2);
        model.addAttribute("visitorRate",rate3);
        model.addAttribute("markRate",rate4);

        return objectMapper.writeValueAsString(model);
    }

    @RequestMapping("/MonthTotalData")
    public String getMonthTotalData(Model model) throws JsonProcessingException {
        DayData res = dayDataService.getMonthTotalData();
        model.addAttribute("totalMoney",res.getDayTotal());
        model.addAttribute("totalVisitor",res.getDayVisitor());
        model.addAttribute("totalOrder",res.getDayOrder());
        model.addAttribute("avgMark",res.getDayGrade());
        return objectMapper.writeValueAsString(model);
    }

    @RequestMapping("/AvgNutrition")
    public String getAvgNutrition() throws JsonProcessingException {
        List<AvgNutritionVo> res = dayDataService.getAvgNutrition();
        return objectMapper.writeValueAsString(res);
    }

    //30天每天的DayData数据
    @RequestMapping("/MonthEvrData")
    public String getMonthEverydayData() throws JsonProcessingException {
        return dayDataService.getEvrDay();
    }
}
