package com.example.sampleproject_1.WaterReminder.model;

public class WaterIntake {
    public final String time;
    public final int quantity;

    public WaterIntake(String time, int quantity) {
        this.time = time;
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public int getQuantity() {
        return quantity;
    }
}
