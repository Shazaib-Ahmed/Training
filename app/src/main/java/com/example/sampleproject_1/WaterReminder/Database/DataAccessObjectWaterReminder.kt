package com.example.sampleproject_1.WaterReminder.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DataAccessObjectWaterReminder {


   /* @Insert
     fun insert(entityWaterReminder: EntityWaterReminder?)

     @get:Query("SELECT * from water_reminder_users_stats")
     val getAllUsers: List<EntityWaterReminder?>?*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityWaterReminder: EntityWaterReminder)

    @Query("SELECT * from water_reminder_users_stats")
    fun getAllUsers(): LiveData<List<EntityWaterReminder>>
}