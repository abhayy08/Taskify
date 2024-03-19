package com.abhay.taskflow.features.feature_note.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HexagonShapeBox(
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    borderWidth: Float = 6f,
    cornerRadius: Float = 12f,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            val path = Path().apply {
                val width = size.width
                val height = size.height
                val radius = if (width > height) size.width / 2f else height / 2f
                val angle = 2.0 * Math.PI / 6
                val cx = width / 2f
                val cy = height / 2f
                moveTo(
                    cx + (radius * cos(0.0).toFloat()), cy + (radius * sin(0.0).toFloat())
                )
                for (i in 1 until 6) {
                    lineTo(
                        cx + (radius * cos(angle * i).toFloat()),
                        cy + (radius * sin(angle * i).toFloat())
                    )
                }
                close()
            }
            drawPath(
                path = path, color = borderColor, style = Stroke(
                    width = borderWidth, pathEffect = PathEffect.cornerPathEffect(cornerRadius)
                )
            )
        }
        content()
    }
}