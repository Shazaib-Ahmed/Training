package com.example.sampleproject_1.WaterReminder.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_reminder_users_stats")
data class EntityWaterReminder(
        @PrimaryKey(autoGenerate = true) val kEY_ID: Int,
        val kEY_DATE: String,
        val kEY_INTOOK: Int,
        val totalInTook_KEY: Int,
        val kEY_TOTAL_INTAKE: Int
)
/* @PrimaryKey(autoGenerate = true) var kEY_ID = 0
 var kEY_DATE: String? = null
 var kEY_INTOOK = 0
 var totalInTook_KEY = 0
 var kEY_TOTAL_INTAKE = 0

 constructor() {}

 constructor(KEY_DATE: String?, KEY_INTOOK: Int, totalInTook_KEY: Int, KEY_TOTAL_INTAKE: Int) {
     kEY_DATE = KEY_DATE
     kEY_INTOOK = KEY_INTOOK
     this.totalInTook_KEY = totalInTook_KEY
     kEY_TOTAL_INTAKE = KEY_TOTAL_INTAKE
 }*/
