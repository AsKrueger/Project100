package com.example.project100.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.system.SystemMessages
import com.example.project100.system.calculateTotalProgress
import com.example.project100.ui.components.*
import com.example.project100.ui.theme.ElectricPurple
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.WarningRed
import com.example.project100.viewmodel.MainViewModel
import com.example.project100.viewmodel.TrainingViewModel

@Composable
fun DashboardScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    trainingViewModel: TrainingViewModel = hiltViewModel()
) {
    val workout by trainingViewModel.todayWorkout.collectAsState()
    val userProfile by mainViewModel.userProfile.collectAsState()
    val isLocked by mainViewModel.isLocked.collectAsState()
    
    val totalProgress = calculateTotalProgress(workout.pushUps, workout.sitUps, workout.squats, workout.runningKm)
    
    // Generate a message that only changes when progress changes or on launch
    val systemMessage = remember(totalProgress, isLocked) {
        SystemMessages.getMessageForProgress(totalProgress, isLocked)
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF050505))) {
        GridBackground()
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                SystemHeader(title = "PROJECT100")
            }

            item {
                SystemMessageBanner(message = systemMessage)
            }

            item {
                RankPanel(rank = userProfile?.currentRank ?: "E")
            }

            item {
                OperationalStreakPanel(days = userProfile?.currentStreak ?: 0)
            }

            item {
                MainSystemStatusRing(progress = totalProgress)
            }

            item {
                ActionButtonsRow()
            }

            item {
                QuickStatsGrid(
                    pushUps = workout.pushUps,
                    sitUps = workout.sitUps,
                    squats = workout.squats,
                    running = workout.runningKm
                )
            }

            /* Hiding for now
            item {
                ActiveOperationsPanel()
            }
            
            item {
                UpgradePanel()
                Spacer(modifier = Modifier.height(32.dp))
            }
            */

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun RankPanel(rank: String) {
    SystemPanel(
        modifier = Modifier.fillMaxWidth(),
        borderColor = Color.White.copy(alpha = 0.05f),
        contentColor = Color.White.copy(alpha = 0.02f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("CURRENT CLEARANCE", style = MaterialTheme.typography.labelSmall, color = Color.Gray, fontSize = 9.sp, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.height(16.dp))
            
            Box(contentAlignment = Alignment.Center) {
                // Background circle decoration
                Canvas(modifier = Modifier.size(90.dp)) {
                    drawCircle(color = NeonBlue.copy(alpha = 0.1f), style = Stroke(1.dp.toPx()))
                }
                Text(rank, fontSize = 52.sp, fontWeight = FontWeight.Black, color = NeonBlue)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Text("RANK $rank", fontSize = 14.sp, color = NeonBlue, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            
            Spacer(modifier = Modifier.height(16.dp))
            // Progress bar below rank
            Box(modifier = Modifier.fillMaxWidth(0.7f).height(2.dp).background(Color.DarkGray)) {
                // Simple visual filler based on rank
                val fill = when(rank) {
                    "S" -> 1f
                    "A" -> 0.8f
                    "B" -> 0.6f
                    "C" -> 0.4f
                    "D" -> 0.2f
                    else -> 0.1f
                }
                Box(modifier = Modifier.fillMaxWidth(fill).fillMaxHeight().background(NeonBlue))
            }
        }
    }
}

@Composable
fun OperationalStreakPanel(days: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("OPERATIONAL STREAK", style = MaterialTheme.typography.labelSmall, color = Color.Gray, fontSize = 9.sp, letterSpacing = 1.sp)
        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(vertical = 4.dp)) {
            Text("$days", fontSize = 42.sp, fontWeight = FontWeight.Black, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("DAYS", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
        }
        
        // Segmented bar
        Row(
            modifier = Modifier.fillMaxWidth().height(6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(12) { index ->
                val isFilled = index < (days % 13) 
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(if (isFilled) NeonBlue else Color.White.copy(alpha = 0.1f))
                )
            }
        }
    }
}

@Composable
fun MainSystemStatusRing(progress: Float) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 10.dp)) {
        // Tick marks background
        Canvas(modifier = Modifier.size(240.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.minDimension / 2
            for (i in 0 until 100) {
                val angle = (i * 3.6f - 90f) * (Math.PI / 180).toFloat()
                val lineLength = if (i % 5 == 0) 10.dp.toPx() else 5.dp.toPx()
                val start = Offset(
                    center.x + (radius - lineLength) * kotlin.math.cos(angle),
                    center.y + (radius - lineLength) * kotlin.math.sin(angle)
                )
                val end = Offset(
                    center.x + radius * kotlin.math.cos(angle),
                    center.y + radius * kotlin.math.sin(angle)
                )
                drawLine(
                    color = Color.White.copy(alpha = 0.1f),
                    start = start,
                    end = end,
                    strokeWidth = 1.dp.toPx()
                )
            }
        }
        
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(190.dp),
            color = NeonBlue,
            strokeWidth = 6.dp,
            trackColor = NeonBlue.copy(alpha = 0.05f),
            strokeCap = StrokeCap.Butt
        )
        
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("000.00.00", fontSize = 9.sp, color = Color.Gray, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
            Spacer(modifier = Modifier.height(12.dp))
            Text("SYSTEM STATUS", style = MaterialTheme.typography.labelSmall, color = Color.Gray, fontSize = 9.sp)
            Text("${(progress * 100).toInt()}%", fontSize = 56.sp, fontWeight = FontWeight.Black, color = Color.White)
            Text(if (progress >= 1f) "OPTIMIZED" else "STABILIZING...", fontSize = 11.sp, color = NeonBlue, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text("DATA_EXTRACT_S#882", fontSize = 8.sp, color = Color.Gray.copy(alpha = 0.5f))
        }
    }
}

@Composable
fun ActionButtonsRow() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        SystemButton(
            text = "INITIATE PROTOCOL",
            onClick = { /* TODO: Navigate to training */ },
            modifier = Modifier.weight(1f)
        )
        SystemButton(
            text = "ABORT TASK",
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f),
            containerColor = WarningRed,
            isOutline = true
        )
    }
}

@Composable
fun QuickStatsGrid(pushUps: Int, sitUps: Int, squats: Int, running: Double) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatItem(label = "PUSH-UPS", current = pushUps, goal = 100, color = NeonBlue, modifier = Modifier.weight(1f))
            StatItem(label = "SIT-UPS", current = sitUps, goal = 100, color = ElectricPurple, modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatItem(label = "SQUATS", current = squats, goal = 100, color = Color.Cyan, modifier = Modifier.weight(1f))
            StatItem(label = "RUNNING", current = running.toInt(), goal = 10, unit = "KM", color = WarningRed, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StatItem(label: String, current: Int, goal: Int, color: Color, modifier: Modifier = Modifier, unit: String = "") {
    val progress = (current.toFloat() / goal).coerceIn(0f, 1f)
    SystemPanel(
        modifier = modifier,
        borderColor = Color.White.copy(alpha = 0.05f),
        contentColor = Color.White.copy(alpha = 0.02f),
        cornerColor = color.copy(alpha = 0.5f)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(45.dp)) {
                CircularProgressIndicator(
                    progress = { progress },
                    color = color,
                    strokeWidth = 3.dp,
                    trackColor = color.copy(alpha = 0.1f)
                )
                // Small indicator dot
                Box(modifier = Modifier.size(4.dp).background(color.copy(alpha = 0.8f), CircleShape))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(label, fontSize = 9.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                Text("$current/$goal$unit", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ActiveOperationsPanel() {
    SystemPanel(modifier = Modifier.fillMaxWidth(), borderColor = Color.White.copy(alpha = 0.05f)) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("ACTIVE OPERATIONS", fontSize = 9.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text("LIVE_FEED_ON", fontSize = 9.sp, color = NeonBlue, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            
            val logs = listOf(
                "[08:00] MORNING_CALIBRATION_COMPLETE" to NeonBlue,
                "[12:45] NUTRITION_PROTOCOL_ACKNOWLEDGED" to NeonBlue,
                "[18:22] CARDIO_DEFICIT_DETECTED. RECTIFY." to WarningRed
            )
            
            logs.forEach { (log, color) ->
                Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (color == WarningRed) {
                        Box(modifier = Modifier.size(4.dp).background(WarningRed, CircleShape))
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(
                        text = log,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                        fontSize = 10.sp,
                        color = color.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun UpgradePanel() {
    SystemPanel(
        modifier = Modifier.fillMaxWidth(),
        borderColor = NeonBlue.copy(alpha = 0.2f),
        contentColor = NeonBlue.copy(alpha = 0.02f)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Technical visual graphic
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(1.dp, NeonBlue.copy(alpha = 0.3f))
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val sw = 1.dp.toPx()
                    // Grid inside the box
                    for(i in 1..4) {
                        drawLine(NeonBlue.copy(0.1f), Offset(i * size.width/5, 0f), Offset(i * size.width/5, size.height), sw)
                        drawLine(NeonBlue.copy(0.1f), Offset(0f, i * size.height/5), Offset(size.width, i * size.height/5), sw)
                    }
                    // Tech cross
                    drawLine(NeonBlue.copy(0.3f), Offset(0f, 0f), Offset(size.width, size.height), sw)
                    // Circle in middle
                    drawCircle(NeonBlue.copy(0.2f), radius = size.width/4, style = Stroke(sw))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("UPGRADE AVAILABLE", fontWeight = FontWeight.Black, color = Color.White, fontSize = 12.sp)
                Text("Unlock Advanced HUD Augmentations for Rank C.", color = Color.Gray, fontSize = 10.sp, lineHeight = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                SystemButton(
                    text = "DATA_VAULT_OPEN",
                    onClick = { },
                    modifier = Modifier.height(30.dp),
                    containerColor = NeonBlue.copy(alpha = 0.2f),
                    contentColor = NeonBlue
                )
            }
        }
    }
}
