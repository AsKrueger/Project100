package com.example.project100.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.viewmodel.TrainingViewModel

@Composable
fun TrainingScreen(
    viewModel: TrainingViewModel = hiltViewModel()
) {
    val workout by viewModel.todayWorkout.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text(
                text = "DAILY QUEST",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = "ACTIVE MISSION: PHYSICAL CONDITIONING",
                style = MaterialTheme.typography.labelSmall,
                color = NeonBlue
            )
        }

        item {
            ExerciseCounter(
                label = "PUSH-UPS",
                current = workout.pushUps,
                goal = 100,
                onAdd = { viewModel.updatePushUps(it) }
            )
        }

        item {
            ExerciseCounter(
                label = "SIT-UPS",
                current = workout.sitUps,
                goal = 100,
                onAdd = { viewModel.updateSitUps(it) }
            )
        }

        item {
            ExerciseCounter(
                label = "SQUATS",
                current = workout.squats,
                goal = 100,
                onAdd = { viewModel.updateSquats(it) }
            )
        }

        item {
            ExerciseCounter(
                label = "RUNNING (KM)",
                current = workout.runningKm.toInt(),
                goal = 10,
                isDecimal = true,
                onAdd = { viewModel.updateRunning(it.toDouble()) }
            )
        }
        
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ExerciseCounter(
    label: String,
    current: Int,
    goal: Int,
    isDecimal: Boolean = false,
    onAdd: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .border(1.dp, NeonBlue.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, fontWeight = FontWeight.Bold, color = Color.White)
            Text(
                text = "$current / $goal",
                color = NeonBlue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TrainingButton(text = "+1", onClick = { onAdd(1) }, modifier = Modifier.weight(1f))
            TrainingButton(text = "+10", onClick = { onAdd(10) }, modifier = Modifier.weight(1f))
            TrainingButton(text = "-1", onClick = { onAdd(-1) }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TrainingButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = NeonBlue
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, NeonBlue.copy(alpha = 0.5f))
    ) {
        Text(text, fontSize = 12.sp)
    }
}
