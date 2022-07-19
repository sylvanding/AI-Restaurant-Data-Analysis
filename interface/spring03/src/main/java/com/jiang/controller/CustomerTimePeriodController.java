package com.jiang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.pojo.CustomerTimePeriod;
import com.jiang.service.CustomerTimePeriodService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/CusTime")
public class CustomerTimePeriodController {

    @Autowired
    @Qualifier("CustomerTimePeriodServiceImpl")
    private CustomerTimePeriodService customerTimePeriodService;
    ObjectMapper objectMapper = new ObjectMapper();

    //返回某段时间内 每个时间段人数
    @RequestMapping("/api1")
    public String getCusTime(String startTime, String endTime, int n, Model module) throws JsonProcessingException {
        List<CustomerTimePeriod> list = customerTimePeriodService.getPeopleTimeMap(startTime,endTime,n);
        list.sort(Comparator.comparing(CustomerTimePeriod::getTimePeriod));
        return objectMapper.writeValueAsString(list);
    }

    //返回某天早中晚人流量
    @RequestMapping("/getThreeNum")
    public String getThreeNum(int n,Model model) throws JsonProcessingException {
        HashMap<String,Object> res = customerTimePeriodService.getThreeTimeMap(n);
        res.remove("time");
        return objectMapper.writeValueAsString(res);
    }

}
