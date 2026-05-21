package com.example.project100.system

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SystemManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun scheduleDailyReset() {
        val workRequest = PeriodicWorkRequestBuilder<DailyResetWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "DailyReset",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun calculateInitialDelay(): Long {
        val now = LocalDateTime.now()
        val resetTime = LocalTime.of(23, 59)
        var nextReset = LocalDateTime.of(now.toLocalDate(), resetTime)

        if (now.isAfter(nextReset)) {
            nextReset = nextReset.plusDays(1)
        }

        return Duration.between(now, nextReset).toMillis()
    }
}
