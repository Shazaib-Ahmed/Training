package com.example.sampleproject_1.WaterReminder.Utils;

public class DailyWaterIntakeData {
    String days;
    float percentage;

    public DailyWaterIntakeData(String days, float percentage) {
        this.days = days;
        this.percentage = percentage;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
