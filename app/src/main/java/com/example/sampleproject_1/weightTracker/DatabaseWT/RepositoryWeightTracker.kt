package com.example.sampleproject_1.weightTracker.DatabaseWT

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.Entry
import java.security.KeyStore

class RepositoryWeightTracker(private  var dataAccessObjectWeightTracker: DataAccessObjectWeightTracker) {

  val getUserWT:LiveData<List<EntityWeightTracker>> = dataAccessObjectWeightTracker.getAllUsersWT()

    suspend fun insert(entityWeightTracker: EntityWeightTracker) {
        dataAccessObjectWeightTracker.insert(entityWeightTracker)
    }

    suspend fun deleteAllData(){
        dataAccessObjectWeightTracker.deleteAllData()
    }


}