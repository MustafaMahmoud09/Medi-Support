package com.example.sharedui.uiElement.components.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun TextNormalRedView(
    theme: CustomTheme,
    dimen: CustomDimen,
    text: String,
    size: Float,
    fontColor: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    Text(
        text = text,
        fontSize = size.sp,
        color = fontColor,
        fontFamily = robotoRegular,
        modifier = modifier,
    )
}//end TextNormalRedView