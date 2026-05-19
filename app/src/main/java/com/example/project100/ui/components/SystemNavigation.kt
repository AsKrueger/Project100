package com.example.project100.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project100.ui.theme.NeonBlue

@Composable
fun SystemNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Black)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SystemNavItem(
            label = "HUD",
            isSelected = currentRoute == "dashboard",
            onClick = { onNavigate("dashboard") }
        )
        SystemNavItem(
            label = "TRAIN",
            isSelected = currentRoute == "training",
            onClick = { onNavigate("training") }
        )
        SystemNavItem(
            label = "DATA",
            isSelected = currentRoute == "history",
            onClick = { onNavigate("history") }
        )
        SystemNavItem(
            label = "SYSTEM",
            isSelected = currentRoute == "profile",
            onClick = { onNavigate("profile") }
        )
    }
}

@Composable
fun SystemNavItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) NeonBlue else Color.Gray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(if (isSelected) NeonBlue.copy(alpha = 0.2f) else Color.Transparent)
                .let { if (isSelected) it.background(color) else it } // Simple solid block for icon
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = color,
            fontSize = 8.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
        )
    }
}
