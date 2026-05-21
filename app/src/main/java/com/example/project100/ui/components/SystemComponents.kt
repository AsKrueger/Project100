package com.example.project100.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project100.ui.theme.NeonBlue
import com.example.project100.ui.theme.WarningRed

@Composable
fun GridBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val gridSpacing = 40.dp.toPx()
        val color = Color.White.copy(alpha = 0.03f)
        
        // Vertical lines
        var x = 0f
        while (x < size.width) {
            drawLine(
                color = color,
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = 1f
            )
            x += gridSpacing
        }
        
        // Horizontal lines
        var y = 0f
        while (y < size.height) {
            drawLine(
                color = color,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1f
            )
            y += gridSpacing
        }
    }
}

@Composable
fun SystemHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Shield icon placeholder
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .border(1.dp, NeonBlue)
                    .padding(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize().background(NeonBlue.copy(alpha = 0.5f)))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = NeonBlue,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Black
            )
        }
        
        // Wifi/Signal icon placeholder
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            repeat(3) { i ->
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height((4 + (i * 4)).dp)
                        .background(NeonBlue.copy(alpha = if(i==2) 1f else 0.4f))
                )
            }
        }
    }
}

@Composable
fun SystemMessageBanner(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, WarningRed.copy(alpha = 0.3f))
            .background(WarningRed.copy(alpha = 0.05f))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "⚠",
                color = WarningRed,
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "SYSTEM MESSAGE: $message",
                style = MaterialTheme.typography.labelSmall,
                color = WarningRed.copy(alpha = 0.8f),
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
fun SystemPanel(
    modifier: Modifier = Modifier,
    borderColor: Color = Color.White.copy(alpha = 0.1f),
    contentColor: Color = Color.Transparent,
    showCorners: Boolean = true,
    cornerColor: Color = NeonBlue,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .border(1.dp, borderColor)
            .background(contentColor)
    ) {
        if (showCorners) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val s = 8.dp.toPx()
                val sw = 1.5.dp.toPx()
                
                // Top-left
                drawPath(
                    path = Path().apply {
                        moveTo(0f, s)
                        lineTo(0f, 0f)
                        lineTo(s, 0f)
                    },
                    color = cornerColor,
                    style = Stroke(sw)
                )
                
                // Bottom-right
                drawPath(
                    path = Path().apply {
                        moveTo(size.width - s, size.height)
                        lineTo(size.width, size.height)
                        lineTo(size.width, size.height - s)
                    },
                    color = cornerColor,
                    style = Stroke(sw)
                )
            }
        }
        
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun SystemButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = NeonBlue,
    contentColor: Color = Color.Black,
    isOutline: Boolean = false
) {
    if (isOutline) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier.height(45.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(2.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, containerColor),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = containerColor)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Black,
                fontSize = 11.sp,
                letterSpacing = 1.sp
            )
        }
    } else {
        Button(
            onClick = onClick,
            modifier = modifier.height(45.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor
            )
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Black,
                fontSize = 11.sp,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun SystemTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "MANUAL_ENTRY"
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(Color.Black)
            .border(1.dp, NeonBlue.copy(alpha = 0.3f))
            .padding(8.dp),
        textStyle = TextStyle(
            color = NeonBlue,
            fontSize = 12.sp,
            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
        ),
        cursorBrush = SolidColor(NeonBlue),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = NeonBlue.copy(alpha = 0.3f),
                    fontSize = 12.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
            }
            innerTextField()
        }
    )
}
