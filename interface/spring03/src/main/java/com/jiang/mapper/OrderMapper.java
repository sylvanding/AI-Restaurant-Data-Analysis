package com.jiang.mapper;

import com.jiang.pojo.Order;

import java.util.List;

public interface OrderMapper {

    List<Order> getOrderListByPage();
}
