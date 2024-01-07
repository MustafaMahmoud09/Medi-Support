package com.example.sharedui.uiElement.components.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun TextNormalView(
    theme: CustomTheme,
    dimen: CustomDimen,
    text: String,
    size: Float,
    defineLine: Boolean = false,
    maxLines:Int = Int.MAX_VALUE,
    fontColor: Color = theme.blackLight,
    modifier: Modifier = Modifier
) {

    if (!defineLine) {

        Text(
            text = text,
            fontSize = size.sp,
            color = fontColor,
            fontFamily = robotoRegular,
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    }else{

        Text(
            text = text,
            fontSize = size.sp,
            color = fontColor,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            fontFamily = robotoRegular,
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    }
}//end TextNormalBackLightView