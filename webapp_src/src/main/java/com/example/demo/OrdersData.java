package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class OrdersData {
    String[][] orders_data;

    public OrdersData() throws IOException {
        String host_name = "http://175.178.233.192:8080/";
        URL url = new URL(host_name + "spring/getOrderList");
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
        this.orders_data = new String[jsonArray.size()][5];
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        for (int i = 0; i < jsonArray.size(); i++) {
            String str = jsonArray.get(i) + "";
            JSONObject jsonObject = JSONObject.parseObject(str);
            orders_data[i][0] = jsonObject.get("userId") + "";
            orders_data[i][1] = jsonObject.get("orderId") + "";
            orders_data[i][2] = jsonObject.get("content") + "";
            orders_data[i][3] = jsonObject.get("time") + "";
            orders_data[i][3] = orders_data[i][3].substring(0, orders_data[i][3].length() - 2);
            orders_data[i][4] = "$" + decimalFormat.format((Float.parseFloat(jsonObject.get("money") + "")));
        }
    }

    public String[][] getOrders_data() {
        return orders_data;
    }
}
