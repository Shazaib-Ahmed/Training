package com.example.sampleproject_1.WaterReminder.Utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {
    const val USERS_SHARED_PREF = "user_pref"
    const val PRIVATE_MODE = 0

    @JvmField
    var WEIGHT_KEY = "weight"
    var FIRST_RUN_KEY = "firstrun"
    var WORK_TIME_KEY = "worktime"

    @JvmField
    var TOTAL_INTAKE = "totalintake"
    var SLEEPING_TIME_KEY = "sleepingtime"

    @JvmField
    var WAKE_UP_TIME_KEY = "wakeuptime"

    @JvmField
    var GENDER_KEY = "gender"

    fun calculateIntake(weight: Int, workTime: Int): Int {
        return weight * 100 / 3 + workTime / 6 * 7
    }

    val currentDate: String
        get() {
            val calendar = Calendar.getInstance()
            val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
            return dateFormat.format(calendar)
        }

}