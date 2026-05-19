package com.example.project100.di

import android.content.Context
import androidx.room.Room
import com.example.project100.data.local.AppDatabase
import com.example.project100.data.local.dao.PunishmentDao
import com.example.project100.data.local.dao.UserProfileDao
import com.example.project100.data.local.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "project100_db"
        ).build()
    }

    @Provides
    fun provideWorkoutDao(database: AppDatabase): WorkoutDao = database.workoutDao()

    @Provides
    fun providePunishmentDao(database: AppDatabase): PunishmentDao = database.punishmentDao()

    @Provides
    fun provideUserProfileDao(database: AppDatabase): UserProfileDao = database.userProfileDao()
}
