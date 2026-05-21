package com.example.project100.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey val date: LocalDate,
    val pushUps: Int = 0,
    val sitUps: Int = 0,
    val squats: Int = 0,
    val runningKm: Double = 0.0,
    val waterMl: Int = 0,
    val isCompleted: Boolean = false
)
