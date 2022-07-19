package com.jiang.pojo;

public class DayData {

    private int dayTotal;
    private int dayOrder;
    private int dayVisitor;
    private double dayGrade;

    public DayData() {
    }

    public DayData(int dayTotal, int dayOrder, int dayVisitor, double dayGrade) {
        this.dayTotal = dayTotal;
        this.dayOrder = dayOrder;
        this.dayVisitor = dayVisitor;
        this.dayGrade = dayGrade;
    }

    public int getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(int dayTotal) {
        this.dayTotal = dayTotal;
    }

    public int getDayOrder() {
        return dayOrder;
    }

    public void setDayOrder(int dayOrder) {
        this.dayOrder = dayOrder;
    }

    public int getDayVisitor() {
        return dayVisitor;
    }

    public void setDayVisitor(int dayVisitor) {
        this.dayVisitor = dayVisitor;
    }

    public double getDayGrade() {
        return dayGrade;
    }

    public void setDayGrade(double dayGrade) {
        this.dayGrade = dayGrade;
    }

    @Override
    public String toString() {
        return "DayData{" +
                "dayTotal=" + dayTotal +
                ", dayOrder=" + dayOrder +
                ", dayVisitor=" + dayVisitor +
                ", dayGrade=" + dayGrade +
                '}';
    }
}
