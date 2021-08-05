package com.example.sampleproject_1.WaterReminder.Utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {


    const val USERS_SHARED_PREF              =           "user_pref"
    const val PRIVATE_MODE                   =               0

    /** WATER_REMINDER **/

    @JvmField
    var WEIGHT_KEY                           =          "weight"
    var FIRST_RUN_KEY                        =          "firstrun"
    var NOTIFICATION_KEY                     =          "notificationkey"
    var WORK_TIME_KEY                        =          "worktime"

    @JvmField
    var TOTAL_INTAKE                         =          "totalintake"
    var SLEEPING_TIME_KEY                    =          "sleepingtime"

    @JvmField
    var WAKE_UP_TIME_KEY                     =          "wakeuptime"

    @JvmField
    var GENDER_KEY                           =          "gender"

    fun calculateIntake(weight: Int, workTime: Int): Int {
        return weight * 100 / 3 + workTime / 6 * 7
    }

    val currentDate: String
        get() {
            val calendar = Calendar.getInstance()
            val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
            return dateFormat.format(calendar)
        }


    /**  WEIGHT_TRACKER  **/

    @JvmField
    var INITIAL_WEIGHT_KEY_WT                =          "current_weight_wt"
    var FINAL_WEIGHT_KEY_WT                  =          "goal_weight_wt"
    var FIRST_RUN_KEY_WEIGHT_TRACKER         =          "firstrunWT"
    var RADIO_OPTION_KEY_WT                  =          "radioOptionKeyWt"
    var NOTIFICATION_KEY_WT                  =          "notificationkeyWT"

    fun covertToKg(weight: Int): Double {
        val change = 2.2046226218
        return weight / change
    }


    fun covertToLb(weight: Int): Double {
        val change = 2.2046226218
        return weight * change
    }

    /**  WATER_TRACKER  **/

    @JvmField
    var WEIGHT_KEY_WATER_TRACKER             =          "weight"
    var FIRST_RUN_KEY_WATER_TRACKER          =          "firstrun"
    var NOTIFICATION_KEY_WATER_TRACKER       =          "notificationkey"
    var WORK_TIME_KEY_WATER_TRACKER          =          "worktime"
    var TOTAL_INTAKE_WATER_TRACKER           =          "totalintake"
    var GENDER_KEY_WATER_TRACKER             =          "gender"

    /**  WEIGHT_TRACKER_2  **/

    @JvmField
    var ENTER_WEIGHT_KEY_WT2                 =          "enter_weight_wt2"
    var GOAL_WEIGHT_KEY_WT2                  =          "goal_weight_wt2"
    var DROPPED_WEIGHT_KEY_WT2               =          "dropped_weight_wt2"
    var CURRENT_WEIGHT_KEY_WT2               =          "current_weight_wt2"
    var FIRST_RUN_KEY_WEIGHT_TRACKER_2       =          "firstrunWT2"
    var RADIO_OPTION_KEY_WT2                 =          "radioOptionKeyWt2"
    var NOTIFICATION_KEY_WT2                 =          "notificationkeyWT2"
    var PROGRESS_PERCENT_KEY_WT2             =          "progresspercentWT2"

    fun covertToKgWt2(weight: Int): Double {
        val change = 2.2046226218
        return weight / change
    }


    fun covertToLbWt2(weight: Int): Double {
        val change = 2.2046226218
        return weight * change
    }

}