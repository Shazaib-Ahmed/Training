package com.example.sampleproject_1.weightTracker.DatabaseWT

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleproject_1.WaterReminder.Database.DataAccessObjectWaterReminder
import com.example.sampleproject_1.WaterReminder.Database.DatabaseWaterReminder

@Database(entities = [EntityWeightTracker::class], version = 1)
abstract class DatabaseWeightTracker : RoomDatabase() {

    abstract fun dataAccessObjectWeightTracker(): DataAccessObjectWeightTracker

    companion object {
        private const val dbName = "database_weight_tracker"

        private var instance: DatabaseWeightTracker? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): DatabaseWeightTracker? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, DatabaseWeightTracker::class.java, dbName)
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance
        }
    }
}