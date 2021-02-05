package com.example.sampleproject_1.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "water_reminder_users_details")
public class EntityWaterReminder {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "weight")
    private String weight;

    @ColumnInfo(name = "bedTime")
    private String bedTime;

    @ColumnInfo(name = "wakeUpTime")
    private String wakeUpTime;

    public EntityWaterReminder() {
        this.gender = gender;
        this.weight = weight;
        this.bedTime = bedTime;
        this.wakeUpTime = wakeUpTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBedTime() {
        return bedTime;
    }

    public void setBedTime(String bedTime) {
        this.bedTime = bedTime;
    }

    public String getWakeUpTime() {
        return wakeUpTime;
    }

    public void setWakeUpTime(String wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }

}
