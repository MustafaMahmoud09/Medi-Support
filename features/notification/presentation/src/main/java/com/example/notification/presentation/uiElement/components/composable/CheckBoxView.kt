package com.example.notification.presentation.uiElement.components.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.IconView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun CheckBoxView(
    dimen: CustomDimen,
    theme: CustomTheme,
    checked: Boolean,
    onChanged: (Boolean) -> Unit,
    size: Float,
    color: Color = theme.redDark,
    borderWidth: Float = dimen.dimen_0_125,
    borderShape: Shape = RectangleShape,
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
                shape = borderShape
            )
            .padding(
                all = dimen.dimen_0_125.dp
            )
            .clickableWithoutHover {
                onChanged(!checked)
            }
    ) {

        //if checked equal true create true icon here
        AnimatedVisibility(
            visible = checked,
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
            IconView(
                dimen = dimen,
                theme = theme,
                icon = painterResource(
                    id = com.example.sharedui.R.drawable.true_icon
                ),
                tint = color,
                modifier = Modifier
                    .fillMaxSize()
            )

        }//end AnimatedVisibility

    }//end Box

}//end CheckBoxView