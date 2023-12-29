package com.example.auth.uiElement.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun MultiTextColorSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    textOne: String,
    textTwo: String,
    colorOne: Color,
    colorTwo: Color,
    onClick: () -> Unit,
    fontSize: Float = dimen.dimen_1_75,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = textOne,
            fontColor = colorOne,
            size = fontSize
        )

        Spacer(
            modifier = Modifier
                .width(
                    dimen.dimen_0_5.dp
                )
        )

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = textTwo,
            fontColor = colorTwo,
            size = fontSize,
            modifier = Modifier
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null
                ) { onClick() }
        )

    }//end Row

}//end MultiTextColorSection