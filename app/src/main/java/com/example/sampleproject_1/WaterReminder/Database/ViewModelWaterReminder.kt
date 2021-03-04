package com.example.sampleproject_1.WaterReminder.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ViewModelWaterReminder(application: Application) :AndroidViewModel(application) {

    /* private val repository: RepositoryWaterReminder = RepositoryWaterReminder(application)
    fun insert(entityWaterReminder: EntityWaterReminder?) {
        repository.insert(entityWaterReminder)
    }

    val allData: LiveData<List<EntityWaterReminder?>?>
        get() = repository.allData

    // private LiveData<List<EntityWaterReminder>> allNotes;
    init {
        // allNotes = repository.getAllUsers();
    }*/

    val getAllUser: LiveData<List<EntityWaterReminder>>
    private val repositoryWaterReminder: RepositoryWaterReminder

    init {
        val dataAccessObjectWaterReminder = DatabaseWaterReminder.getInstance(application)?.dataAccessObjectWaterReminder()
        repositoryWaterReminder = RepositoryWaterReminder(dataAccessObjectWaterReminder!!)
        getAllUser = repositoryWaterReminder.getAllUser
    }

    fun insert(entityWaterReminder: EntityWaterReminder) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryWaterReminder.insert(entityWaterReminder)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryWaterReminder.deleteAllData()
        }
    }

}
