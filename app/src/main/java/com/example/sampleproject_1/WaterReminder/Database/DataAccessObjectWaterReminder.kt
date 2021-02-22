package com.example.sampleproject_1.WaterReminder.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataAccessObjectWaterReminder {
    @Insert
    fun insert(entityWaterReminder: EntityWaterReminder?)

    @get:Query("SELECT * from water_reminder_users_stats")
    val getAllUsers: List<EntityWaterReminder?>?
}