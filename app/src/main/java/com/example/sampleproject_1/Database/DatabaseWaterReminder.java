package com.example.sampleproject_1.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {EntityWaterReminder.class},version = 3)
public abstract class DatabaseWaterReminder extends RoomDatabase {

    private static final String dbName = "database_water_reminder";

    private static DatabaseWaterReminder userDatabase;

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


    public static synchronized DatabaseWaterReminder getUserDatabase(Context context){
        if (userDatabase == null){
            userDatabase = Room.databaseBuilder(context,DatabaseWaterReminder.class,dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDatabase;
    }
}
