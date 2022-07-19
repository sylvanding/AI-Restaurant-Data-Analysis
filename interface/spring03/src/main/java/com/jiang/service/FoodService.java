package com.jiang.service;

import com.jiang.pojo.Food;

import java.util.List;

public interface FoodService {

    List<Food> getFoodList(int num,int select);

}
