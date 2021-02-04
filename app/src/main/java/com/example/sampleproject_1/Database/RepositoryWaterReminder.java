package com.example.sampleproject_1.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sampleproject_1.UserInfo;
import com.example.sampleproject_1.UserInfoDao;

import java.util.List;

public class RepositoryWaterReminder {
    private DataAccessObjectWaterReminder dataAccessObjectWaterReminder;
    private List<EntityWaterReminder> allUserDetails;

    public RepositoryWaterReminder(Application application) {
        DatabaseWaterReminder databaseWaterReminder = DatabaseWaterReminder.getInstance(application);
        dataAccessObjectWaterReminder = databaseWaterReminder.dataAccessObjectWaterReminder();
    }

    public void insert(EntityWaterReminder entityWaterReminder) {
        new RepositoryWaterReminder.InsertUserInfoAsyncTask(dataAccessObjectWaterReminder).execute(entityWaterReminder);
    }

    public List<EntityWaterReminder> getAllUserInfo () {
        return allUserDetails;
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

