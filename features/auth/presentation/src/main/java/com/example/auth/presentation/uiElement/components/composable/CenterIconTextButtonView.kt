package com.example.auth.presentation.uiElement.components.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun CenterIconTextButtonView(
    theme: CustomTheme,
    dimen: CustomDimen,
    icon: Painter,
    text: String,
    height: Float = dimen.dimen_6_5,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .height(
                height.dp
            )
            .clip(
                shape = RoundedCornerShape(
                    dimen.dimen_1_25.dp
                )
            )
            .border(
                width = dimen.dimen_0_25.dp,
                shape = RoundedCornerShape(
                    dimen.dimen_1_25.dp
                ),
                color = theme.grayLight
            )
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = icon,
            contentDescription = "icon",
            modifier = Modifier
                .size(dimen.dimen_3.dp)
        )

        Spacer(
            modifier = Modifier
                .width(dimen.dimen_2.dp)
        )

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = text,
            size = dimen.dimen_2
        )

    }//end Row

}//end IconStartButtonView