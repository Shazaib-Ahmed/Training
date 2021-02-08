package com.example.sampleproject_1.WaterReminder.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "water_reminder_users_details")
public class EntityWaterReminder {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String gender;

    private int weight;

    private String bedTime;

    private String wakeUpTime;

    public EntityWaterReminder(String gender, int weight, String bedTime, String wakeUpTime) {
        this.gender = gender;
        this.weight = weight;
        this.bedTime = bedTime;
        this.wakeUpTime = wakeUpTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public int getWeight() {
        return weight;
    }

    public String getBedTime() {
        return bedTime;
    }

    public String getWakeUpTime() {
        return wakeUpTime;
    }
}
