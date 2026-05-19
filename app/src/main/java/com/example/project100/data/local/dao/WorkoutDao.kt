package com.example.project100.data.local.dao

import androidx.room.*
import com.example.project100.data.local.entity.WorkoutEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts WHERE date = :date")
    suspend fun getWorkoutByDate(date: LocalDate): WorkoutEntity?

    @Query("SELECT * FROM workouts WHERE date = :date")
    fun getWorkoutFlowByDate(date: LocalDate): Flow<WorkoutEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Query("SELECT * FROM workouts ORDER BY date DESC")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>
}
