package com.example.sampleproject_1.weightTracker2.databaseWt2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelWeightTracker2 (application: Application) : AndroidViewModel(application) {

    val getAllUserWT2: LiveData<List<EntityWeightTracker2>>



    private val repositoryWeightTracker2: RepositoryWeightTracker2

    init {
        val dataAccessObjectWeightTracker2 = DatabaseWeightTracker2.getInstance(application)?.dataAccessObjectWeightTracker2()
        repositoryWeightTracker2 = RepositoryWeightTracker2(dataAccessObjectWeightTracker2!!)
        getAllUserWT2 = repositoryWeightTracker2.getUserWT2

    }

    fun insert(entityWeightTracker2: EntityWeightTracker2) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryWeightTracker2.insert(entityWeightTracker2)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryWeightTracker2.deleteAllData()
        }
    }




}