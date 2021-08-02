package com.example.sampleproject_1.weightTracker2.databaseWt2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_tracker_2_user_stats")
class EntityWeightTracker2 (@PrimaryKey(autoGenerate = true) val key_ID_weight_tracker2: Int,
                            val key_initial_weight: Int,
                            val key_final_weight: Int,
                            val key_current_weight: Int,
                            val key_current_date: String
) {
}