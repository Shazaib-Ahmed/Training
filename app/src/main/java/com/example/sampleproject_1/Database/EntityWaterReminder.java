package com.example.sampleproject_1.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "water_reminder_users_details")
public class EntityWaterReminder {
    public EntityWaterReminder() {

    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    private String gender;

    private String weight;

    private String bedTime;

    private String wakeUpTime;

    public EntityWaterReminder(String gender, String weight, String bedTime, String wakeUpTime) {
        this.gender = gender;
        this.weight = weight;
        this.bedTime = bedTime;
        this.wakeUpTime = wakeUpTime;
    }

    public String getGender() {
        return gender;
    }

    public String getWeight() {
        return weight;
    }

    public String getBedTime() {
        return bedTime;
    }

    public String getWakeUpTime() {
        return wakeUpTime;
    }
    public int getId() {
        return id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setBedTime(String bedTime) {
        this.bedTime = bedTime;
    }

    public void setWakeUpTime(String wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }
}
