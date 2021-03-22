package com.example.sampleproject_1.weightTracker.DatabaseWT

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sampleproject_1.WaterReminder.Database.EntityWaterReminder

@Dao
interface DataAccessObjectWeightTracker {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityWeightTracker: EntityWeightTracker)

    /*@Query("SELECT * from weight_tracker_user_stats")
    fun getAllUsersWT(): LiveData<List<EntityWeightTracker>>*/

    @Query("SELECT * from weight_tracker_user_stats")
    fun getAllUsersWT(): LiveData<List<EntityWeightTracker>>

    @Query("DELETE from weight_tracker_user_stats")
    suspend fun deleteAllData()
}