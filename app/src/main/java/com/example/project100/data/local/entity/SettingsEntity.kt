package com.example.project100.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val id: Int = 0,
    val isSoundEnabled: Boolean = true,
    val isNotificationsEnabled: Boolean = true,
    val preferredUnit: String = "KM", // KM or Miles
    val lastSyncTimestamp: Long = System.currentTimeMillis()
)
