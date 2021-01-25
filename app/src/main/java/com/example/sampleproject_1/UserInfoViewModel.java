package com.example.sampleproject_1;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserInfoViewModel extends AndroidViewModel {
    private  UserInfoRepository repository;
    private LiveData<List<UserInfo>> allUserInfo;

    public UserInfoViewModel(@NonNull Application application) {
        super(application);
        repository =new UserInfoRepository(application);
        allUserInfo =repository.getAllUserInfo();
    }

    public void insert(UserInfo userInfo){
        repository.insert(userInfo);
    }

    public void update(UserInfo userInfo){
        repository.update(userInfo);
    }

    public void delete(UserInfo userInfo){
        repository.delete(userInfo);
    }

    public void deleteAllUserInfo(){
        repository.deleteAllUserInfo();
    }

    public LiveData<List<UserInfo>> getAllUserInfo() {
        return allUserInfo;
    }
}
