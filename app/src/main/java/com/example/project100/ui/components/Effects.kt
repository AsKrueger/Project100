package com.example.project100.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.project100.ui.theme.NeonBlue

@Composable
fun SystemBackgroundEffects() {
    val infiniteTransition = rememberInfiniteTransition(label = "scanline")
    val yOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "yOffset"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val height = size.height
        val width = size.width
        
        // 1. Grid Táctica sutil
        val gridSize = 40.dp.toPx()
        for (x in 0..(width / gridSize).toInt()) {
            drawLine(
                color = Color.White.copy(alpha = 0.05f),
                start = Offset(x * gridSize, 0f),
                end = Offset(x * gridSize, height),
                strokeWidth = 1f
            )
        }
        for (y in 0..(height / gridSize).toInt()) {
            drawLine(
                color = Color.White.copy(alpha = 0.05f),
                start = Offset(0f, y * gridSize),
                end = Offset(width, y * gridSize),
                strokeWidth = 1f
            )
        }

        // 2. Scanlines (Líneas horizontales finas)
        val lineSpacing = 4.dp.toPx()
        var currentY = 0f
        while (currentY < height) {
            drawLine(
                color = Color.White.copy(alpha = 0.02f),
                start = Offset(0f, currentY),
                end = Offset(width, currentY),
                strokeWidth = 1f
            )
            currentY += lineSpacing
        }

        // 3. Moving Scanline (Efecto de barrido de radar)
        val scanlineY = yOffset * height
        drawLine(
            color = NeonBlue.copy(alpha = 0.15f),
            start = Offset(0f, scanlineY),
            end = Offset(width, scanlineY),
            strokeWidth = 2.dp.toPx()
        )
    }
}
