package com.example.sharedui.uiElement.components.items

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun IconTextSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    text: String,
    fontFamily: FontFamily,
    fontSize: Float,
    fontColor: Color,
    icon: Painter,
    iconSize: Float,
    iconTint: Color,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    spaceBetweenComponents: Float = dimen.dimen_0_75,
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
                    width = spaceBetweenComponents.dp
                )
        )

        Text(
            text = text,
            fontFamily = fontFamily,
            color = fontColor,
            overflow = overflow,
            maxLines = maxLines,
            fontSize = fontSize.sp,
        )

    }//end Row

}//end IconTextSection