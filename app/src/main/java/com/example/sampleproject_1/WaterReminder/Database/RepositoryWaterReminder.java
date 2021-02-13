package com.example.sampleproject_1.WaterReminder.Database;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class RepositoryWaterReminder {
    private DataAccessObjectWaterReminder dataAccessObjectWaterReminder;
    private MutableLiveData<List<EntityWaterReminder>> allUsers;

    public RepositoryWaterReminder(Application application) {
        DatabaseWaterReminder databaseWaterReminder = DatabaseWaterReminder.getInstance(application);
        dataAccessObjectWaterReminder = databaseWaterReminder.dataAccessObjectWaterReminder();
        allUsers = new MutableLiveData<>();
    }

    public void insert(EntityWaterReminder entityWaterReminder) {
        new InsertUserInfoAsyncTask(dataAccessObjectWaterReminder).execute(entityWaterReminder);
    }

    MutableLiveData<List<EntityWaterReminder>> getAllData(){
        new GetWaterReminderData(dataAccessObjectWaterReminder,allUsers).execute();
        return allUsers;
    }


   /* public LiveData<List<EntityWaterReminder>> getAllUsers(){
        return allUsers;
    }*/


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

    private static class GetWaterReminderData extends AsyncTask<Void, Void, List<EntityWaterReminder>> {

        private DataAccessObjectWaterReminder dataAccessObjectWaterReminder;
        private MutableLiveData<List<EntityWaterReminder>> allData;

       public GetWaterReminderData(DataAccessObjectWaterReminder dao, MutableLiveData<List<EntityWaterReminder>> mAllWords) {
            dataAccessObjectWaterReminder = dao;
            this.allData = mAllWords;
        }

        @Override
        protected List<EntityWaterReminder> doInBackground(Void... lists) {
            List<EntityWaterReminder> current = dataAccessObjectWaterReminder.getGetAllUsers();
//            List<BarEntry> values = new ArrayList<>();
//            for (int i = 0; i < current.size(); i++) {
//                values.add(new BarEntry(100f,current.get(i).getKEY_INTOOK()));
//            }
            return current;
        }

        @Override
        protected void onPostExecute(List<EntityWaterReminder> entries) {
            super.onPostExecute(entries);
            allData.postValue(entries);
        }
    }



}

