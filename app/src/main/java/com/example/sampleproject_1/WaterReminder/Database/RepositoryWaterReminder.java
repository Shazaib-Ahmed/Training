package com.example.sampleproject_1.WaterReminder.Database;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositoryWaterReminder {
    private DataAccessObjectWaterReminder dataAccessObjectWaterReminder;
    private LiveData<List<EntityWaterReminder>> allUsers;

    public RepositoryWaterReminder(Application application) {
        DatabaseWaterReminder databaseWaterReminder = DatabaseWaterReminder.getInstance(application);
        dataAccessObjectWaterReminder = databaseWaterReminder.dataAccessObjectWaterReminder();
        allUsers = dataAccessObjectWaterReminder.getGetAllUsers();
    }

    public void insert(EntityWaterReminder entityWaterReminder) {
        new InsertUserInfoAsyncTask(dataAccessObjectWaterReminder).execute(entityWaterReminder);
    }

    public LiveData<List<EntityWaterReminder>> getAllUsers(){
        return allUsers;
    }


    private static class InsertUserInfoAsyncTask extends AsyncTask<EntityWaterReminder, Void, Void> {

        private DataAccessObjectWaterReminder dataAccessObjectWaterReminder;

        private InsertUserInfoAsyncTask(DataAccessObjectWaterReminder dataAccessObjectWaterReminder) {
            this.dataAccessObjectWaterReminder = dataAccessObjectWaterReminder;
        }

        @Override
        protected Void doInBackground(EntityWaterReminder... entityWaterReminders) {
            dataAccessObjectWaterReminder.insert(entityWaterReminders[0]);
            return null;
        }
    }


}

