package com.example.project100.system

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.project100.data.local.dao.PunishmentDao
import com.example.project100.data.local.dao.UserProfileDao
import com.example.project100.data.local.dao.WorkoutDao
import com.example.project100.data.local.entity.PunishmentEntity
import com.example.project100.data.local.entity.UserProfileEntity
import com.example.project100.data.local.entity.WorkoutEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.firstOrNull
import java.time.LocalDate

@HiltWorker
class DailyResetWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val workoutDao: WorkoutDao,
    private val punishmentDao: PunishmentDao,
    private val userProfileDao: UserProfileDao
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val yesterday = LocalDate.now().minusDays(1)
        val workout = workoutDao.getWorkoutByDate(yesterday) ?: WorkoutEntity(yesterday)

        val missingPushUps = (100 - workout.pushUps).coerceAtLeast(0)
        val missingSitUps = (100 - workout.sitUps).coerceAtLeast(0)
        val missingSquats = (100 - workout.squats).coerceAtLeast(0)
        val missingKm = (10.0 - workout.runningKm).coerceAtLeast(0.0)

        val burpeesDebt = missingPushUps + missingSitUps + missingSquats + (missingKm * 10).toInt()
        
        val currentProfile = userProfileDao.getUserProfile().firstOrNull() ?: UserProfileEntity()

        if (burpeesDebt > 0) {
            punishmentDao.insertPunishment(
                PunishmentEntity(
                    dateGenerated = yesterday,
                    totalBurpees = burpeesDebt
                )
            )
            // Reset streak and update rank
            val updatedProfile = currentProfile.copy(
                currentStreak = 0,
                currentRank = calculateRank(0)
            )
            userProfileDao.insertOrUpdateProfile(updatedProfile)
        } else {
            // Increment streak
            val newStreak = currentProfile.currentStreak + 1
            val updatedProfile = currentProfile.copy(
                currentStreak = newStreak,
                highestStreak = maxOf(currentProfile.highestStreak, newStreak),
                currentRank = calculateRank(newStreak)
            )
            userProfileDao.insertOrUpdateProfile(updatedProfile)
        }

        return Result.success()
    }

    private fun calculateRank(streak: Int): String {
        return when {
            streak >= 1000 -> "S"
            streak >= 365 -> "A"
            streak >= 100 -> "B"
            streak >= 30 -> "C"
            streak >= 7 -> "D"
            else -> "E"
        }
    }
}
