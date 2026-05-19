package com.example.project100.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.project100.data.local.dao.PunishmentDao
import com.example.project100.data.local.dao.UserProfileDao
import com.example.project100.data.local.dao.WorkoutDao
import com.example.project100.data.local.entity.PunishmentEntity
import com.example.project100.data.local.entity.UserProfileEntity
import com.example.project100.data.local.entity.WorkoutEntity

@Database(
    entities = [WorkoutEntity::class, PunishmentEntity::class, UserProfileEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun punishmentDao(): PunishmentDao
    abstract fun userProfileDao(): UserProfileDao
}
