package com.jiang.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.pojo.SexMoneyRate;
import com.jiang.service.SexMoneyRateService;
import com.jiang.service.SexMoneyRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SexRate")
public class SexMoneyRateController {

    @Autowired
    @Qualifier("SexMoneyRateServiceImpl") //实现类记得注册Bean！
    private SexMoneyRateService sexMoneyRateService;

    //男女消费水平比例
    @RequestMapping("/getRate")
    public String getRate(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SexMoneyRate man = sexMoneyRateService.getMoneyRateBySex(0); //0代表男性
        SexMoneyRate woman = sexMoneyRateService.getMoneyRateBySex(1);
        double res = man.getAverageOrderMoney()/woman.getAverageOrderMoney();
        model.addAttribute("ManVsWoman",res);
        return objectMapper.writeValueAsString(model);
    }


    //男性平均单价
    @RequestMapping("/getManAvg")
    public String getManAvg(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SexMoneyRate man = sexMoneyRateService.getMoneyRateBySex(0);
        model.addAttribute("manAvgPrice",man.getAverageOrderMoney());
        return objectMapper.writeValueAsString(model);
    }


    //女性平均单价
    @RequestMapping("/getWomanAvg")
    public String getWomanAvg(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SexMoneyRate woman = sexMoneyRateService.getMoneyRateBySex(1);
        model.addAttribute("womanAvgPrice",woman.getAverageOrderMoney());
        return objectMapper.writeValueAsString(model);
    }

}
