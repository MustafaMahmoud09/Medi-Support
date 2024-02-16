package com.example.sharedui.uiElement.components.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun MultiStyleTextView(
    dimen: CustomDimen,
    theme: CustomTheme,
    parentText: String,
    subTexts: Array<String>,
    subTextsStyle: Array<SpanStyle>,
    parentTextStyle: SpanStyle,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = TextAlign.Center,
    textSize: Float = dimen.dimen_1_75,
    fontFamily: FontFamily = robotoRegular,
    modifier: Modifier = Modifier
) {

    //condition for validate from data
    if (subTextsStyle.size == subTexts.size && subTextsStyle.isNotEmpty()) {

        //create multi color text
        val aboutInfoColor = AnnotatedString.Builder(parentText).apply {

            //define text color to parent text
            addStyle(
                style = parentTextStyle,
                start = 0,
                end = parentText.length
            )

            //for loop on start index to texts to change colors text
            for (index in subTexts.indices) {
                //get this text
                val subText = subTexts[index]

                //get start text index for this text
                val textIndex = parentText.indexOf(subText)

                //check text exist in parent text or no
                if (textIndex != -1) {
                    //change color sub text here
                    addStyle(
                        style = subTextsStyle[index],
                        start = textIndex,
                        end = textIndex + subText.length
                    )

                }//end if

            }//end for

        }.toAnnotatedString()

        //create text here
        Text(
            text = aboutInfoColor,
            fontSize = textSize.sp,
            textAlign = textAlign,
            maxLines = maxLines,
            fontFamily = fontFamily,
            modifier = modifier,
        )

    }//end if

    //error in data execute this
    else {

        Text(
            text = parentText,
            fontSize = textSize.sp,
            textAlign = textAlign,
            maxLines = maxLines,
            fontFamily = fontFamily,
            modifier = modifier,
        )

    }//end else

}//end MultiStyleTextView