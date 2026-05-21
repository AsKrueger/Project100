package com.example.project100.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.project100.data.local.entity.WorkoutEntity
import com.example.project100.ui.components.SystemHeader
import com.example.project100.ui.components.SystemPanel
import com.example.project100.ui.components.SystemButton
import com.example.project100.ui.components.SystemTextField
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.ElectricPurple
import com.example.project100.viewmodel.MainViewModel
import java.time.format.DateTimeFormatter

@Composable
fun ProfileScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToMetrics: () -> Unit
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val workoutHistory by viewModel.workoutHistory.collectAsState()
    var showEditDialog by remember { mutableStateOf(false) }
    var tempUsername by remember { mutableStateOf("") }
    
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.updateProfilePicture(it.toString()) }
    }

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            containerColor = Color.Black,
            modifier = Modifier.border(1.dp, NeonBlue),
            title = {
                Text(
                    "UPDATE_IDENTITY",
                    color = NeonBlue,
                    style = MaterialTheme.typography.labelSmall
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("ENTER NEW CODENAME", color = Color.Gray, fontSize = 10.sp)
                    SystemTextField(
                        value = tempUsername,
                        onValueChange = { tempUsername = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = userProfile?.username ?: "RECRUIT_001"
                    )
                }
            },
            confirmButton = {
                SystemButton(
                    text = "CONFIRM",
                    onClick = {
                        if (tempUsername.isNotBlank()) {
                            viewModel.updateUsername(tempUsername)
                        }
                        showEditDialog = false
                    }
                )
            },
            dismissButton = {
                SystemButton(
                    text = "CANCEL",
                    onClick = { showEditDialog = false },
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray
                )
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            SystemHeader(title = "PROJECT100")
        }

        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "SYSTEM_USER_INTERFACE",
                    style = MaterialTheme.typography.labelSmall,
                    color = NeonBlue,
                    fontSize = 10.sp
                )
                Text(
                    text = "IDENTITY VERIFIED",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Black
                )
            }
        }

        item {
            // Profile Avatar with System look
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .border(1.dp, NeonBlue.copy(alpha = 0.5f), CircleShape)
                    .padding(8.dp)
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(2.dp, NeonBlue, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (userProfile?.profilePictureUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(userProfile?.profilePictureUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize().clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text(
                            text = (userProfile?.username ?: "R").take(1).uppercase(),
                            fontSize = 64.sp,
                            color = NeonBlue,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
                
                // Overlay for "Edit"
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp)
                        .background(NeonBlue, CircleShape)
                        .border(2.dp, Color.Black, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }

        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    tempUsername = userProfile?.username ?: ""
                    showEditDialog = true
                }
            ) {
                Text(
                    text = userProfile?.username ?: "RECRUIT_001",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "LEVEL: ${userProfile?.currentRank ?: "E"}",
                    color = ElectricPurple,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "[ TAP TO EDIT IDENTITY ]",
                    fontSize = 8.sp,
                    color = NeonBlue.copy(alpha = 0.5f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        item {
            SystemPanel(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("CORE_DATA_METRICS", fontSize = 10.sp, color = NeonBlue, fontWeight = FontWeight.Bold)
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        ProfileStatItem("CURRENT STREAK", "${userProfile?.currentStreak ?: 0} DAYS")
                        ProfileStatItem("PEAK EFFICIENCY", "${userProfile?.highestStreak ?: 0} DAYS")
                    }
                    
                    HorizontalDivider(color = NeonBlue.copy(alpha = 0.1f))
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        ProfileStatItem("PUNISHMENT CLEARED", "${userProfile?.totalPunishmentBurpeesCompleted ?: 0}")
                        ProfileStatItem("SYSTEM UPTIME", calculateUptime(userProfile?.joinDate))
                    }
                }
            }
        }

        item {
            SystemButton(
                text = "UPDATE BIOMETRIC DATA",
                onClick = onNavigateToMetrics,
                modifier = Modifier.fillMaxWidth(),
                containerColor = NeonBlue.copy(alpha = 0.1f),
                contentColor = NeonBlue
            )
        }

        item {
            Text(
                text = "DEPLOYMENT_HISTORY",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.labelSmall,
                color = NeonBlue,
                fontSize = 10.sp
            )
        }

        if (workoutHistory.isEmpty()) {
            item {
                Text("NO RECORDS FOUND IN ARCHIVE", color = Color.Gray, fontSize = 12.sp)
            }
        } else {
            items(workoutHistory.take(5)) { workout ->
                HistoryRecordItem(workout)
            }
        }

        item {
            SystemPanel(
                modifier = Modifier.fillMaxWidth(),
                borderColor = Color.Gray.copy(alpha = 0.3f),
                showCorners = false
            ) {
                Column {
                    Text("SYSTEM_SETTINGS", fontSize = 10.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("SYNC_PROTOCOL: ACTIVE", fontSize = 12.sp, color = Color.White)
                    Text("ENCRYPTION: AES-256", fontSize = 12.sp, color = Color.White)
                    Text("LOCAL_DATA_ONLY: TRUE", fontSize = 12.sp, color = NeonBlue)
                }
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun HistoryRecordItem(workout: WorkoutEntity) {
    SystemPanel(
        modifier = Modifier.fillMaxWidth(),
        showCorners = false,
        borderColor = if (workout.isCompleted) NeonBlue.copy(alpha = 0.2f) else Color.Red.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = workout.date.format(DateTimeFormatter.ISO_LOCAL_DATE),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (workout.isCompleted) "MISSION_SUCCESS" else "MISSION_FAILED",
                    fontSize = 8.sp,
                    color = if (workout.isCompleted) NeonBlue else Color.Red
                )
            }
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HistoryMetricSmall("P", workout.pushUps.toString())
                HistoryMetricSmall("S", workout.squats.toString())
                HistoryMetricSmall("R", "${workout.runningKm}k")
            }
        }
    }
}

@Composable
fun HistoryMetricSmall(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 7.sp, color = Color.Gray)
        Text(text = value, fontSize = 10.sp, color = Color.White)
    }
}

@Composable
fun ProfileStatItem(label: String, value: String) {
    Column {
        Text(text = label, fontSize = 8.sp, color = Color.Gray)
        Text(text = value, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

fun calculateUptime(joinDate: Long?): String {
    if (joinDate == null) return "0 DAYS"
    val diff = System.currentTimeMillis() - joinDate
    val days = diff / (1000 * 60 * 60 * 24)
    return "$days DAYS"
}
