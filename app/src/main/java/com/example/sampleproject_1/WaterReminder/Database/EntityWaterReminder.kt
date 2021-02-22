package com.example.sampleproject_1.WaterReminder.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_reminder_users_stats")
class EntityWaterReminder {
    @PrimaryKey(autoGenerate = true)
    var kEY_ID = 0

    constructor() {}

    var kEY_DATE: String? = null

    var kEY_INTOOK = 0
    var totalInTook_KEY = 0

    constructor(KEY_DATE: String?, KEY_INTOOK: Int, totalInTook_KEY: Int, KEY_TOTAL_INTAKE: Int) {
        kEY_DATE = KEY_DATE
        kEY_INTOOK = KEY_INTOOK
        this.totalInTook_KEY = totalInTook_KEY
        kEY_TOTAL_INTAKE = KEY_TOTAL_INTAKE
    }

    var kEY_TOTAL_INTAKE = 0

    fun getKEY_INTOOK(date: String?): Int {
        return kEY_INTOOK
    }

}