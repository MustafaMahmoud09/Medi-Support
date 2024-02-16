package com.example.booking.uiElement.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.booking.uiElement.components.composable.InteractionRatingBarView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun InteractionRatingSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleColor: Color,
    titleSize: Float,
    value: Int,
    onChanged: (Int) -> Unit,
    size: Float = dimen.dimen_1_75,
    color: Color = theme.gold,
    spacingBetweenComponents: Float = dimen.dimen_0_5,
    modifier: Modifier = Modifier
) {

    //create container here
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        //create title here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            fontColor = titleColor
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .width(
                    width = spacingBetweenComponents.dp
                )
        )

        //create interaction rating bar here
        InteractionRatingBarView(
            dimen = dimen,
            theme = theme,
            value = value,
            onChanged = onChanged,
            size = size,
            color = color
        )

    }//end Row

}//end InteractionRatingSection