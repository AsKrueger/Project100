package com.example.project100.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 0, // Single user profile
    val username: String = "RECRUIT",
    val profilePictureUri: String? = null,
    val currentStreak: Int = 0,
    val highestStreak: Int = 0,
    val currentRank: String = "E",
    val totalPunishmentBurpeesCompleted: Int = 0,
    val joinDate: Long = System.currentTimeMillis()
)
