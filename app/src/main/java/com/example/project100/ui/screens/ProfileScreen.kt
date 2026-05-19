package com.example.project100.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.ElectricPurple
import com.example.project100.viewmodel.MainViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ProfileScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val userProfile by viewModel.userProfile.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "USER PROFILE",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Text(
            text = "IDENTITY VERIFIED",
            style = MaterialTheme.typography.labelSmall,
            color = NeonBlue
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Profile Avatar placeholder
        Box(
            modifier = Modifier
                .size(120.dp)
                .border(2.dp, NeonBlue, CircleShape)
                .padding(4.dp)
                .clip(CircleShape)
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = (userProfile?.username ?: "R").take(1).uppercase(),
                fontSize = 48.sp,
                color = NeonBlue,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = userProfile?.username ?: "RECRUIT",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        
        Text(
            text = "RANK ${userProfile?.currentRank ?: "E"}",
            color = ElectricPurple,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        StatsGrid(userProfile)

        Spacer(modifier = Modifier.height(32.dp))

        userProfile?.let {
            val date = Instant.ofEpochMilli(it.joinDate).atZone(ZoneId.systemDefault()).toLocalDate()
            Text(
                text = "JOIN DATE: ${date.format(DateTimeFormatter.ISO_LOCAL_DATE)}",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun StatsGrid(profile: com.example.project100.data.local.entity.UserProfileEntity?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard(label = "STREAK", value = "${profile?.currentStreak ?: 0}", modifier = Modifier.weight(1f))
            StatCard(label = "BEST", value = "${profile?.highestStreak ?: 0}", modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard(label = "BURPEES DONE", value = "${profile?.totalPunishmentBurpeesCompleted ?: 0}", modifier = Modifier.weight(1f))
            StatCard(label = "STATUS", value = "ACTIVE", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StatCard(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.Black)
            .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.bodyLarge, color = NeonBlue, fontWeight = FontWeight.Bold)
    }
}
