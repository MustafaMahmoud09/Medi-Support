package com.example.sharedui.uiElement.components.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun MultiFamilyTextView(
    dimen: CustomDimen,
    theme: CustomTheme,
    parentText: String,
    subTexts: Array<String>,
    subTextsFamily: Array<FontFamily>,
    parentTextFamily: FontFamily,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = TextAlign.Center,
    textSize: Float = dimen.dimen_1_75,
    textColor: Color = theme.black,
    modifier: Modifier = Modifier
) {

    //condition for validate from data
    if (subTextsFamily.size == subTexts.size && subTextsFamily.isNotEmpty()) {

        //create multi family text
        val aboutInfoColor = AnnotatedString.Builder(parentText).apply {

            //define text font to parent text
            addStyle(
                style = SpanStyle(
                    fontFamily = parentTextFamily
                ),
                start = 0,
                end = parentText.length
            )

            //for loop on start index to texts to change family text
            for (index in subTexts.indices) {
                //get this text
                val subText = subTexts[index]

                //get start text index for this text
                val textIndex = parentText.indexOf(subText)

                //check text exist in parent text or no
                if (textIndex != -1) {
                    //change font family sub text here
                    addStyle(
                        style = SpanStyle(
                            fontFamily = subTextsFamily[index]
                        ),
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
            color = textColor,
            modifier = modifier,
        )

    }//end if

    //error in data execute this
    else {

        Text(
            text = parentText,
            fontSize = textSize.sp,
            color = textColor,
            textAlign = textAlign,
            maxLines = maxLines,
            fontFamily = parentTextFamily,
            modifier = modifier,
        )

    }//end else

}//end MultiColorTextView