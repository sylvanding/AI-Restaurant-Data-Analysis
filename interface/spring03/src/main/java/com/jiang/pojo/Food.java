package com.jiang.pojo;

public class Food {

    private int id;
    private String name;
    private int num;
    private double mark;

    public Food() {
    }

    public Food(int id, String name, int num, double mark) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", mark=" + mark +
                '}';
    }
}
