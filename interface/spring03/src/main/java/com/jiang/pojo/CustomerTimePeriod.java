package com.jiang.pojo;

public class CustomerTimePeriod {

    private String timePeriod;
    private int num;

    public CustomerTimePeriod() {
    }

    public CustomerTimePeriod(String time, int num) {
        this.timePeriod = time;
        this.num = num;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CustomerTimePeriod{" +
                "time='" + timePeriod + '\'' +
                ", num=" + num +
                '}';
    }
}
