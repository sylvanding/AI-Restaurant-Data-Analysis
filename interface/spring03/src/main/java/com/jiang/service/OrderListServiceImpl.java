package com.jiang.service;

import com.jiang.mapper.OrderMapper;
import com.jiang.pojo.Order;

import java.util.List;

public class OrderListServiceImpl implements OrderListService{

    private OrderMapper orderMapper;

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> getList() {
        return orderMapper.getOrderListByPage();
    }
}
