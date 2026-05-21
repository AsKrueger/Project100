package com.example.project100.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.data.local.entity.BodyMetricsEntity
import com.example.project100.ui.components.SystemHeader
import com.example.project100.ui.components.SystemPanel
import com.example.project100.ui.components.SystemButton
import com.example.project100.ui.components.SystemTextField
import com.example.project100.viewmodel.BodyMetricsViewModel
import com.example.project100.ui.theme.NeonBlue
import java.time.format.DateTimeFormatter

@Composable
fun BodyMetricsScreen(
    viewModel: BodyMetricsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val allMetrics by viewModel.allMetrics.collectAsState()
    
    var weight by remember { mutableStateOf("") }
    var bodyFat by remember { mutableStateOf("") }
    var chest by remember { mutableStateOf("") }
    var waist by remember { mutableStateOf("") }
    var arms by remember { mutableStateOf("") }
    var legs by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SystemHeader(title = "PROJECT100")
        }

        item {
            Column {
                Text(
                    text = "BIOMETRIC_DATA_ENTRY",
                    style = MaterialTheme.typography.labelSmall,
                    color = NeonBlue,
                    fontSize = 10.sp
                )
                Text(
                    text = "BODY EVOLUTION TRACKER",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Black
                )
            }
        }

        item {
            SystemPanel(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    MetricInputRow("WEIGHT (KG)", weight) { weight = it }
                    MetricInputRow("BODY FAT (%)", bodyFat) { bodyFat = it }
                    MetricInputRow("CHEST (CM)", chest) { chest = it }
                    MetricInputRow("WAIST (CM)", waist) { waist = it }
                    MetricInputRow("ARMS (CM)", arms) { arms = it }
                    MetricInputRow("LEGS (CM)", legs) { legs = it }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    SystemButton(
                        text = "SYNC BIOMETRICS",
                        onClick = {
                            viewModel.updateMetrics(
                                weight.toDoubleOrNull() ?: 0.0,
                                bodyFat.toDoubleOrNull() ?: 0.0,
                                chest.toDoubleOrNull() ?: 0.0,
                                waist.toDoubleOrNull() ?: 0.0,
                                arms.toDoubleOrNull() ?: 0.0,
                                legs.toDoubleOrNull() ?: 0.0
                            )
                            // Clear fields
                            weight = ""; bodyFat = ""; chest = ""; waist = ""; arms = ""; legs = ""
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {
            Text(
                text = "BIOMETRIC_LOG_HISTORY",
                style = MaterialTheme.typography.labelSmall,
                color = NeonBlue,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        items(allMetrics.reversed()) { metrics ->
            MetricsHistoryCard(metrics)
        }

        item {
            SystemButton(
                text = "RETURN TO SYSTEM",
                onClick = onBack,
                containerColor = Color.Transparent,
                contentColor = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MetricsHistoryCard(metrics: BodyMetricsEntity) {
    SystemPanel(
        modifier = Modifier.fillMaxWidth(),
        showCorners = false,
        borderColor = NeonBlue.copy(alpha = 0.2f)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = metrics.date.format(DateTimeFormatter.ISO_LOCAL_DATE),
                    color = NeonBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Text(
                    text = "${metrics.weight} KG",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MetricSmallStat("FAT", "${metrics.bodyFat}%")
                MetricSmallStat("CHEST", "${metrics.chest}cm")
                MetricSmallStat("WAIST", "${metrics.waist}cm")
                MetricSmallStat("ARMS", "${metrics.arms}cm")
                MetricSmallStat("LEGS", "${metrics.legs}cm")
            }
        }
    }
}

@Composable
fun MetricSmallStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 8.sp, color = Color.Gray)
        Text(text = value, fontSize = 10.sp, color = Color.White)
    }
}

@Composable
fun MetricInputRow(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
        SystemTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.width(120.dp),
            placeholder = "0.00"
        )
    }
}
