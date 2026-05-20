package com.example.project100.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.data.local.entity.WorkoutEntity
import com.example.project100.ui.components.SystemHeader
import com.example.project100.ui.components.SystemPanel
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.SuccessGreen
import com.example.project100.ui.theme.WarningRed
import com.example.project100.viewmodel.HistoryViewModel
import com.example.project100.viewmodel.MainViewModel
import java.time.format.DateTimeFormatter

@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val history by historyViewModel.filteredWorkouts.collectAsState(initial = emptyList())
    val currentFilter by historyViewModel.filter.collectAsState()
    val userProfile by mainViewModel.userProfile.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SystemHeader(title = "PROJECT100")
        }

        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            text = "QUEST LOG",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "ANALYTICS ENGINE v4.02.1",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray,
                            fontSize = 8.sp
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("CURRENT RANK", fontSize = 8.sp, color = Color.Gray)
                        Text(
                            userProfile?.currentRank ?: "E",
                            fontSize = 10.sp,
                            color = NeonBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        item {
            HistoryFilters(
                selectedFilter = currentFilter,
                onFilterSelected = { historyViewModel.setFilter(it) }
            )
        }

        item {
            EfficiencyChart(history)
        }

        item {
            ActiveStreakPanel(streak = userProfile?.currentStreak ?: 0)
        }

        item {
            Text(
                text = "TEMPORAL LOGS",
                style = MaterialTheme.typography.labelSmall,
                color = NeonBlue,
                fontWeight = FontWeight.Bold
            )
        }

        items(history) { workout ->
            TemporalLogItem(workout)
        }

        item {
            MilestoneTimeline()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun EfficiencyChart(workouts: List<WorkoutEntity>) {
    SystemPanel(modifier = Modifier.fillMaxWidth().height(180.dp)) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("COMPLETION EFFICIENCY", fontSize = 10.sp, color = NeonBlue, fontWeight = FontWeight.Bold)
                val avg = if (workouts.isEmpty()) 0f else workouts.map { 
                    calculateEfficiency(it)
                }.average().toFloat()
                Text("AVG: ${(avg * 100).toInt()}%", fontSize = 10.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(24.dp))
            
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val lineCount = 5
                    for (i in 0..lineCount) {
                        val y = size.height * (i.toFloat() / lineCount)
                        drawLine(
                            color = Color.White.copy(alpha = 0.05f),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = 1f
                        )
                    }
                }
                
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val displayWorkouts = workouts.takeLast(7)
                    displayWorkouts.forEach { workout ->
                        val efficiency = calculateEfficiency(workout)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(20.dp)
                                    .fillMaxHeight(efficiency.coerceAtLeast(0.1f))
                                    .background(NeonBlue.copy(alpha = 0.5f))
                                    .border(1.dp, NeonBlue.copy(alpha = 0.8f))
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                workout.date.format(DateTimeFormatter.ofPattern("E")).uppercase(),
                                fontSize = 8.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

fun calculateEfficiency(workout: WorkoutEntity): Float {
    val p1 = (workout.pushUps / 100f).coerceIn(0f, 1f)
    val p2 = (workout.sitUps / 100f).coerceIn(0f, 1f)
    val p3 = (workout.squats / 100f).coerceIn(0f, 1f)
    val p4 = (workout.runningKm.toFloat() / 10f).coerceIn(0f, 1f)
    return (p1 + p2 + p3 + p4) / 4f
}

@Composable
fun HistoryFilters(selectedFilter: String, onFilterSelected: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf("WEEK", "MONTH", "YEAR").forEach { filter ->
            val isSelected = filter == selectedFilter
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, if (isSelected) NeonBlue else Color.DarkGray.copy(alpha = 0.5f))
                    .background(if (isSelected) NeonBlue.copy(alpha = 0.1f) else Color.Transparent)
                    .clickable { onFilterSelected(filter) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = filter,
                    fontSize = 10.sp,
                    color = if (isSelected) NeonBlue else Color.Gray,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun ActiveStreakPanel(streak: Int) {
    SystemPanel(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text("ACTIVE STREAK", fontSize = 10.sp, color = Color.Gray)
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "$streak",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("DAYS", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("NEXT MILESTONE: 30 DAYS", fontSize = 8.sp, color = NeonBlue, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(Color.DarkGray.copy(alpha = 0.3f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth((streak / 30f).coerceIn(0f, 1f))
                        .fillMaxHeight()
                        .background(NeonBlue)
                )
            }
        }
    }
}

@Composable
fun TemporalLogItem(workout: WorkoutEntity) {
    val formatter = DateTimeFormatter.ofPattern("dd.MMM")
    val isSuccess = workout.pushUps >= 100 && workout.sitUps >= 100 && workout.squats >= 100 && workout.runningKm >= 10.0
    val statusText = if (isSuccess) "SUCCESS" else "FAILURE"
    val statusColor = if (isSuccess) SuccessGreen else WarningRed
    val protocolName = when {
        workout.pushUps >= 100 && workout.sitUps >= 100 -> "IRON PROTOCOL"
        workout.runningKm >= 10.0 -> "VOID MEDITATION"
        else -> "DATA SYNTHESIS"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = workout.date.format(formatter).uppercase(),
            fontSize = 12.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(60.dp)
        )
        
        Text(
            text = if (isSuccess) protocolName else "SYSTEM REFRESH",
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Medium
        )

        Box(
            modifier = Modifier
                .border(1.dp, statusColor.copy(alpha = 0.5f))
                .background(statusColor.copy(alpha = 0.1f))
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = statusText,
                color = statusColor,
                fontSize = 8.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(1.dp, statusColor, androidx.compose.foundation.shape.CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isSuccess) "✓" else "✕",
                color = statusColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MilestoneTimeline() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(8.dp).background(NeonBlue))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "MILESTONE TIMELINE",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        
        MilestoneItem(
            day = "10 DAY STREAK",
            title = "\"Acolyte Protocol Initiated\"",
            date = "OCT 04",
            isCompleted = true
        )
        MilestoneItem(
            day = "20 DAY STREAK",
            title = "RANK UP: VANGUARD-III",
            date = "OCT 24",
            isCompleted = true,
            isRankUp = true
        )
        MilestoneItem(
            day = "30 DAY STREAK",
            title = "\"Ascension Event Alpha\"",
            date = "LOCKED",
            isCompleted = false
        )
    }
}

@Composable
fun MilestoneItem(day: String, title: String, date: String, isCompleted: Boolean, isRankUp: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .border(2.dp, if (isCompleted) NeonBlue else Color.DarkGray, androidx.compose.foundation.shape.CircleShape)
                    .background(if (isCompleted) NeonBlue else Color.Transparent, androidx.compose.foundation.shape.CircleShape)
            )
            Box(modifier = Modifier.width(1.dp).height(60.dp).background(if (isCompleted) NeonBlue.copy(alpha = 0.3f) else Color.DarkGray))
        }
        Spacer(modifier = Modifier.width(16.dp))
        SystemPanel(
            modifier = Modifier.weight(1f),
            borderColor = if (isCompleted) NeonBlue.copy(alpha = 0.3f) else Color.DarkGray.copy(alpha = 0.5f),
            showCorners = false
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = day,
                        fontSize = 10.sp,
                        color = if (isCompleted) NeonBlue else Color.Gray,
                        fontWeight = FontWeight.Black
                    )
                    Text(date, fontSize = 9.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = if (isCompleted) Color.White else Color.Gray,
                    fontWeight = FontWeight.Bold
                )
                if (isRankUp) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "UNLOCKED: SYSTEM OVERRIDE PERMISSION",
                        fontSize = 9.sp,
                        color = Color(0xFFBC00FF),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
