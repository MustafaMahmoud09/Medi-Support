package com.example.heartrate.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun StatusView(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_0_75.dp
    ),
    background: Color = theme.redDark,
    status: String,
    statusColor: Color = theme.white,
    statusSize: Float = dimen.dimen_1_25,
    width: Float,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
            .clip(
                shape = shape
            ).width(
                width = width.dp
            )
            .background(
                color = background
            )
            .padding(
                horizontal = dimen.dimen_1_25.dp,
                vertical = dimen.dimen_1_25.dp
            ),
        contentAlignment = Alignment.Center
    ) {

        //create status here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = status,
            size = statusSize,
            color = statusColor
        )

    }//end Box

}//end StatusView