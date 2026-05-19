package com.example.project100.data.local.dao

import androidx.room.*
import com.example.project100.data.local.entity.PunishmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PunishmentDao {
    @Query("SELECT * FROM punishments WHERE isCleared = 0 ORDER BY dateGenerated ASC")
    fun getActivePunishments(): Flow<List<PunishmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPunishment(punishment: PunishmentEntity)

    @Update
    suspend fun updatePunishment(punishment: PunishmentEntity)

    @Query("SELECT SUM(totalBurpees - completedBurpees) FROM punishments WHERE isCleared = 0")
    fun getTotalDebtFlow(): Flow<Int?>

    @Query("SELECT * FROM punishments ORDER BY dateGenerated DESC")
    fun getAllPunishments(): Flow<List<PunishmentEntity>>
}
