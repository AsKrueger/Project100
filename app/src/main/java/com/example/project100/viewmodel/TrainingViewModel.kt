package com.example.project100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project100.data.local.entity.WorkoutEntity
import com.example.project100.data.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val today = LocalDate.now()

    val todayWorkout: StateFlow<WorkoutEntity> = repository.getTodayWorkout(today)
        .map { it ?: WorkoutEntity(today) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WorkoutEntity(today))

    fun updatePushUps(delta: Int) {
        viewModelScope.launch {
            val current = todayWorkout.value
            repository.updateWorkout(current.copy(pushUps = (current.pushUps + delta).coerceAtLeast(0)))
        }
    }

    fun updateSitUps(delta: Int) {
        viewModelScope.launch {
            val current = todayWorkout.value
            repository.updateWorkout(current.copy(sitUps = (current.sitUps + delta).coerceAtLeast(0)))
        }
    }

    fun updateSquats(delta: Int) {
        viewModelScope.launch {
            val current = todayWorkout.value
            repository.updateWorkout(current.copy(squats = (current.squats + delta).coerceAtLeast(0)))
        }
    }

    fun updateRunning(delta: Double) {
        viewModelScope.launch {
            val current = todayWorkout.value
            repository.updateWorkout(current.copy(runningKm = (current.runningKm + delta).coerceAtLeast(0.0)))
        }
    }

    fun updateWater(deltaMl: Int) {
        viewModelScope.launch {
            val current = todayWorkout.value
            repository.updateWorkout(current.copy(waterMl = (current.waterMl + deltaMl).coerceAtLeast(0)))
        }
    }
}
