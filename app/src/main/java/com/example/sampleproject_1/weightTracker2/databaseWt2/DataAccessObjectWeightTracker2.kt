package com.example.sampleproject_1.weightTracker2.databaseWt2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataAccessObjectWeightTracker2 {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityWeightTracker2: EntityWeightTracker2)

    /*@Query("SELECT * from weight_tracker_user_stats")
    fun getAllUsersWT(): LiveData<List<EntityWeightTracker>>*/

    @Query("SELECT * from weight_tracker_2_user_stats")
    fun getAllUsersWT2(): LiveData<List<EntityWeightTracker2>>

    @Query("DELETE from weight_tracker_2_user_stats")
    suspend fun deleteAllData()
}