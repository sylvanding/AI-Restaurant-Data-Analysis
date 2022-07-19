package com.jiang.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiang.pojo.AvgNutritionVo;
import com.jiang.pojo.DayData;

import java.util.HashMap;
import java.util.List;

public interface DayDataService {

    DayData getDayTotal(int day);

    double getDayMark(int day);

    double getMonthTotalMoney();

    DayData getMonthTotalData();

    List<AvgNutritionVo> getAvgNutrition();

    String getEvrDay() throws JsonProcessingException;
}
