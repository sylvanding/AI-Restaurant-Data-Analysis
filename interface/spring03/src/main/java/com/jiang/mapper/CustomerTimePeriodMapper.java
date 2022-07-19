package com.jiang.mapper;

import com.jiang.pojo.CustomerTimePeriod;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface CustomerTimePeriodMapper {

    //按自定分钟段统计餐厅人数
    List<CustomerTimePeriod> getPeopleTimeMap(@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("n") int n);

    //获取某天早中晚顾客数
    HashMap<String,Object> getThreeTimeMap(int n);
}
