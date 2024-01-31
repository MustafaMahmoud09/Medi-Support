package com.example.sharedui.uiElement.components.material4


import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    width: Dp = 72.dp,
    height: Dp = 36.dp,
    checkedTrackColor: Color = Color.Red,
    uncheckedTrackColor: Color = Color.White,
    spaceBetweenThumbAndTrack: Dp = 0.dp,
    trackShape: Shape = RoundedCornerShape(
        percent = 90
    ),
    thumbShape: Shape = RoundedCornerShape(
        percent = 90
    ),
    checkedThumbColor: Color = Color.White,
    uncheckedThumbColor: Color = Color.White,
    modifier: Modifier = Modifier,
    thumbContent: @Composable () -> Unit = {}
) {

    // for moving the thumb
    val alignment by animateAlignmentAsState(if (checked) 1f else -1f)

    //create Track here
    Box(
        modifier = modifier
            .size(
                width = width,
                height = height
            )
            .clip(
                shape = trackShape
            )
            .background(
                color = if (checked) checkedTrackColor else uncheckedTrackColor
            )
            .clickableWithoutHover {
                onCheckedChange()
            },
        contentAlignment = alignment
    ) {

        //create thumb here
        Box(
            modifier = Modifier
                .padding(
                    start = spaceBetweenThumbAndTrack,
                    end = spaceBetweenThumbAndTrack
                )
                .clip(
                    shape = thumbShape
                )
                .background(
                    color = if (checked) checkedThumbColor else uncheckedThumbColor
                )
                .fillMaxHeight()
                .aspectRatio(1f)
        ) {

            thumbContent()

        }//end Thumb Box

    }//end Track Box

}//end Switch


@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {

    val bias by animateFloatAsState(targetBiasValue)
    return derivedStateOf {
        BiasAlignment(horizontalBias = bias, verticalBias = 0f)
    }

}//end animateAlignmentAsState
