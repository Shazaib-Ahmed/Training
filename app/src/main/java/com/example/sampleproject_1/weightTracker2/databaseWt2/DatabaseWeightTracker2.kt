package com.example.sampleproject_1.weightTracker2.databaseWt2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityWeightTracker2::class], version = 2)
abstract  class DatabaseWeightTracker2 : RoomDatabase() {

    abstract fun dataAccessObjectWeightTracker2(): DataAccessObjectWeightTracker2

    companion object {
        private const val dbName = "database_weight_tracker2"

        private var instanceWT2: DatabaseWeightTracker2? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): DatabaseWeightTracker2? {
            if (instanceWT2 == null) {
                instanceWT2 = Room.databaseBuilder(context.applicationContext, DatabaseWeightTracker2::class.java, dbName)
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instanceWT2
        }
    }
}