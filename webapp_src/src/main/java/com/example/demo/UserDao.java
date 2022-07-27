package com.example.demo;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserDao {
    int valid;
    String user_name;
    String user_psw;

    public UserDao(String user_name, String user_psw) {
        this.valid = 0;
        this.user_name = user_name;
        this.user_psw = user_psw;
    }

    public int isValid() throws IOException {
        String host_name = "http://175.178.233.192:8080/";
        URL url = new URL(host_name + "spring/Login?username=" + this.user_name + "&password=" + this.user_psw);
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
        JSONObject jsonObject = JSONObject.parseObject(String.valueOf(msg));
        this.valid = Integer.parseInt(jsonObject.get("msg") + "");
        return this.valid;
    }
}
