package com.example.sharedui.uiElement.components.material4

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.coroutines.delay


@Composable
fun GradientCircularProgressBar(
    colors: Brush,
    trackColor: Brush,
    strokeWidth: Float,
    modifier: Modifier = Modifier
) {

    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {

        while (true) {
            progress += 0.01f
            if (progress > 1f) {
                delay(20)
                progress = .02f
            }
            delay(7)
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress
    )

    Box(
        modifier = modifier
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val radius = size.width / 2f - 10 // 10 is the stroke width

            val startAngle = 90f
            val sweepAngle = animatedProgress * -360

            drawArc(
                brush = trackColor,
                startAngle = startAngle,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth
                )
            )

            drawArc(
                brush = colors,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth
                )
            )

        }//end Canvas

    }//end Box

}//end GradientCircularProgressBar