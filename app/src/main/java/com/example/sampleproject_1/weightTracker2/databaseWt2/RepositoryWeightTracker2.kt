package com.example.sampleproject_1.weightTracker2.databaseWt2

import androidx.lifecycle.LiveData


class RepositoryWeightTracker2(private  var dataAccessObjectWeightTracker2: DataAccessObjectWeightTracker2) {

    val getUserWT2: LiveData<List<EntityWeightTracker2>> = dataAccessObjectWeightTracker2.getAllUsersWT2()

    suspend fun insert(entityWeightTracker2: EntityWeightTracker2) {
        dataAccessObjectWeightTracker2.insert(entityWeightTracker2)
    }

    suspend fun deleteAllData(){
        dataAccessObjectWeightTracker2.deleteAllData()
    }


}