package com.example.project100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project100.data.local.entity.WorkoutEntity
import com.example.project100.data.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

data class HistorySlot(
    val date: LocalDate,
    val workout: WorkoutEntity?,
    val isFuture: Boolean = false
)

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _filter = MutableStateFlow("WEEK")
    val filter: StateFlow<String> = _filter

    private val allWorkouts: StateFlow<List<WorkoutEntity>> = repository.getAllWorkouts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val historySlots: StateFlow<List<HistorySlot>> = combine(allWorkouts, _filter) { workouts, filter ->
        val now = LocalDate.now()
        val workoutMap = workouts.associateBy { it.date }
        
        when (filter) {
            "WEEK" -> {
                // Start of current week (Monday)
                val startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
                (0..6).map { days ->
                    val date = startOfWeek.plusDays(days.toLong())
                    HistorySlot(date, workoutMap[date], date.isAfter(now))
                }
            }
            "MONTH" -> {
                val startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth())
                val daysInMonth = YearMonth.from(now).lengthOfMonth()
                (0 until daysInMonth).map { days ->
                    val date = startOfMonth.plusDays(days.toLong())
                    HistorySlot(date, workoutMap[date], date.isAfter(now))
                }
            }
            "YEAR" -> {
                // For year, we return one slot per month (using first day of month)
                val startOfYear = now.with(TemporalAdjusters.firstDayOfYear())
                (0..11).map { months ->
                    val date = startOfYear.plusMonths(months.toLong())
                    // For month view in list, maybe aggregate? 
                    // But user wants to see "all months until current".
                    // Let's just provide the first day of each month for now.
                    HistorySlot(date, workoutMap[date], date.isAfter(now))
                }
            }
            else -> emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setFilter(newFilter: String) {
        _filter.value = newFilter
    }
}
