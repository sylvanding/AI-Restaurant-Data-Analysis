package com.jiang.mapper;

import com.jiang.pojo.Food;

import java.util.List;

public interface FoodMapper {

    //获取口碑最好的num道菜
    List<Food> getBestFood(int num);

    //获取口碑最差的n道菜
    List<Food> getWorstFood(int num);

    //获取卖出数量最多的num道菜
    List<Food> getMostFood(int num);

    //获取卖出数量最少的num道菜
    List<Food> getLeastFood(int num);
}
