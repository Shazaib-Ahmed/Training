package com.example.sampleproject_1.weightTracker.DatabaseWT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_tracker_user_stats")
class EntityWeightTracker(@PrimaryKey(autoGenerate = true) val key_ID_weight_tracker: Int,
                          val key_initial_weight: Int,
                          val key_final_weight: Int,
                          val key_current_weight: Int
) {
}