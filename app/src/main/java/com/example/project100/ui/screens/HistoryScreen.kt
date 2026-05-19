package com.example.project100.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.data.local.entity.WorkoutEntity
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.WarningRed
import com.example.project100.viewmodel.HistoryViewModel
import java.time.format.DateTimeFormatter

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val history by viewModel.allWorkouts.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "MISSION LOGS",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Text(
            text = "SYSTEM RECORD ARCHIVE",
            style = MaterialTheme.typography.labelSmall,
            color = NeonBlue
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(history) { workout ->
                HistoryItem(workout)
            }
        }
    }
}

@Composable
fun HistoryItem(workout: WorkoutEntity) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val isSuccess = workout.pushUps >= 100 && workout.sitUps >= 100 && workout.squats >= 100 && workout.runningKm >= 10.0
    val borderColor = if (isSuccess) NeonBlue else WarningRed

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .border(1.dp, borderColor.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = workout.date.format(formatter),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = if (isSuccess) "CLEAR" else "FAILED",
                color = borderColor,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "P:${workout.pushUps} S:${workout.sitUps} SQ:${workout.squats} R:${workout.runningKm}",
                fontSize = 10.sp,
                color = Color.Gray
            )
        }
    }
}
