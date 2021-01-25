package com.example.sampleproject_1;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {UserInfo.class},version = 1)
public abstract class UserInfoDatabase extends RoomDatabase {

    private static UserInfoDatabase instance;

    public abstract UserInfoDao userInfoDao();

    public static synchronized UserInfoDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserInfoDatabase.class,"user_info_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserInfoDao userInfoDao;

        private PopulateDbAsyncTask(UserInfoDatabase db)
        {
            userInfoDao = db.userInfoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userInfoDao.insert(new UserInfo("John",20,"abc@gmail.com",987654321));
            userInfoDao.insert(new UserInfo("Paul",21,"xyz@gmail.com",9000000));
            userInfoDao.insert(new UserInfo("Ashley",23,"pqr@gmail.com",9999999));
            return null;
        }
    }

}
