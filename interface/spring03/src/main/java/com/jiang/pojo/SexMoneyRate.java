package com.jiang.pojo;

public class SexMoneyRate {

    private int sex;
    private double averageOrderMoney;

    public SexMoneyRate() {
    }

    public SexMoneyRate(int sex, double averageOrderMoney) {
        this.sex = sex;
        this.averageOrderMoney = averageOrderMoney;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getAverageOrderMoney() {
        return averageOrderMoney;
    }

    public void setAverageOrderMoney(double averageOrderMoney) {
        this.averageOrderMoney = averageOrderMoney;
    }

    @Override
    public String toString() {
        return "SexMoneyRate{" +
                "sex=" + sex +
                ", averageOrderMoney=" + averageOrderMoney +
                '}';
    }
}
