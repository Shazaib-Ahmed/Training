package com.example.sampleproject_1.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {EntityWaterReminder.class},version = 3)
public abstract class DatabaseWaterReminder extends RoomDatabase {

    private static DatabaseWaterReminder instance;

    public abstract DataAccessObjectWaterReminder dataAccessObjectWaterReminder();

  /* public static DatabaseWaterReminder getInstance(final Context context){
        if (instance==null)
        {
            synchronized (DatabaseWaterReminder.class){
                instance= Room.databaseBuilder(context,DatabaseWaterReminder.class,"database_water_reminder").build();
            }
        }
        return instance;
    }*/


    public static synchronized DatabaseWaterReminder getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseWaterReminder.class,"database_water_reminder")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
