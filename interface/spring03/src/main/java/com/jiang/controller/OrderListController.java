package com.jiang.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderListController {
    @Autowired
    @Qualifier("OrderListServiceImpl")
    private OrderListService orderListService;
    ObjectMapper objectMapper = new ObjectMapper();


    @RequestMapping("/getOrderList")
    public String getList() throws JsonProcessingException {
        return objectMapper.writeValueAsString(orderListService.getList());
    }

}
