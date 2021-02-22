package com.example.sampleproject_1.WaterReminder.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ViewModelWaterReminder(application: Application) :AndroidViewModel(application) {

    private val repository: RepositoryWaterReminder = RepositoryWaterReminder(application)
    fun insert(entityWaterReminder: EntityWaterReminder?) {
        repository.insert(entityWaterReminder)
    }

    val allData: LiveData<List<EntityWaterReminder?>?>
        get() = repository.allData

    // private LiveData<List<EntityWaterReminder>> allNotes;
    init {
        // allNotes = repository.getAllUsers();
    }
}