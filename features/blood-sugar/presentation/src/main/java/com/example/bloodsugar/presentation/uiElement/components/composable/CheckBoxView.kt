package com.example.bloodsugar.presentation.uiElement.components.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun CheckBoxView(
    dimen: CustomDimen,
    theme: CustomTheme,
    value: Boolean,
    onChanged: (Boolean) -> Unit,
    size: Float = dimen.dimen_2_25,
    color: Color = theme.redDark,
    borderWidth: Float = dimen.dimen_0_125,
    shape: Shape = CircleShape,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
            .size(
                size = size.dp
            )
            .appBorder(
                borderWidth = borderWidth,
                borderColor = color,
                shape = shape
            )
            .clickableWithoutHover {
                onChanged(!value)
            }
            .padding(
                all = (dimen.dimen_0_25 + dimen.dimen_0_125).dp
            )
    ) {

        //if checked equal true create true icon here
        AnimatedVisibility(
            visible = value,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            )
        ) {

            //create icon here
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        shape = shape
                    )
                    .background(
                        color = color
                    )
            )

        }//end AnimatedVisibility

    }//end Box

}//end CheckBoxView