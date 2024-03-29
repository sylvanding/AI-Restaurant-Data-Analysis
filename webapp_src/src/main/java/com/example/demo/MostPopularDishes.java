package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class MostPopularDishes {
    String[] most_popular_dishes_names;
    String[] most_popular_dishes_scores;

    public MostPopularDishes() throws IOException {
        String host_name = "http://175.178.233.192:8080/";
        URL url = new URL(host_name + "spring/FoodList/get?num=10&select=0");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();
        StringBuilder msg = new StringBuilder();
        if (code == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                msg.append(line).append("\n");
            }
            reader.close();
        }
        connection.disconnect();
        JSONArray jsonArray = JSONObject.parseArray(String.valueOf(msg));
        this.most_popular_dishes_names = new String[jsonArray.size()];
        this.most_popular_dishes_scores = new String[jsonArray.size()];
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        for (int i = 0; i < jsonArray.size(); i++) {
            String str = jsonArray.get(i) + "";
            JSONObject jsonObject = JSONObject.parseObject(str);
            most_popular_dishes_names[i] = jsonObject.get("name") + "";
            most_popular_dishes_scores[i] = decimalFormat.format((Float.parseFloat(jsonObject.get("mark") + "") * 10));
        }
    }

    public String[] getMost_popular_dishes_names() {
        return most_popular_dishes_names;
    }

    public String[] getMost_popular_dishes_scores() {
        return most_popular_dishes_scores;
    }
}
