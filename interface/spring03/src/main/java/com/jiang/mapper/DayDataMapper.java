package com.jiang.mapper;

import com.jiang.pojo.AvgNutritionVo;
import com.jiang.pojo.DayData;

import java.util.HashMap;
import java.util.List;

public interface DayDataMapper {

    //获取某一天营业额、订单量、顾客数(平均评分置-1)
    DayData getDayTotal(int day);

    //获取某一天平均评分
    double getDayMark(int day);

    //月流水
    int getMonthTotalMoney();

    //月订单
    int getMonthTotalOrder();

    //月人流
    int getMonthTotalVisitor();

    //月均分
    double getMonthAvgGrade();

    //三餐营养均值
    List<AvgNutritionVo> getAvgNutrition();
}
