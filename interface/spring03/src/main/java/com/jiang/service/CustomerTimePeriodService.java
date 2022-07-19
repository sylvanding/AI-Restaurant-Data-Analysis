package com.jiang.service;

import com.jiang.pojo.CustomerTimePeriod;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface CustomerTimePeriodService {

    List<CustomerTimePeriod> getPeopleTimeMap(String beginTime,String endTime,int n);

    HashMap<String,Object> getThreeTimeMap(int n);
}
