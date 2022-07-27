import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class jsonToInt_UserDao {
    public static void main(String[] args) throws IOException {
        int valid;
        String user_name="1583896900";
        String user_psw="123456";

        String host_name = "http://175.178.233.192:8080/";
        URL url = new URL(host_name + "spring/Login?username=" + user_name + "&password=" + user_psw);
        System.out.println(url);
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
        valid = Integer.parseInt(jsonObject.get("msg") + "");
        System.out.println(valid);
    }
}
