package com.example.project100.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "body_metrics")
data class BodyMetricsEntity(
    @PrimaryKey val date: LocalDate,
    val weight: Double = 0.0,
    val bodyFat: Double = 0.0,
    val chest: Double = 0.0,
    val waist: Double = 0.0,
    val arms: Double = 0.0,
    val legs: Double = 0.0
)
