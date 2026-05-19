package com.example.project100.data.local.dao

import androidx.room.*
import com.example.project100.data.local.entity.BodyMetricsEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface BodyMetricsDao {
    @Query("SELECT * FROM body_metrics ORDER BY date DESC")
    fun getAllMetrics(): Flow<List<BodyMetricsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMetrics(metrics: BodyMetricsEntity)

    @Query("SELECT * FROM body_metrics WHERE date = :date")
    suspend fun getMetricsByDate(date: LocalDate): BodyMetricsEntity?
}
