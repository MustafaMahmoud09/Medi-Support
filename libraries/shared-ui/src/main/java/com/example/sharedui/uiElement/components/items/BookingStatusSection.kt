package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appShadow
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun BookingStatusSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    status: String,
    statusColor: Color,
    statusSize: Float = dimen.dimen_1_5,
    background: Color,
    shape: Shape = RoundedCornerShape(
        percent = 90
    ),
    elevation: Float = dimen.dimen_0_5,
    ambientColor: Color = theme.black,
    spotColor: Color = theme.black,
    height: Float = dimen.dimen_2_5,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
            .appShadow(
                shape = shape,
                elevation = elevation,
                ambientColor = ambientColor,
                spotColor = spotColor
            )
            .height(
                height = height.dp
            )
            .background(
                color = background
            ),
        contentAlignment = Alignment.Center
    ) {

        //create status here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = status,
            size = statusSize,
            fontColor = statusColor
        )

    }//end Box

}//end BookingStatusSection