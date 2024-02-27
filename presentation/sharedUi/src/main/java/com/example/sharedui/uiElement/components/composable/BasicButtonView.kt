package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun BasicButtonView(
    dimen: CustomDimen,
    theme: CustomTheme,
    text: String,
    height: Float = dimen.dimen_6_5,
    color: Color = theme.redDark,
    onClick: () -> Unit,
    fontSize: Float = dimen.dimen_2_25,
    roundSize: Float = dimen.dimen_1_25,
    fontColor: Color = theme.white,
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(
                height = height.dp
            )
            .clip(
                shape = RoundedCornerShape(
                    roundSize.dp
                )
            )
            .background(
                color = color
            )
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                onClick()
            }
    ) {

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = text,
            size = fontSize,
            fontColor = fontColor
        )

    }//end Box

}//end BasicButtonView