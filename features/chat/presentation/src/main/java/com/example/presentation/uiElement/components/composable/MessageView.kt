package com.example.presentation.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun MessageView(
    theme: CustomTheme,
    dimen: CustomDimen,
    background: Color,
    message: String,
    messageColor: Color,
    messageSize: Float,
    messageShape: Shape,
    modifier: Modifier = Modifier
){

    //create container here
    Box(
        modifier = modifier
            .clip(
                shape = messageShape
            )
            .background(
                color = background
            )
            .padding(
                all = dimen.dimen_1_5.dp
            ),
        contentAlignment = Alignment.Center,
    ) {

        //create message here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = message,
            size = messageSize,
            textAlign = null,
            fontColor = messageColor,
        )

    }//end Box

}//end MessageView