package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun LineView(
    dimen: CustomDimen,
    theme: CustomTheme,
    height: Float = dimen.dimen_0_125,
    color: Color = theme.grayLineLight,
    modifier: Modifier
) {

    Spacer(
        modifier = modifier
            .height(
                height.dp
            )
            .background(
                color = color
            )
    )

}//end LineView