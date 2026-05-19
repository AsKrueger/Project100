package com.example.project100.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project100.ui.theme.WarningRed
import com.example.project100.viewmodel.PunishmentViewModel

@Composable
fun PunishmentOverlay(
    debt: Int,
    viewModel: PunishmentViewModel = hiltViewModel()
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        // Warning background glow
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha)
                .background(WarningRed.copy(alpha = 0.1f))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "SYSTEM PENALTY ACTIVE",
                color = WarningRed,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "QUEST FAILURE DETECTED",
                color = WarningRed,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.alpha(0.7f)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "CURRENT SYSTEM DEBT",
                color = Color.White,
                style = MaterialTheme.typography.labelSmall
            )
            
            Text(
                text = "$debt BURPEES",
                color = WarningRed,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MaterialTheme.typography.headlineLarge.fontFamily
            )

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "APPLICATION ACCESS RESTRICTED",
                color = WarningRed,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.completeBurpees(10) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = WarningRed,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("COMPLETE 10 BURPEES", fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { viewModel.completeBurpees(1) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = WarningRed
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, WarningRed),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("COMPLETE 1 BURPEE")
            }
        }
    }
}
