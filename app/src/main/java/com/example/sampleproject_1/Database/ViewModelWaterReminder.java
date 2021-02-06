package com.example.sampleproject_1.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class ViewModelWaterReminder extends AndroidViewModel {
    private RepositoryWaterReminder repository;
    private LiveData<List<EntityWaterReminder>> allNotes;


    public ViewModelWaterReminder(@NonNull Application application) {
        super(application);
        repository = new RepositoryWaterReminder(application);
        allNotes = repository.getAllUsers();
    }

    public void insert(EntityWaterReminder entityWaterReminder) {
        repository.insert(entityWaterReminder);
    }

    public LiveData<List<EntityWaterReminder>> getAllNotes() {
        return allNotes;
    }


}
