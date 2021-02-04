/*
package com.example.sampleproject_1.Database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class RepositoryWaterReminder {
    private DataAccessObjectWaterReminder dataAccessObjectWaterReminder;

    public RepositoryWaterReminder(Application application) {
        DatabaseWaterReminder databaseWaterReminder = DatabaseWaterReminder.getInstance(application);
        dataAccessObjectWaterReminder = databaseWaterReminder.dataAccessObjectWaterReminder();
    }

    public void insert(EntityWaterReminder entityWaterReminder) {
        new RepositoryWaterReminder.InsertUserInfoAsyncTask(dataAccessObjectWaterReminder).execute(entityWaterReminder);
    }
    public void getUserGender() {
        new RepositoryWaterReminder.getUserGenderInfoAsyncTask(dataAccessObjectWaterReminder).execute();
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

    private static class getUserGenderInfoAsyncTask extends AsyncTask<EntityWaterReminder, Void, Void> {
        private DataAccessObjectWaterReminder dataAccessObjectWaterReminder;

        private getUserGenderInfoAsyncTask(DataAccessObjectWaterReminder dataAccessObjectWaterReminder) {
            this.dataAccessObjectWaterReminder = dataAccessObjectWaterReminder;
        }

        @Override
        protected Void doInBackground(EntityWaterReminder... entityWaterReminders) {
            return null;
        }
    }

}

*/
