package com.example.sampleproject_1.WaterReminder.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityWaterReminder::class], version = 7)
abstract class DatabaseWaterReminder : RoomDatabase() {
    abstract fun dataAccessObjectWaterReminder(): DataAccessObjectWaterReminder

    companion object {
        private const val dbName = "database_water_reminder"

        private var instance: DatabaseWaterReminder? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): DatabaseWaterReminder? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, DatabaseWaterReminder::class.java, dbName)
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance
        }
    }
}