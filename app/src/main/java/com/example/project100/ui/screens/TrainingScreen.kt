package com.example.project100.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.system.calculateTotalProgress
import com.example.project100.ui.components.*
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.WarningRed
import com.example.project100.viewmodel.TrainingViewModel
import java.util.Locale

@Composable
fun TrainingMetricsPanel(completion: Float) {
    SystemPanel(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TOTAL_COMPLETION",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${(completion * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall,
                    color = NeonBlue,
                    fontWeight = FontWeight.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { completion },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = NeonBlue,
                trackColor = NeonBlue.copy(alpha = 0.1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "SYSTEM_OPTIMIZATION: ${if (completion >= 1f) "OPTIMAL" else "IN_PROGRESS"}",
                style = MaterialTheme.typography.labelSmall,
                color = if (completion >= 1f) Color.Green else Color.Gray,
                fontSize = 9.sp
            )
        }
    }
}

@Composable
fun TrainingScreen(
    viewModel: TrainingViewModel = hiltViewModel()
) {
    val workout by viewModel.todayWorkout.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF050505))) {
        GridBackground()
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SystemHeader(title = "PROJECT100")
            }

            item {
                Column {
                    Text(
                        text = "OPERATIONAL STATUS",
                        style = MaterialTheme.typography.labelSmall,
                        color = NeonBlue,
                        fontSize = 10.sp
                    )
                    Text(
                        text = "TRAINING_PROTOCOL",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            item {
                ExerciseTrackerItem(
                    index = "01",
                    label = "PUSH-UPS",
                    current = workout.pushUps.toDouble(),
                    goal = 100.0,
                    onAdd = { viewModel.updatePushUps(it.toInt()) }
                )
            }

            item {
                ExerciseTrackerItem(
                    index = "02",
                    label = "SIT-UPS",
                    current = workout.sitUps.toDouble(),
                    goal = 100.0,
                    onAdd = { viewModel.updateSitUps(it.toInt()) }
                )
            }

            item {
                ExerciseTrackerItem(
                    index = "03",
                    label = "SQUATS",
                    current = workout.squats.toDouble(),
                    goal = 100.0,
                    onAdd = { viewModel.updateSquats(it.toInt()) }
                )
            }

            item {
                ExerciseTrackerItem(
                    index = "04",
                    label = "10KM RUN",
                    current = workout.runningKm,
                    goal = 10.0,
                    unit = "KM",
                    isDecimal = true,
                    onAdd = { viewModel.updateRunning(it) }
                )
            }

            item {
                TrainingMetricsPanel(
                    completion = calculateTotalProgress(workout.pushUps, workout.sitUps, workout.squats, workout.runningKm)
                )
            }

            item {
                VitalAlertBanner()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ExerciseTrackerItem(
    index: String,
    label: String,
    current: Double,
    goal: Double,
    unit: String = "",
    isDecimal: Boolean = false,
    onAdd: (Double) -> Unit
) {
    var manualValue by remember { mutableStateOf("") }

    SystemPanel(
        modifier = Modifier.fillMaxWidth(),
        showCorners = true
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$index // $label",
                    style = MaterialTheme.typography.labelSmall,
                    color = NeonBlue,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "TARGET: ${if (isDecimal) "%.2f".format(Locale.US, goal) else goal.toInt()} $unit",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isDecimal) "%.2f".format(Locale.US, current) else current.toInt().toString(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    SystemButton(
                        text = if (isDecimal) "+100M" else "+1",
                        onClick = { onAdd(if (isDecimal) 0.1 else 1.0) },
                        modifier = Modifier.width(80.dp)
                    )
                    SystemButton(
                        text = if (isDecimal) "+1KM" else "+10",
                        onClick = { onAdd(if (isDecimal) 1.0 else 10.0) },
                        modifier = Modifier.width(80.dp),
                        containerColor = NeonBlue.copy(alpha = 0.2f),
                        contentColor = NeonBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progress bar
            val progress = (current / goal).toFloat().coerceIn(0f, 1f)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color.DarkGray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .background(NeonBlue)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SystemTextField(
                    value = manualValue,
                    onValueChange = { manualValue = it },
                    modifier = Modifier.weight(1f),
                    placeholder = "MANUAL_ENTRY"
                )
                SystemButton(
                    text = "SET",
                    onClick = {
                        manualValue.toDoubleOrNull()?.let { 
                            onAdd(it - current) 
                            manualValue = ""
                        }
                    },
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            }
        }
    }
}

@Composable
fun VitalAlertBanner() {
    SystemPanel(
        modifier = Modifier.fillMaxWidth(),
        borderColor = WarningRed.copy(alpha = 0.5f),
        showCorners = true
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("!", color = WarningRed, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                GlitchText(
                    text = "VITAL_ALERT",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = WarningRed,
                    isCritical = true
                )
            }
            Text(
                text = "HYDRATION_RESERVES_LOW. CONSUME 500ML LIQUID IMMEDIATELY.",
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            SystemButton(
                text = "ACKNOWLEDGE",
                onClick = { },
                containerColor = WarningRed.copy(alpha = 0.2f),
                contentColor = WarningRed,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
