package com.example.project100.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.project100.ui.components.SystemButton
import com.example.project100.ui.components.SystemPanel
import com.example.project100.ui.theme.WarningRed
import com.example.project100.viewmodel.PunishmentViewModel

@Composable
fun PunishmentOverlay(
    debt: Int,
    viewModel: PunishmentViewModel = hiltViewModel()
) {
    // IMPORTANTE: Recolectar el estado para que el ViewModel mantenga el flujo activo
    val activePunishments by viewModel.activePunishments.collectAsState()
    
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
            .background(Color.Black.copy(alpha = 0.95f)), // Ligeramente transparente para efecto overlay
        contentAlignment = Alignment.Center
    ) {
        // Glitch/Emergency background effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha * 0.2f)
                .background(WarningRed)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            // Error Icon
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(WarningRed, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("!", color = Color.Black, fontSize = 40.sp, fontWeight = FontWeight.Black)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "SYSTEM\nPENALTY\nACTIVE",
                color = WarningRed,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Black
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "APPLICATION ACCESS RESTRICTED",
                color = WarningRed,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            SystemPanel(
                modifier = Modifier.fillMaxWidth(),
                borderColor = WarningRed.copy(alpha = 0.5f)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "OUTSTANDING VIOLATION",
                        color = WarningRed,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 10.sp
                    )
                    
                    Text(
                        text = "$debt",
                        color = Color.White,
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Black
                    )
                    
                    Text(
                        text = "BURPEES",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        letterSpacing = 4.sp
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(WarningRed.copy(alpha = 0.3f)))
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "FAILURE TO COMPLY WILL RESULT IN\nPERMANENT DATA PURGE",
                        color = WarningRed,
                        fontSize = 9.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            SystemButton(
                text = "REGISTER BURPEE COMPLETION (1)",
                onClick = { 
                    if (debt > 0) {
                        viewModel.completeBurpees(1)
                    }
                },
                containerColor = WarningRed,
                contentColor = Color.White,
                modifier = Modifier.fillMaxWidth().height(60.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "> SECURE_LINK: TERMINAL_002    SYSTEM_STATUS: LOCKED",
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                color = WarningRed.copy(alpha = 0.5f),
                fontSize = 8.sp
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = "RESTRICTED MILITARY GRADE INTERFACE\nPROJECT100 - EVOLUTION OR EXTINCTION",
                color = Color.DarkGray,
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                lineHeight = 12.sp
            )
        }
    }
}
