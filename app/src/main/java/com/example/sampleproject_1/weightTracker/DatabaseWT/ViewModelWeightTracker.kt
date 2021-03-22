package com.example.sampleproject_1.weightTracker.DatabaseWT

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelWeightTracker(application: Application) : AndroidViewModel(application) {

    val getAllUserWT: LiveData<List<EntityWeightTracker>>

    private val repositoryWeightTracker: RepositoryWeightTracker

    init {
        val dataAccessObjectWeightTracker = DatabaseWeightTracker.getInstance(application)?.dataAccessObjectWeightTracker()
        repositoryWeightTracker = RepositoryWeightTracker(dataAccessObjectWeightTracker!!)
        getAllUserWT = repositoryWeightTracker.getUserWT
    }

    fun insert(entityWeightTracker: EntityWeightTracker) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryWeightTracker.insert(entityWeightTracker)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryWeightTracker.deleteAllData()
        }
    }


}