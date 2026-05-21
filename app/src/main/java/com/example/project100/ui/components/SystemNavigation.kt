package com.example.project100.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project100.ui.theme.NeonBlue

@Composable
fun SystemNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF080808))
            .border(1.dp, Color(0xFF1A1A1A))
            .padding(bottom = 12.dp, top = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SystemNavItem(
                label = "HUD",
                iconType = NavIconType.HUD,
                isSelected = currentRoute == "dashboard",
                onClick = { onNavigate("dashboard") }
            )
            SystemNavItem(
                label = "TRAIN",
                iconType = NavIconType.TRAIN,
                isSelected = currentRoute == "training",
                onClick = { onNavigate("training") }
            )
            SystemNavItem(
                label = "DATA",
                iconType = NavIconType.DATA,
                isSelected = currentRoute == "history",
                onClick = { onNavigate("history") }
            )
            SystemNavItem(
                label = "SYSTEM",
                iconType = NavIconType.SYSTEM,
                isSelected = currentRoute == "profile",
                onClick = { onNavigate("profile") }
            )
        }
    }
}

enum class NavIconType { HUD, TRAIN, DATA, SYSTEM }

@Composable
fun SystemNavItem(
    label: String,
    iconType: NavIconType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val activeColor = NeonBlue
    val inactiveColor = Color.Gray // Changed from DarkGray for better visibility
    val color = if (isSelected) activeColor else inactiveColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .then(
                    if (isSelected) {
                        Modifier.border(1.dp, activeColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                            .background(activeColor.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                    } else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            NavIcon(type = iconType, color = color)
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = label,
            color = color,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun NavIcon(type: NavIconType, color: Color) {
    Canvas(modifier = Modifier.size(20.dp)) { // Increased size slightly
        when (type) {
            NavIconType.HUD -> {
                val s = size.width / 2.3f
                val gap = 2.dp.toPx()
                // 4 squares grid
                drawRect(color, Offset(0f, 0f), Size(s, s))
                drawRect(color, Offset(s + gap, 0f), Size(s, s))
                drawRect(color, Offset(0f, s + gap), Size(s, s))
                drawRect(color, Offset(s + gap, s + gap), Size(s, s))
            }
            NavIconType.TRAIN -> {
                // Dumbbell icon
                withTransform({ rotate(45f, center) }) {
                    val w = size.width
                    val h = size.height
                    drawRect(color, Offset(w * 0.1f, h * 0.42f), Size(w * 0.8f, h * 0.16f))
                    drawRect(color, Offset(0f, h * 0.3f), Size(w * 0.25f, h * 0.4f))
                    drawRect(color, Offset(w * 0.75f, h * 0.3f), Size(w * 0.25f, h * 0.4f))
                }
            }
            NavIconType.DATA -> {
                // Chart icon
                drawRect(color, Offset(0f, 0f), size, style = Stroke(1.5.dp.toPx()))
                drawLine(color, Offset(size.width*0.3f, size.height*0.7f), Offset(size.width*0.3f, size.height*0.4f), 2.dp.toPx())
                drawLine(color, Offset(size.width*0.5f, size.height*0.7f), Offset(size.width*0.5f, size.height*0.2f), 2.dp.toPx())
                drawLine(color, Offset(size.width*0.7f, size.height*0.7f), Offset(size.width*0.7f, size.height*0.5f), 2.dp.toPx())
            }
            NavIconType.SYSTEM -> {
                // Profile circle + arc
                drawCircle(color, radius = size.width*0.22f, center = Offset(size.width/2, size.height*0.35f), style = Stroke(1.5.dp.toPx()))
                drawPath(
                    path = Path().apply {
                        addArc(
                            Rect(0f, size.height*0.5f, size.width, size.height*1.1f),
                            180f, 180f
                        )
                    },
                    color = color,
                    style = Stroke(1.5.dp.toPx())
                )
            }
        }
    }
}
