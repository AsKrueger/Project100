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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project100.ui.theme.NeonBlue

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
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(NeonBlue)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Black
            )
        }
        
        Canvas(modifier = Modifier.size(20.dp)) {
            val strokeWidth = 2.dp.toPx()
            drawCircle(color = NeonBlue, radius = 4f, style = Stroke(strokeWidth))
            drawCircle(color = NeonBlue.copy(alpha = 0.5f), radius = 10f, style = Stroke(strokeWidth))
        }
    }
}

@Composable
fun SystemMessageBanner(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, NeonBlue.copy(alpha = 0.5f))
            .background(NeonBlue.copy(alpha = 0.05f))
            .padding(8.dp)
    ) {
        Text(
            text = "SYSTEM MESSAGE: $message",
            style = MaterialTheme.typography.labelSmall,
            color = NeonBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp
        )
    }
}

@Composable
fun SystemPanel(
    modifier: Modifier = Modifier,
    borderColor: Color = NeonBlue.copy(alpha = 0.2f),
    showCorners: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .border(1.dp, borderColor)
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        if (showCorners) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val size = 10.dp.toPx()
                val stroke = 2.dp.toPx()
                
                drawPath(
                    path = Path().apply {
                        moveTo(0f, size)
                        lineTo(0f, 0f)
                        lineTo(size, 0f)
                    },
                    color = borderColor.copy(alpha = 1f),
                    style = Stroke(stroke)
                )
                
                drawPath(
                    path = Path().apply {
                        moveTo(this@Canvas.size.width - size, this@Canvas.size.height)
                        lineTo(this@Canvas.size.width, this@Canvas.size.height)
                        lineTo(this@Canvas.size.width, this@Canvas.size.height - size)
                    },
                    color = borderColor.copy(alpha = 1f),
                    style = Stroke(stroke)
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
    contentColor: Color = Color.Black
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Black,
            fontSize = 12.sp,
            letterSpacing = 1.sp
        )
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
