package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover

@Composable
fun LinkView(
    text: String,
    color: Color,
    size: Float,
    fontFamily: FontFamily,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    //create line under text
    val result = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(text)
        }
    }

    //create result text
    BasicText(
        text = result,
        style = TextStyle.Default.copy(
            color = color,
            fontFamily = fontFamily,
            fontSize = size.sp
        ),
        modifier = modifier
            .clickableWithoutHover {
                onClick()
            }
    )

}//end LinkView