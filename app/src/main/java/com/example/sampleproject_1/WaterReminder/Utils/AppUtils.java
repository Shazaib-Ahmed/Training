package com.example.sampleproject_1.WaterReminder.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppUtils {

    public static final String USERS_SHARED_PREF = "user_pref";
    public static final int PRIVATE_MODE = 0;
    public static String WEIGHT_KEY = "weight";
    public static String FIRST_RUN_KEY = "firstrun";
    public static String WORK_TIME_KEY = "worktime";
    public static String TOTAL_INTAKE = "totalintake";
    public static String SLEEPING_TIME_KEY = "sleepingtime";
    public static String WAKE_UP_TIME_KEY = "wakeuptime";
    public static String GENDER_KEY = "gender";


    public static int calculateIntake(int weight, int workTime) {
        return ((weight * 100 / 3) + (workTime / 6 * 7));
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(calendar);
    }


}
