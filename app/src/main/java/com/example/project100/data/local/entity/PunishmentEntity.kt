package com.example.project100.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "punishments")
data class PunishmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dateGenerated: LocalDate,
    val totalBurpees: Int,
    val completedBurpees: Int = 0,
    val isCleared: Boolean = false
)
