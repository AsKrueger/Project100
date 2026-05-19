package com.example.project100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project100.data.local.entity.WorkoutEntity
import com.example.project100.data.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _filter = MutableStateFlow("WEEK")
    val filter: StateFlow<String> = _filter

    val allWorkouts: StateFlow<List<WorkoutEntity>> = repository.getAllWorkouts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val filteredWorkouts: StateFlow<List<WorkoutEntity>> = combine(allWorkouts, _filter) { workouts, filter ->
        val now = LocalDate.now()
        when (filter) {
            "WEEK" -> workouts.filter { it.date.isAfter(now.minusWeeks(1)) }
            "MONTH" -> workouts.filter { it.date.isAfter(now.minusMonths(1)) }
            "YEAR" -> workouts.filter { it.date.isAfter(now.minusYears(1)) }
            else -> workouts
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setFilter(newFilter: String) {
        _filter.value = newFilter
    }
}
