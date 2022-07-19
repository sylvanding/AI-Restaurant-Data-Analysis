package com.jiang.service;

import com.jiang.mapper.CustomerTimePeriodMapper;
import com.jiang.pojo.CustomerTimePeriod;

import java.util.HashMap;
import java.util.List;

public class CustomerTimePeriodServiceImpl implements CustomerTimePeriodService{

    private CustomerTimePeriodMapper customerTimePeriodMapper;

    public void setCustomerTimePeriodMapper(CustomerTimePeriodMapper customerTimePeriodMapper) {
        this.customerTimePeriodMapper = customerTimePeriodMapper;
    }

    @Override
    public List<CustomerTimePeriod> getPeopleTimeMap(String beginTime, String endTime, int n) {

        return customerTimePeriodMapper.getPeopleTimeMap(beginTime,endTime,n);

    }

    @Override
    public HashMap<String, Object> getThreeTimeMap(int n) {
        return customerTimePeriodMapper.getThreeTimeMap(n);
    }

}
