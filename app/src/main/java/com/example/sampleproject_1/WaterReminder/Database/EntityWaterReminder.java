package com.example.sampleproject_1.WaterReminder.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "water_reminder_users_stats")
public class EntityWaterReminder {

    @PrimaryKey(autoGenerate = true)
    private int KEY_ID;

    public EntityWaterReminder() {

    }

    public int getKEY_ID() {
        return KEY_ID;
    }

    private String KEY_DATE;

    private int KEY_INTOOK;

    public int getKEY_INTOOK() {
        return KEY_INTOOK;
    }

    private int KEY_TOTAL_INTAKE;

    public void setKEY_ID(int KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    public EntityWaterReminder(String KEY_DATE, int KEY_INTOOK, int KEY_TOTAL_INTAKE) {
        this.KEY_DATE = KEY_DATE;
        this.KEY_INTOOK = KEY_INTOOK;
        this.KEY_TOTAL_INTAKE = KEY_TOTAL_INTAKE;
    }

    public String getKEY_DATE() {
        return KEY_DATE;
    }

    public int getKEY_INTOOK(String date) {
        return KEY_INTOOK;
    }

    public int getKEY_TOTAL_INTAKE() {
        return KEY_TOTAL_INTAKE;
    }
}
