package com.example.project100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project100.data.local.entity.BodyMetricsEntity
import com.example.project100.data.repository.BodyMetricsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class BodyMetricsViewModel @Inject constructor(
    private val repository: BodyMetricsRepository
) : ViewModel() {

    val allMetrics: StateFlow<List<BodyMetricsEntity>> = repository.getAllMetrics()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateMetrics(
        weight: Double,
        bodyFat: Double,
        chest: Double,
        waist: Double,
        arms: Double,
        legs: Double
    ) {
        viewModelScope.launch {
            val today = LocalDate.now()
            val metrics = BodyMetricsEntity(
                date = today,
                weight = weight,
                bodyFat = bodyFat,
                chest = chest,
                waist = waist,
                arms = arms,
                legs = legs
            )
            repository.saveMetrics(metrics)
        }
    }
}
