package com.abhay.taskify.features.feature_note.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhay.taskify.ui.theme.TaskifyTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HexagonCard(
    modifier: Modifier = Modifier,
    sides: Int,
    rotation: Float = 0f,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    cornerRadius: Float = with(LocalDensity.current) { 8.dp.toPx() },
    borderColor: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier
            .aspectRatio(1f, true),
        contentAlignment = Alignment.Center
    ) {
        val width: Float = constraints.maxWidth.toFloat()
        val height: Float = constraints.maxHeight.toFloat()

        Canvas(modifier = Modifier.matchParentSize()) {
            val r = rotation * (Math.PI / 180)
            val path = Path().apply {
                val radius = if (width > height) size.width / 2f else height / 2f
                val angle = 2.0 * Math.PI / sides
                val cx = width / 2f
                val cy = height / 2f
                moveTo(
                    cx + (radius * cos(0.0 + r).toFloat()),
                    cy + (radius * sin(0.0 + r).toFloat())
                )
                for (i in 1 until sides) {
                    lineTo(
                        cx + (radius * cos(angle * i + r).toFloat()),
                        cy + (radius * sin(angle * i + r).toFloat())
                    )
                }
                close()
            }

            val path2 = Path().apply {
                val radius =
                    if (width > height) width / 2f - 6f else height / 2f - 6f
                val angle = 2.0 * Math.PI / sides
                val cx = width / 2f
                val cy = height / 2f
                moveTo(
                    cx + (radius * cos(0.0 + r).toFloat()),
                    cy + (radius * sin(0.0 + r).toFloat())
                )
                for (i in 1 until sides) {
                    lineTo(
                        cx + (radius * cos(angle * i + r).toFloat()),
                        cy + (radius * sin(angle * i + r).toFloat())
                    )
                }
                close()
            }

            clipPath(path2) {
                drawRoundRect(
                    color = backgroundColor,
                    topLeft = Offset.Zero,
                    size = Size(width, height)
                )
            }

            drawPath(
                path = path,
                color = borderColor,
                style = Stroke(
                    width = 12f,
                    join = StrokeJoin.Round,
                    pathEffect = PathEffect.cornerPathEffect(cornerRadius),
                    cap = StrokeCap.Round
                )
            )
        }
        content()
    }
}


@Preview
@Composable
fun NoteItemPrev() {
    TaskifyTheme {
        HexagonCard(
            sides = 6, rotation = 0f,
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Hello")
                Divider(modifier = Modifier.fillMaxWidth(0.4f))
                Text(text = "Content")
            }
        }
    }
}