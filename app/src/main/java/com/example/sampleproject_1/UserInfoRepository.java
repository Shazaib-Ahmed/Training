package com.example.sampleproject_1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class UserInfoRepository {
    private UserInfoDao userInfoDao;
    private LiveData<List<UserInfo>> allUserInfo;

    public  UserInfoRepository(Application application){
        UserInfoDatabase database =UserInfoDatabase.getInstance(application);
        userInfoDao = database.userInfoDao();
        allUserInfo =userInfoDao.getAllUserInfo();
    }

    public void insert(UserInfo userInfo) {
        new InsertUserInfoAsyncTask(userInfoDao).execute(userInfo);

    }

    public void update(UserInfo userInfo) {
        new UpdateUserInfoAsyncTask(userInfoDao).execute(userInfo);

    }

    public void delete(UserInfo userInfo) {
        new DeleteUserInfoAsyncTask(userInfoDao).execute(userInfo);
    }

    public void deleteAllUserInfo() {
        new DeleteAllUserInfoUserInfoAsyncTask(userInfoDao).execute();
    }

    public LiveData<List<UserInfo>> getAllUserInfo () {
        return allUserInfo;
    }

    private  static  class  InsertUserInfoAsyncTask extends AsyncTask<UserInfo, Void, Void>{

        private  UserInfoDao userInfoDao;

        private  InsertUserInfoAsyncTask(UserInfoDao userInfoDao)
        {
            this.userInfoDao=userInfoDao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            userInfoDao.insert(userInfos[0]);
            return null;
        }
    }

    private  static  class  UpdateUserInfoAsyncTask extends AsyncTask<UserInfo, Void, Void>{

        private  UserInfoDao userInfoDao;

        private  UpdateUserInfoAsyncTask(UserInfoDao userInfoDao)
        {
            this.userInfoDao=userInfoDao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            userInfoDao.update(userInfos[0]);
            return null;
        }
    }

    private  static  class  DeleteUserInfoAsyncTask extends AsyncTask<UserInfo, Void, Void>{

        private  UserInfoDao userInfoDao;

        private  DeleteUserInfoAsyncTask(UserInfoDao userInfoDao)
        {
            this.userInfoDao=userInfoDao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            userInfoDao.delete(userInfos[0]);
            return null;
        }
    }

    private  static  class  DeleteAllUserInfoUserInfoAsyncTask extends AsyncTask<Void, Void, Void>{

        private  UserInfoDao userInfoDao;

        private  DeleteAllUserInfoUserInfoAsyncTask(UserInfoDao userInfoDao)
        {
            this.userInfoDao=userInfoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userInfoDao.deleteAllNotes();
            return null;
        }
    }


}
