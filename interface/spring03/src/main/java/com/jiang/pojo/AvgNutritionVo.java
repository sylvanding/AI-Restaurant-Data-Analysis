package com.jiang.pojo;

public class AvgNutritionVo {

    private String timePeriod;
    private double Calorie;
    private double Fat;
    private double Protein;
    private double Vitamin;

    public AvgNutritionVo() {
    }

    public AvgNutritionVo(String timePeriod, double calorie, double fat, double protein, double vitamin) {
        this.timePeriod = timePeriod;
        Calorie = calorie;
        Fat = fat;
        Protein = protein;
        Vitamin = vitamin;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public double getCalorie() {
        return Calorie;
    }

    public void setCalorie(double calorie) {
        Calorie = calorie;
    }

    public double getFat() {
        return Fat;
    }

    public void setFat(double fat) {
        Fat = fat;
    }

    public double getProtein() {
        return Protein;
    }

    public void setProtein(double protein) {
        Protein = protein;
    }

    public double getVitamin() {
        return Vitamin;
    }

    public void setVitamin(double vitamin) {
        Vitamin = vitamin;
    }

    @Override
    public String toString() {
        return "AvgNutritionVo{" +
                "timePeriod='" + timePeriod + '\'' +
                ", Calorie=" + Calorie +
                ", Fat=" + Fat +
                ", Protein=" + Protein +
                ", Vitamin=" + Vitamin +
                '}';
    }
}
