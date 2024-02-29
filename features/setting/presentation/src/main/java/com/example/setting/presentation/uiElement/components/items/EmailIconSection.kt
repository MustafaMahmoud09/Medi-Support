package com.example.setting.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun EmailIconSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    text: String,
    fontFamily: FontFamily,
    fontSize: Float,
    icon: Painter,
    textOneColor: Color = theme.redDark,
    textTwoColor: Color = theme.black,
    iconSize: Float,
    iconTint: Color,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = icon,
            contentDescription = "icon",
            tint = iconTint,
            modifier = Modifier
                .size(
                    size = iconSize.dp
                )
        )

        Spacer(
            modifier = Modifier
                .width(
                    width = dimen.dimen_0_75.dp
                )
        )


        val textColor = AnnotatedString.Builder(text).apply {

            addStyle(
                style = SpanStyle(color = textOneColor),
                start = 0,
                end = text.length
            )

            var start = -1

            for (index in 0 until text.length) {
                if (text[index] == '@') {
                    start = index
                    break
                }
            }//end for

            if (start != -1) {
                addStyle(
                    style = SpanStyle(color = textTwoColor),
                    start = start,
                    end = text.length
                )
            }//end if

        }.toAnnotatedString()

        Text(
            text = textColor,
            fontFamily = fontFamily,
            fontSize = fontSize.sp,
        )

    }//end Row

}//end IconTextSection