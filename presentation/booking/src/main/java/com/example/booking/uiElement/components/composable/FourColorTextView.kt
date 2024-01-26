package com.example.booking.uiElement.components.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun FourColorTextView(
    dimen: CustomDimen,
    theme: CustomTheme,
    text: String,
    firstText: String,
    secondText: String,
    thirdText: String,
    firstTextColor: Color,
    secondTextColor: Color,
    thirdTextColor: Color,
    otherTextColor: Color,
    textSize: Float = dimen.dimen_1_75,
    modifier: Modifier = Modifier
) {

    //create multi color text
    val aboutInfoColor = AnnotatedString.Builder(text).apply {
        //define start text to each text
        val startIndexFirstText = text.indexOf(firstText)
        val startIndexSecondText = text.indexOf(secondText)
        val startIndexThirdText = text.indexOf(thirdText)

        //define text color to other text
        addStyle(
            style = SpanStyle(color = otherTextColor),
            start = 0,
            end = text.length
        )

        //define text color to first text
        if (startIndexFirstText != -1) {
            addStyle(
                style = SpanStyle(color = firstTextColor),
                start = startIndexFirstText,
                end = startIndexFirstText + firstText.length
            )
        }

        //define text color to second text
        if (startIndexSecondText != -1) {
            addStyle(
                style = SpanStyle(color = secondTextColor),
                start = startIndexSecondText,
                end = startIndexSecondText + secondText.length
            )
        }

        //define text color to third text
        if (startIndexThirdText != -1) {
            addStyle(
                style = SpanStyle(color = thirdTextColor),
                start = startIndexThirdText,
                end = startIndexThirdText + thirdText.length
            )
        }

    }.toAnnotatedString()

    //create text here
    Text(
        text = aboutInfoColor,
        fontSize = textSize.sp,
        textAlign = TextAlign.Center,
        fontFamily = robotoRegular,
        modifier = modifier,
    )

}//end TreeColorTextView