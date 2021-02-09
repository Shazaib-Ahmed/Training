package com.example.sampleproject_1.WaterReminder.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataAccessObjectWaterReminder {

    @Insert
    void insert(EntityWaterReminder entityWaterReminder);

    @Query("SELECT * from water_reminder_users_stats")
    LiveData<List<EntityWaterReminder>> getGetAllUsers();

}
