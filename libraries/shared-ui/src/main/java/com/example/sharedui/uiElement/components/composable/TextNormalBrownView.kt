package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme


@Composable
fun TextNormalBrownView(
    theme: CustomTheme,
    dimen: CustomDimen,
    text: String,
    size: Float,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Text(
        text = text,
        fontSize = size.sp,
        color = theme.brownLight,
        fontFamily = robotoRegular,
        modifier = modifier
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ){
                onClick()
            },
    )
}//end TextNormalBrownView