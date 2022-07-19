package com.jiang.service;

import com.jiang.mapper.SexMoneyRateMapper;
import com.jiang.pojo.SexMoneyRate;

public class SexMoneyRateServiceImpl implements SexMoneyRateService{

    private SexMoneyRateMapper sexMoneyRateMapper;

    public void setSexMoneyRateMapper(SexMoneyRateMapper sexMoneyRateMapper) {
        this.sexMoneyRateMapper = sexMoneyRateMapper;
    }

    @Override
    public SexMoneyRate getMoneyRateBySex(int sex) {
        return sexMoneyRateMapper.getMoneyRateBySex(sex);
    }

}
