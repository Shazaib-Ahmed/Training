package com.example.sampleproject_1.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataAccessObjectWaterReminder {

    @Insert
    void insertUserDetail(EntityWaterReminder entityWaterReminder);

    @Query("SELECT * from water_reminder_users_details")
    public List<EntityWaterReminder> getGetAllUsers();


}
