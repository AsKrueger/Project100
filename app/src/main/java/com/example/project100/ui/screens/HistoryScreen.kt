package com.example.project100.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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
import com.example.project100.viewmodel.HistorySlot
import com.example.project100.viewmodel.HistoryViewModel
import com.example.project100.viewmodel.MainViewModel
import java.time.format.DateTimeFormatter

@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val slots by historyViewModel.historySlots.collectAsState()
    val currentFilter by historyViewModel.filter.collectAsState()
    val userProfile by mainViewModel.userProfile.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF050505))) {
        // Subtle background decoration
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = NeonBlue.copy(alpha = 0.03f),
                radius = size.width,
                center = Offset(size.width, 0f)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                SystemHeader(title = "PROJECT100")
            }

            item {
                HeaderSection(userProfile?.currentRank ?: "E")
            }

            item {
                HistoryFilters(
                    selectedFilter = currentFilter,
                    onFilterSelected = { historyViewModel.setFilter(it) }
                )
            }

            item {
                EfficiencyChart(slots, currentFilter)
            }

            item {
                ActiveStreakPanel(streak = userProfile?.currentStreak ?: 0)
            }

            item {
                LogsHeader()
            }

            items(slots) { slot ->
                TemporalSlotItem(slot, currentFilter)
            }
            
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun HeaderSection(rank: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            Text(
                text = "QUEST LOG",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp
            )
            Text(
                text = "ANALYTICS_SYSTEM_v4.02",
                style = MaterialTheme.typography.labelSmall,
                color = NeonBlue.copy(alpha = 0.5f),
                fontSize = 11.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("CLEARANCE", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
            Text(
                rank,
                fontSize = 24.sp,
                color = NeonBlue,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
private fun LogsHeader() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
        Box(modifier = Modifier.size(width = 3.dp, height = 18.dp).background(NeonBlue))
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "CHRONOLOGICAL LOGS",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            letterSpacing = 2.sp
        )
    }
}

@Composable
fun EfficiencyChart(slots: List<HistorySlot>, filter: String) {
    SystemPanel(
        modifier = Modifier.fillMaxWidth().height(250.dp),
        borderColor = NeonBlue.copy(alpha = 0.15f)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("COMPLETION EFFICIENCY", fontSize = 12.sp, color = NeonBlue, fontWeight = FontWeight.Bold)
                    Text("DATA_VISUALIZATION_ACTIVE", fontSize = 9.sp, color = Color.Gray)
                }
                val workouts = slots.mapNotNull { it.workout }
                val avg = if (workouts.isEmpty()) 0f else workouts.map { 
                    calculateEfficiency(it)
                }.average().toFloat()
                
                Column(horizontalAlignment = Alignment.End) {
                    Text("AVG_SCORE", fontSize = 9.sp, color = Color.Gray)
                    Text("${(avg * 100).toInt()}%", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Black)
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Box(modifier = Modifier.fillMaxSize()) {
                // Enhanced grid background
                Canvas(modifier = Modifier.fillMaxSize().alpha(0.1f)) {
                    val lines = 5
                    for (i in 0 until lines) {
                        val y = size.height * (i.toFloat() / (lines - 1))
                        drawLine(Color.White, Offset(0f, y), Offset(size.width, y), 1.dp.toPx())
                    }
                }
                
                val scrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .horizontalScroll(scrollState)
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(if (filter == "MONTH") 10.dp else 20.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    val displayList = slots.reversed() // ViewModel sends it ordered for list, chart usually needs chronological if reversed was used.
                    
                    displayList.forEach { slot ->
                        val efficiency = slot.workout?.let { calculateEfficiency(it) } ?: 0f
                        val isFuture = slot.isFuture
                        
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxHeight().width(if (filter == "MONTH") 14.dp else 40.dp)
                        ) {
                            Box(
                                modifier = Modifier.weight(1f).fillMaxWidth(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                // Glow effect for active bars
                                if (efficiency > 0) {
                                    Box(
                                        modifier = Modifier
                                            .width(if (filter == "MONTH") 8.dp else 20.dp)
                                            .fillMaxHeight(efficiency.coerceAtLeast(0.05f))
                                            .blur(10.dp)
                                            .background(NeonBlue.copy(alpha = 0.25f))
                                    )
                                }
                                
                                // Data Bar
                                Box(
                                    modifier = Modifier
                                        .width(if (filter == "MONTH") 10.dp else 28.dp)
                                        .fillMaxHeight(efficiency.coerceAtLeast(0.04f))
                                        .clip(RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = if (slot.workout != null) 
                                                    listOf(NeonBlue, NeonBlue.copy(alpha = 0.1f))
                                                else if (isFuture)
                                                    listOf(Color.White.copy(alpha = 0.05f), Color.Transparent)
                                                else
                                                    listOf(Color.DarkGray.copy(alpha = 0.2f), Color.Transparent)
                                            )
                                        )
                                        .then(
                                            if (slot.workout != null) 
                                                Modifier.border(0.5.dp, NeonBlue.copy(alpha = 0.4f), RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                                            else Modifier
                                        )
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(10.dp))
                            
                            Text(
                                text = slot.label.uppercase(),
                                fontSize = 9.sp,
                                color = if (slot.workout != null) Color.White else Color.Gray.copy(alpha = 0.6f),
                                fontWeight = if (slot.workout != null) FontWeight.Bold else FontWeight.Normal,
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                            )
                        }
                    }
                }
                
                // Final visual indicator line at 100%
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(NeonBlue.copy(alpha = 0.05f)).align(Alignment.TopCenter))
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
        modifier = Modifier.fillMaxWidth().height(45.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        listOf("WEEK", "MONTH", "YEAR").forEach { filter ->
            val isSelected = filter == selectedFilter
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(2.dp))
                    .background(if (isSelected) NeonBlue.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.02f))
                    .border(
                        1.dp, 
                        if (isSelected) NeonBlue else Color.White.copy(alpha = 0.05f),
                        RoundedCornerShape(2.dp)
                    )
                    .clickable { onFilterSelected(filter) },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = filter,
                        fontSize = 12.sp,
                        color = if (isSelected) NeonBlue else Color.Gray,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp
                    )
                    if (isSelected) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(modifier = Modifier.size(width = 20.dp, height = 1.dp).background(NeonBlue))
                    }
                }
            }
        }
    }
}

@Composable
fun ActiveStreakPanel(streak: Int) {
    SystemPanel(
        modifier = Modifier.fillMaxWidth(),
        borderColor = Color.White.copy(alpha = 0.08f)
    ) {
        Column {
            Text("STREAK_STABILITY", fontSize = 11.sp, color = Color.Gray, fontWeight = FontWeight.Bold, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(vertical = 4.dp)) {
                Text(
                    text = String.format("%02d", streak),
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("CONSECUTIVE_DAYS", fontSize = 13.sp, color = NeonBlue, modifier = Modifier.padding(bottom = 12.dp), fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("NEXT MILESTONE: 30 DAYS", fontSize = 10.sp, color = Color.Gray)
                Text("${(streak/30f * 100).toInt()}%", fontSize = 10.sp, color = NeonBlue, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(6.dp))
            
            // Modern progress bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(Color.White.copy(alpha = 0.05f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth((streak / 30f).coerceIn(0f, 1f))
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(NeonBlue.copy(alpha = 0.4f), NeonBlue)
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun TemporalSlotItem(slot: HistorySlot, filter: String) {
    val workout = slot.workout
    val isSuccess = workout != null && workout.pushUps >= 100 && workout.sitUps >= 100 && workout.squats >= 100 && workout.runningKm >= 10.0
    val isFuture = slot.isFuture
    
    val statusText = if (isFuture) "PENDING" else if (workout == null) "NO_DATA" else if (isSuccess) "SUCCESS" else "FAILURE"
    val statusColor = if (isFuture) Color.Gray.copy(alpha = 0.3f) else if (workout == null) Color.Gray else if (isSuccess) SuccessGreen else WarningRed
    
    val dateDisplay = if (filter == "YEAR") slot.date.format(DateTimeFormatter.ofPattern("MMM")) 
                      else slot.date.format(DateTimeFormatter.ofPattern("dd.MMM"))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .alpha(if (isFuture) 0.4f else 1f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Date Column
        Column(modifier = Modifier.width(75.dp)) {
            Text(
                text = dateDisplay.uppercase(),
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
            )
            Text(
                text = slot.date.dayOfWeek.name.take(3),
                fontSize = 10.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }
        
        // Protocol Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (workout != null && isSuccess) "IRON PROTOCOL" else if (workout != null) "RECOVERY MODE" else if (isFuture) "FUTURE_TASK" else "IDLE_SYSTEM",
                fontSize = 14.sp,
                color = if (workout != null) Color.White else Color.Gray,
                fontWeight = FontWeight.Bold
            )
            if (workout != null) {
                Text(
                    text = "EFFICIENCY: ${(calculateEfficiency(workout) * 100).toInt()}%",
                    fontSize = 10.sp,
                    color = NeonBlue.copy(alpha = 0.8f),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Status Badge
        Box(
            modifier = Modifier
                .border(1.dp, statusColor.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
                .background(statusColor.copy(alpha = 0.05f))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = statusText,
                color = statusColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // Circular Status Icon
        Box(
            modifier = Modifier
                .size(28.dp)
                .border(1.dp, statusColor.copy(alpha = 0.4f), CircleShape)
                .background(if (isSuccess) SuccessGreen.copy(alpha = 0.1f) else Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isSuccess) "✓" else if (workout != null) "✕" else if (isFuture) "○" else "—",
                color = statusColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}
