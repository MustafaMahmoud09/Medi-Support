package com.example.setting.uiElement.components.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.robotoRegular

@Composable
internal fun TwoColorTextSection(
    textSize: Float,
    allText: String,
    otherText: String,
    allColor: Color,
    singleColor: Color,
    modifier: Modifier = Modifier
) {

    val aboutInfoColor = AnnotatedString.Builder(allText).apply {
        val startIndex = allText.indexOf(otherText)

        addStyle(
            style = SpanStyle(color = allColor),
            start = 0,
            end = allText.length
        )

        if (startIndex != -1) {
            addStyle(
                style = SpanStyle(color = singleColor),
                start = startIndex,
                end = startIndex + otherText.length
            )
        }
    }.toAnnotatedString()


    Text(
        text = aboutInfoColor,
        fontSize = textSize.sp,
        textAlign = TextAlign.Center,
        fontFamily = robotoRegular,
        modifier = modifier,
    )

}//end TwoColorTextSection