package com.example.project100.system

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
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
        // Calculate delay until next 23:59
        // Simplified: for demo/dev purposes, can be set shorter or just run daily
        // Real implementation would target midnight
        return 0L 
    }
}
