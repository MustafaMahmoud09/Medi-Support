package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun OnlineView(
    dimen: CustomDimen,
    theme: CustomTheme,
    iconSize: Float = dimen.dimen_1,
    iconShape: Shape = CircleShape,
    iconColor: Color = theme.green75F94C,
    modifier: Modifier = Modifier
){

    //create online icon here
    Spacer(
        modifier = modifier
            .size(
                size = iconSize.dp
            )
            .clip(
                shape = iconShape
            )
            .background(
                color = iconColor
            )
    )

}