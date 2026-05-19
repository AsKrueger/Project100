package com.example.project100.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.project100.data.local.dao.*
import com.example.project100.data.local.entity.*

@Database(
    entities = [
        WorkoutEntity::class, 
        PunishmentEntity::class, 
        UserProfileEntity::class, 
        BodyMetricsEntity::class,
        SettingsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun punishmentDao(): PunishmentDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun bodyMetricsDao(): BodyMetricsDao
    abstract fun settingsDao(): SettingsDao
}
