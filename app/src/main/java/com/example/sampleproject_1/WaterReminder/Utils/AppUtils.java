package com.example.sampleproject_1.WaterReminder.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppUtils {

    public int calculateIntake(int weight, int workTime) {
        return ((weight * 100 / 3) + (workTime / 6 * 7));
    }

   String USERS_SHARED_PREF = "user_pref";
    int PRIVATE_MODE = 0;
    String WEIGHT_KEY = "weight";
    String FIRST_RUN_KEY = "firstrun";
}
