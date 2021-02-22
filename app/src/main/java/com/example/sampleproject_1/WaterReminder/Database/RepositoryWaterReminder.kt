package com.example.sampleproject_1.WaterReminder.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.sampleproject_1.WaterReminder.Database.DatabaseWaterReminder.Companion.getInstance

class RepositoryWaterReminder(application: Application?) {
    private val dataAccessObjectWaterReminder: DataAccessObjectWaterReminder?
    private val allUsers: MutableLiveData<List<EntityWaterReminder?>?>
    fun insert(entityWaterReminder: EntityWaterReminder?) {
        InsertUserInfoAsyncTask(dataAccessObjectWaterReminder).execute(entityWaterReminder)
    }

    val allData: MutableLiveData<List<EntityWaterReminder?>?>
        get() {
            GetWaterReminderData(dataAccessObjectWaterReminder, allUsers).execute()
            return allUsers
        }

    /* public LiveData<List<EntityWaterReminder>> getAllUsers(){
        return allUsers;
    }*/
    private class InsertUserInfoAsyncTask(private val dataAccessObjectWaterReminder: DataAccessObjectWaterReminder?) : AsyncTask<EntityWaterReminder?, Void?, Void?>() {
        protected override fun doInBackground(vararg entityWaterReminders: EntityWaterReminder?): Void? {
            dataAccessObjectWaterReminder!!.insert(entityWaterReminders[0])
            return null
        }

    }

    private class GetWaterReminderData(private val dataAccessObjectWaterReminder: DataAccessObjectWaterReminder?, private val allData: MutableLiveData<List<EntityWaterReminder?>?>) : AsyncTask<Void?, Void?, List<EntityWaterReminder?>?>() {
        protected override fun doInBackground(vararg params: Void?): List<EntityWaterReminder?>? {
            //            List<BarEntry> values = new ArrayList<>();
//            for (int i = 0; i < current.size(); i++) {
//                values.add(new BarEntry(100f,current.get(i).getKEY_INTOOK()));
//            }
            return dataAccessObjectWaterReminder!!.getAllUsers
        }

        override fun onPostExecute(entries: List<EntityWaterReminder?>?) {
            super.onPostExecute(entries)
            allData.postValue(entries)
        }

    }

    init {
        val databaseWaterReminder = getInstance(application!!)
        dataAccessObjectWaterReminder = databaseWaterReminder!!.dataAccessObjectWaterReminder()
        allUsers = MutableLiveData()
    }
}