package com.example.sampleproject_1.weightTracker.DatabaseWT

import androidx.lifecycle.LiveData

class RepositoryWeightTracker(private val dataAccessObjectWeightTracker: DataAccessObjectWeightTracker) {

    val getUserWT: LiveData<List<EntityWeightTracker>> = dataAccessObjectWeightTracker.getAllUsersWT()

    suspend fun insert(entityWeightTracker: EntityWeightTracker) {
        dataAccessObjectWeightTracker.insert(entityWeightTracker)
    }

    suspend fun deleteAllData(){
        dataAccessObjectWeightTracker.deleteAllData()
    }

}