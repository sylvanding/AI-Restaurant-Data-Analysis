package com.jiang.service;

import com.jiang.mapper.FoodMapper;
import com.jiang.pojo.Food;

import java.util.List;

public class FoodServiceImpl implements FoodService{

    private FoodMapper foodMapper;

    public void setFoodMapper(FoodMapper foodMapper) {
        this.foodMapper = foodMapper;
    }

    @Override
    public List<Food> getFoodList(int num, int select) {

        //select为0返回评分最高
        //select为1返回评分最低
        //select为2返回销量最高
        //select为3返回销量最低
        if(select == 0){
            return foodMapper.getBestFood(num);
        }else if(select == 1){
            return foodMapper.getWorstFood(num);
        }else if(select == 2){
            return foodMapper.getMostFood(num);
        }else{
            return foodMapper.getLeastFood(num);
        }
    }

}
