package com.example.project100.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.WarningRed
import kotlin.random.Random

@Composable
fun GlitchText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = Color.White,
    isCritical: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glitch")
    val glitchTrigger by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "trigger"
    )

    val isGlitching = glitchTrigger > 0.95f
    val offsetX = if (isGlitching) Random.nextInt(-4, 4) else 0
    val offsetY = if (isGlitching) Random.nextInt(-2, 2) else 0

    Box(modifier = modifier) {
        if (isGlitching) {
            Text(
                text = text,
                style = style,
                color = if (isCritical) WarningRed else NeonBlue,
                modifier = Modifier
                    .offset { IntOffset(offsetX, offsetY) }
                    .alpha(0.5f)
            )
            Text(
                text = text,
                style = style,
                color = if (isCritical) Color.White else Color.Cyan,
                modifier = Modifier
                    .offset { IntOffset(-offsetX, -offsetY) }
                    .alpha(0.5f)
            )
        }
        Text(
            text = text,
            style = style,
            color = color
        )
    }
}
