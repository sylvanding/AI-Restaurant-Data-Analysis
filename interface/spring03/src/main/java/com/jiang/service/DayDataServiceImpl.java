package com.jiang.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.mapper.DayDataMapper;
import com.jiang.pojo.AvgNutritionVo;
import com.jiang.pojo.DayData;

import java.util.HashMap;
import java.util.List;

public class DayDataServiceImpl implements DayDataService{

    private DayDataMapper dayDataMapper;
    ObjectMapper objectMapper = new ObjectMapper();

    public void setDayDataMapper(DayDataMapper dayDataMapper) {
        this.dayDataMapper = dayDataMapper;
    }

    @Override
    public DayData getDayTotal(int day){
        return dayDataMapper.getDayTotal(day);
    }

    @Override
    public double getDayMark(int day) {
        return dayDataMapper.getDayMark(day);
    }

    @Override
    public double getMonthTotalMoney() {
        return dayDataMapper.getMonthTotalMoney();
    }

    @Override
    public DayData getMonthTotalData() {
        DayData res = new DayData();
        res.setDayTotal(dayDataMapper.getMonthTotalMoney());
        res.setDayOrder(dayDataMapper.getMonthTotalOrder());
        res.setDayVisitor(dayDataMapper.getMonthTotalVisitor());
        res.setDayGrade((int)dayDataMapper.getMonthAvgGrade());
        return res;
    }

    @Override
    public List<AvgNutritionVo> getAvgNutrition() {
        return dayDataMapper.getAvgNutrition();
    }

    @Override
    public String getEvrDay() throws JsonProcessingException {
        DayData[] list = new DayData[30];
        for (int i = 0; i < 30; i++) {
            list[i] = dayDataMapper.getDayTotal(i+1);
        }
        for (int i = 0; i < 30; i++) {
            list[i].setDayGrade(dayDataMapper.getDayMark(i+1));
        }
        return objectMapper.writeValueAsString(list);
    }

}
