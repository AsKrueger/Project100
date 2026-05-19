package com.example.project100.data.repository

import com.example.project100.data.local.dao.BodyMetricsDao
import com.example.project100.data.local.entity.BodyMetricsEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BodyMetricsRepository @Inject constructor(
    private val bodyMetricsDao: BodyMetricsDao
) {
    fun getAllMetrics(): Flow<List<BodyMetricsEntity>> = bodyMetricsDao.getAllMetrics()

    suspend fun saveMetrics(metrics: BodyMetricsEntity) = bodyMetricsDao.insertMetrics(metrics)

    suspend fun getMetricsForDate(date: LocalDate): BodyMetricsEntity? = bodyMetricsDao.getMetricsByDate(date)
}
