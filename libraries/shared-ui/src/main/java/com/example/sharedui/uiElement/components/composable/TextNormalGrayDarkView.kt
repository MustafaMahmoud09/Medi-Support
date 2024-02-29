package com.example.sharedui.uiElement.components.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun TextNormalGrayDarkView(
    theme: CustomTheme,
    dimen: CustomDimen,
    text: String,
    size: Float,
    modifier: Modifier = Modifier
) {

    Text(
        text = text,
        fontSize = size.sp,
        color = theme.grayDark,
        fontFamily = robotoRegular,
        modifier = modifier,
    )
}//end TextNormalGrayDarkView