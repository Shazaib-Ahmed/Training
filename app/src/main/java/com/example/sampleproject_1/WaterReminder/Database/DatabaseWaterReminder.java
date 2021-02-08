package com.example.sampleproject_1.WaterReminder.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EntityWaterReminder.class}, version = 4)
public abstract class DatabaseWaterReminder extends RoomDatabase {

    private static final String dbName = "database_water_reminder";

    private static DatabaseWaterReminder instance;

    public abstract DataAccessObjectWaterReminder dataAccessObjectWaterReminder();

    public static synchronized DatabaseWaterReminder getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseWaterReminder.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
