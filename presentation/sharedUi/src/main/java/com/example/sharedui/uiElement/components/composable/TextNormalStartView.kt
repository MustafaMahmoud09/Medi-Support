package com.example.sharedui.uiElement.components.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun TextNormalStartView(
    theme: CustomTheme,
    dimen: CustomDimen,
    text: String,
    size: Float,
    weigh: Int = 400,
    fontColor: Color = theme.black,
    modifier: Modifier = Modifier
) {

    Text(
        text = text,
        fontSize = size.sp,
        fontFamily = robotoRegular,
        fontWeight = FontWeight(weigh),
        color = fontColor,
        modifier = modifier
    )

}