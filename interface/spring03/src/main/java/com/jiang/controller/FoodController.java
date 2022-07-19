package com.jiang.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.pojo.Food;
import com.jiang.service.FoodService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/FoodList")
public class FoodController {

    @Autowired
    @Qualifier("FoodServiceImpl")
    private FoodService foodService;
    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/get")
    public String getFoodList(int num,int select) throws JsonProcessingException {
        if(num < 0 || select < 0 || select >3){
            return "Parameter error";
        }else{
            List<Food> list = foodService.getFoodList(num,select);
            return objectMapper.writeValueAsString(list);
        }
    }
}
