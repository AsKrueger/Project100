package com.example.project100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project100.data.local.entity.WorkoutEntity
import com.example.project100.data.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

data class HistorySlot(
    val date: LocalDate,
    val workout: WorkoutEntity?,
    val label: String,
    val isHeader: Boolean = false,
    val isFuture: Boolean = false,
    val monthSuccessRate: Float = 0f
)

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _filter = MutableStateFlow("WEEK")
    val filter: StateFlow<String> = _filter

    val allWorkouts: StateFlow<List<WorkoutEntity>> = repository.getAllWorkouts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val historySlots: StateFlow<List<HistorySlot>> = combine(allWorkouts, _filter) { workouts, filter ->
        val now = LocalDate.now()
        val workoutMap = workouts.associateBy { it.date }
        
        when (filter) {
            "WEEK" -> {
                // Show the FULL current week (Monday to Sunday)
                val startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                (0..6).map { i ->
                    val date = startOfWeek.plusDays(i.toLong())
                    HistorySlot(
                        date = date,
                        workout = workoutMap[date],
                        label = date.dayOfWeek.name.take(3),
                        isFuture = date.isAfter(now)
                    )
                }.reversed()
            }
            "MONTH" -> {
                // Show the FULL current month
                val startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth())
                val daysInMonth = YearMonth.from(now).lengthOfMonth()
                (0 until daysInMonth).map { i ->
                    val date = startOfMonth.plusDays(i.toLong())
                    HistorySlot(
                        date = date,
                        workout = workoutMap[date],
                        label = date.dayOfMonth.toString(),
                        isFuture = date.isAfter(now)
                    )
                }.reversed()
            }
            "YEAR" -> {
                // Monthly summary for the FULL year
                (1..12).map { m ->
                    val firstDay = LocalDate.of(now.year, m, 1)
                    val lastDay = YearMonth.of(now.year, m).atEndOfMonth()
                    
                    val monthWorkouts = workouts.filter { 
                        (it.date.isAfter(firstDay) || it.date.isEqual(firstDay)) && 
                        (it.date.isBefore(lastDay) || it.date.isEqual(lastDay))
                    }
                    
                    val successCount = monthWorkouts.count { 
                        it.pushUps >= 100 && it.sitUps >= 100 && it.squats >= 100 && it.runningKm >= 10.0 
                    }
                    
                    val daysInMonthTotal = lastDay.dayOfMonth
                    val rate = if (daysInMonthTotal > 0) successCount.toFloat() / daysInMonthTotal else 0f

                    HistorySlot(
                        date = firstDay,
                        workout = if (successCount > 0) monthWorkouts.first() else null,
                        label = firstDay.month.name.take(3),
                        monthSuccessRate = rate,
                        isFuture = firstDay.isAfter(now.with(TemporalAdjusters.firstDayOfMonth()))
                    )
                }.reversed()
            }
            else -> emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setFilter(newFilter: String) {
        _filter.value = newFilter
    }
}
