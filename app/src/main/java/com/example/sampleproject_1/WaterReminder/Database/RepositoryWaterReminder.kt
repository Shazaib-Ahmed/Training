package com.example.sampleproject_1.WaterReminder.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampleproject_1.WaterReminder.Database.DatabaseWaterReminder.Companion.getInstance
import kotlinx.coroutines.flow.Flow

class RepositoryWaterReminder(private val dataAccessObjectWaterReminder: DataAccessObjectWaterReminder) {

    val getAllUser: LiveData<List<EntityWaterReminder>> = dataAccessObjectWaterReminder.getAllUsers()


    suspend fun insert(entityWaterReminder: EntityWaterReminder) {
        dataAccessObjectWaterReminder.insert(entityWaterReminder)
    }

    suspend fun deleteAllData(){
        dataAccessObjectWaterReminder.deleteAllData()
    }

}


/*
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

    private class InsertUserInfoAsyncTask(private val dataAccessObjectWaterReminder: DataAccessObjectWaterReminder?) : AsyncTask<EntityWaterReminder?, Void?, Void?>() {
        protected override fun doInBackground(vararg entityWaterReminders: EntityWaterReminder?): Void? {
            dataAccessObjectWaterReminder!!.insert(entityWaterReminders[0])
            return null
        }

    }

    private class GetWaterReminderData(private val dataAccessObjectWaterReminder: DataAccessObjectWaterReminder?, private val allData: MutableLiveData<List<EntityWaterReminder?>?>) : AsyncTask<Void?, Void?, List<EntityWaterReminder?>?>() {
        override fun doInBackground(vararg params: Void?): List<EntityWaterReminder?>? {
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
}*/
