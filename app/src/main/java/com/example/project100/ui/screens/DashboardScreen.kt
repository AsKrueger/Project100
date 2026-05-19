package com.example.project100.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.project100.ui.components.SystemHeader
import com.example.project100.ui.components.SystemMessageBanner
import com.example.project100.ui.components.SystemPanel
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SystemHeader(title = "PROJECT100")
        }

        item {
            SystemMessageBanner(message = "THE SYSTEM IS WATCHING. EVOLVE OR PERISH.")
        }

        item {
            RankSection(rank = userProfile?.currentRank ?: "E")
        }

        item {
            OperationalStreak(days = userProfile?.currentStreak ?: 0)
        }

        item {
            SystemStatusRing(progress = calculateTotalProgress(workout.pushUps, workout.sitUps, workout.squats, workout.runningKm))
        }

        item {
            ActionButtons()
        }

        item {
            QuickStatsGrid(
                pushUps = workout.pushUps,
                sitUps = workout.sitUps,
                squats = workout.squats,
                running = workout.runningKm
            )
        }

        item {
            ActiveOperationsLog()
        }
        
        item {
            UpgradeCard()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun RankSection(rank: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("CURRENT CLEARANCE", style = MaterialTheme.typography.labelSmall, color = Color.Gray, fontSize = 10.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            // Rank outer ring
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = NeonBlue.copy(alpha = 0.2f),
                    radius = size.minDimension / 2,
                    style = Stroke(width = 1.dp.toPx())
                )
                drawCircle(
                    color = NeonBlue,
                    radius = (size.minDimension / 2) - 10.dp.toPx(),
                    style = Stroke(width = 2.dp.toPx())
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(rank, fontSize = 56.sp, fontWeight = FontWeight.Black, color = NeonBlue)
                Text("RANK $rank", fontSize = 12.sp, color = NeonBlue, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            }
        }
    }
}

@Composable
fun OperationalStreak(days: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("OPERATIONAL STREAK", style = MaterialTheme.typography.labelSmall, color = Color.White, fontSize = 10.sp)
        Row(verticalAlignment = Alignment.Bottom) {
            Text("$days", fontSize = 42.sp, fontWeight = FontWeight.Black, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("DAYS", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
        }
        // Segmented progress bar
        Row(
            modifier = Modifier.fillMaxWidth().height(6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(10) { index ->
                val isFilled = index < (days % 11) // Dummy logic for segments
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(if (isFilled) NeonBlue else Color.DarkGray.copy(alpha = 0.3f))
                )
            }
        }
    }
}

@Composable
fun SystemStatusRing(progress: Float) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(240.dp)) {
        // Decorative outer circles
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = NeonBlue.copy(alpha = 0.05f),
                radius = size.minDimension / 2,
                style = Stroke(width = 1.dp.toPx())
            )
        }
        
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(180.dp),
            color = NeonBlue,
            strokeWidth = 4.dp,
            trackColor = NeonBlue.copy(alpha = 0.1f),
            strokeCap = StrokeCap.Butt
        )
        
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("DATA_EXTRACT_54582", fontSize = 8.sp, color = NeonBlue.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(4.dp))
            Text("SYSTEM STATUS", style = MaterialTheme.typography.labelSmall, color = Color.Gray, fontSize = 10.sp)
            Text("${(progress * 100).toInt()}%", fontSize = 56.sp, fontWeight = FontWeight.Black, color = Color.White)
            Text("STABILIZING...", fontSize = 12.sp, color = NeonBlue, fontWeight = FontWeight.Bold)
        }
        
        // Circular markers
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = 100.dp.toPx()
            // Draw a small dot on the ring as an accent
            drawCircle(
                color = NeonBlue,
                radius = 4.dp.toPx(),
                center = Offset(center.x + radius, center.y)
            )
        }
    }
}

@Composable
fun ActionButtons() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f).height(45.dp),
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(containerColor = NeonBlue, contentColor = Color.Black)
        ) {
            Text("INITIATE PROTOCOL", fontWeight = FontWeight.Black, fontSize = 12.sp)
        }
        OutlinedButton(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f).height(45.dp),
            shape = MaterialTheme.shapes.extraSmall,
            border = androidx.compose.foundation.BorderStroke(1.dp, WarningRed),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = WarningRed)
        ) {
            Text("ABORT TASK", fontWeight = FontWeight.Black, fontSize = 12.sp)
        }
    }
}

@Composable
fun QuickStatsGrid(pushUps: Int, sitUps: Int, squats: Int, running: Double) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        MiniStatRing("PUSH-UPS", pushUps, 100)
        MiniStatRing("SIT-UPS", sitUps, 100)
        MiniStatRing("SQUATS", squats, 100)
        MiniStatRing("RUNNING", running.toInt(), 10)
    }
}

@Composable
fun MiniStatRing(label: String, current: Int, goal: Int) {
    val progress = (current.toFloat() / goal).coerceIn(0f, 1f)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(70.dp)) {
            CircularProgressIndicator(
                progress = { progress },
                color = if (label == "SQUATS") ElectricPurple else NeonBlue,
                strokeWidth = 3.dp,
                trackColor = Color.DarkGray.copy(alpha = 0.3f)
            )
            Text("${current}", fontSize = 14.sp, fontWeight = FontWeight.Black, color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
        Text("$current/$goal", fontSize = 10.sp, color = Color.White)
    }
}

@Composable
fun ActiveOperationsLog() {
    SystemPanel(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("ACTIVE OPERATIONS", style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
                Text("LIVE_FEED_ON", style = MaterialTheme.typography.labelSmall, color = NeonBlue, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            val logs = listOf(
                "[08:00] MORNING_CALIBRATION_COMPLETE",
                "[12:45] NUTRITION_PROTOCOL_ACKNOWLEDGED",
                "[18:22] CARDIO_DEFICIT_DETECTED. RECTIFY."
            )
            logs.forEach { log ->
                val color = if (log.contains("RECTIFY")) WarningRed else Color.Cyan
                Text(
                    text = log,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = color.copy(alpha = 0.9f),
                    modifier = Modifier.padding(vertical = 4.dp),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun UpgradeCard() {
    SystemPanel(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(NeonBlue.copy(alpha = 0.1f))
                    .border(1.dp, NeonBlue)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for an icon/graphic
                Box(modifier = Modifier.fillMaxSize().border(1.dp, NeonBlue.copy(alpha = 0.5f)))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("UPGRADE AVAILABLE", fontWeight = FontWeight.Black, color = Color.White, fontSize = 14.sp)
                Text("Unlock Advanced HUD Augmentations for Rank C.", color = Color.Gray, fontSize = 11.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("VIEW_VAULT_OPEN", color = NeonBlue, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

fun calculateTotalProgress(pushUps: Int, sitUps: Int, squats: Int, running: Double): Float {
    val p1 = (pushUps / 100f).coerceAtMost(1f)
    val p2 = (sitUps / 100f).coerceAtMost(1f)
    val p3 = (squats / 100f).coerceAtMost(1f)
    val p4 = (running.toFloat() / 10f).coerceAtMost(1f)
    return (p1 + p2 + p3 + p4) / 4f
}
