package com.example.project100.data.repository

import com.example.project100.data.local.dao.PunishmentDao
import com.example.project100.data.local.dao.UserProfileDao
import com.example.project100.data.local.dao.WorkoutDao
import com.example.project100.data.local.entity.PunishmentEntity
import com.example.project100.data.local.entity.UserProfileEntity
import com.example.project100.data.local.entity.WorkoutEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val punishmentDao: PunishmentDao,
    private val userProfileDao: UserProfileDao
) {
    fun getTodayWorkout(date: LocalDate): Flow<WorkoutEntity?> = workoutDao.getWorkoutFlowByDate(date)

    suspend fun updateWorkout(workout: WorkoutEntity) = workoutDao.insertWorkout(workout)

    fun getTotalDebt(): Flow<Int?> = punishmentDao.getTotalDebtFlow()

    fun getActivePunishments(): Flow<List<PunishmentEntity>> = punishmentDao.getActivePunishments()

    suspend fun updatePunishment(punishment: PunishmentEntity) = punishmentDao.updatePunishment(punishment)

    suspend fun addPunishment(punishment: PunishmentEntity) = punishmentDao.insertPunishment(punishment)

    fun getUserProfileFlow(): Flow<UserProfileEntity?> = userProfileDao.getUserProfileFlow()

    suspend fun getUserProfile(): UserProfileEntity? = userProfileDao.getUserProfile()

    suspend fun updateProfile(profile: UserProfileEntity) = userProfileDao.insertOrUpdateProfile(profile)

    fun getAllWorkouts(): Flow<List<WorkoutEntity>> = workoutDao.getAllWorkouts()
}
