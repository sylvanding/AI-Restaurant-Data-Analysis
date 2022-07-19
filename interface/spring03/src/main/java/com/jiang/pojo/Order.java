package com.jiang.pojo;

public class Order {

    private int orderId;
    private int userId;
    private String content;
    private String time;
    private float money;

    public Order() {
    }

    public Order(int orderId, int userId, String content, String time, float money) {
        this.orderId = orderId;
        this.userId = userId;
        this.content = content;
        this.time = time;
        this.money = money;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", money=" + money +
                '}';
    }
}
