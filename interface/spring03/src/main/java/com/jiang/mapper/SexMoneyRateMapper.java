package com.jiang.mapper;

import com.jiang.pojo.SexMoneyRate;
import org.apache.ibatis.annotations.Param;

public interface SexMoneyRateMapper {

    //按性别获取平均单价
    SexMoneyRate getMoneyRateBySex(@Param("sex") int sex);

}
