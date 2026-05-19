package com.example.project100.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.ElectricPurple
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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            HeaderSection(userProfile?.username ?: "RECRUIT", userProfile?.currentRank ?: "E")
        }

        item {
            StatusCard(
                streak = userProfile?.currentStreak ?: 0,
                progress = calculateTotalProgress(workout.pushUps, workout.sitUps, workout.squats, workout.runningKm)
            )
        }

        item {
            TrainingGrid(
                pushUps = workout.pushUps,
                sitUps = workout.sitUps,
                squats = workout.squats,
                running = workout.runningKm
            )
        }
        
        item {
            SystemMessage()
        }
    }
}

@Composable
fun HeaderSection(name: String, rank: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "PLAYER: $name",
                style = MaterialTheme.typography.labelSmall,
                color = NeonBlue
            )
            Text(
                text = "SYSTEM DASHBOARD",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
        
        Box(
            modifier = Modifier
                .size(60.dp)
                .border(2.dp, NeonBlue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = rank,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = NeonBlue
            )
        }
    }
}

@Composable
fun StatusCard(streak: Int, progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(DarkGray80, Color.Black)
                )
            )
            .border(1.dp, NeonBlue.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("CURRENT STREAK", style = MaterialTheme.typography.labelSmall)
                Text(
                    "$streak DAYS",
                    style = MaterialTheme.typography.headlineLarge,
                    color = NeonBlue
                )
            }
            
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(100.dp),
                    color = NeonBlue,
                    strokeWidth = 8.dp,
                    trackColor = NeonBlue.copy(alpha = 0.1f)
                )
                Text(
                    "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TrainingGrid(pushUps: Int, sitUps: Int, squats: Int, running: Double) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TrainingItem("PUSH-UPS", pushUps, 100)
        TrainingItem("SIT-UPS", sitUps, 100)
        TrainingItem("SQUATS", squats, 100)
        TrainingItem("RUNNING", running.toInt(), 10, "KM")
    }
}

@Composable
fun TrainingItem(label: String, current: Int, goal: Int, unit: String = "") {
    val progress = (current.toFloat() / goal).coerceIn(0f, 1f)
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Text("$current / $goal $unit", style = MaterialTheme.typography.labelSmall, color = NeonBlue)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.Gray.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .background(NeonBlue)
            )
        }
    }
}

@Composable
fun SystemMessage() {
    val messages = listOf(
        "THE SYSTEM IS WATCHING.",
        "DISCIPLINE CREATES STRENGTH.",
        "FAILURE HAS CONSEQUENCES.",
        "DAILY QUEST UPDATED.",
        "YOUR BODY IS EVOLVING."
    )
    Text(
        text = "> ${messages.random()}",
        style = MaterialTheme.typography.labelSmall,
        color = ElectricPurple,
        modifier = Modifier.padding(top = 24.dp)
    )
}

fun calculateTotalProgress(pushUps: Int, sitUps: Int, squats: Int, running: Double): Float {
    val p1 = (pushUps / 100f).coerceAtMost(1f)
    val p2 = (sitUps / 100f).coerceAtMost(1f)
    val p3 = (squats / 100f).coerceAtMost(1f)
    val p4 = (running.toFloat() / 10f).coerceAtMost(1f)
    return (p1 + p2 + p3 + p4) / 4f
}

private val DarkGray80 = Color(0xFF121212)
