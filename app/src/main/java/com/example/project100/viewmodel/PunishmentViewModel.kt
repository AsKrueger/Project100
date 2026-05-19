package com.example.project100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project100.data.local.entity.PunishmentEntity
import com.example.project100.data.local.entity.UserProfileEntity
import com.example.project100.data.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PunishmentViewModel @Inject constructor(
    private val repository: WorkoutRepository
) : ViewModel() {

    val activePunishments: StateFlow<List<PunishmentEntity>> = repository.getActivePunishments()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalDebt: StateFlow<Int?> = repository.getTotalDebt()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun completeBurpees(amount: Int) {
        viewModelScope.launch {
            var remaining = amount
            val punishments = activePunishments.value
            for (punishment in punishments) {
                if (remaining <= 0) break
                val currentBurpeesNeeded = punishment.totalBurpees - punishment.completedBurpees
                if (currentBurpeesNeeded <= 0) continue
                
                val canTake = minOf(remaining, currentBurpeesNeeded)
                val newCompleted = punishment.completedBurpees + canTake
                val updated = punishment.copy(
                    completedBurpees = newCompleted,
                    isCleared = newCompleted >= punishment.totalBurpees
                )
                repository.updatePunishment(updated)
                remaining -= canTake
            }
            
            // Update total completed in profile
            val profile = repository.getUserProfile() ?: UserProfileEntity()
            repository.updateProfile(profile.copy(
                totalPunishmentBurpeesCompleted = profile.totalPunishmentBurpeesCompleted + amount
            ))
        }
    }
}
