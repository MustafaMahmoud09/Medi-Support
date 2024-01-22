package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun IconTitleView(
    theme: CustomTheme,
    dimen: CustomDimen,
    title: String,
    titleSize: Float = dimen.dimen_1_75,
    titleColor: Color = theme.black,
    iconColor: Color = theme.redDark,
    modifier: Modifier = Modifier
){

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(
            modifier = Modifier
                .size(
                    size = dimen.dimen_2_5.dp
                )
                .clip(
                    shape = RoundedCornerShape(
                        size = dimen.dimen_0_5.dp
                    )
                )
                .background(
                    color = iconColor
                )
        )

        Spacer(
            modifier = Modifier
                .width(
                    width = dimen.dimen_1_75.dp
                )
        )

        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            fontColor = titleColor
        )

    }//end Row

}//end IconTitleView