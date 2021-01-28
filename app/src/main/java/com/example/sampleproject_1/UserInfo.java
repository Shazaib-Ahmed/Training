package com.example.sampleproject_1;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_info")
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int age;

    private String email;

    private String mobileNumber;

    public UserInfo(String name, int age, String email, String mobileNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }


}
