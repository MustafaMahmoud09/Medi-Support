package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun BasicTextFieldView(
    dimen: CustomDimen,
    theme: CustomTheme,
    input: String,
    onChange: (String) -> Unit,
    maxLines: Int = 1,
    textColor: Color = theme.black,
    fontSize: Float = dimen.dimen_2,
    fontFamily: FontFamily = robotoMedium,
    enable: Boolean = true,
    modifier: Modifier = Modifier,
) {

    //create basic text field here
    BasicTextField(
        value = input,
        onValueChange = onChange,
        textStyle = TextStyle.Default.copy(
            fontFamily = fontFamily,
            fontSize = fontSize.sp,
            color = textColor
        ),
        enabled = enable,
        modifier = modifier,
        singleLine = maxLines == 1,
        maxLines = maxLines
    )

}//end BasicTextFieldView


@Composable
fun BasicTextFieldViewII(
    dimen: CustomDimen,
    theme: CustomTheme,
    input: String,
    onChange: (String) -> Unit,
    maxLines: Int = 1,
    textColor: Color = theme.black,
    fontSize: Float = dimen.dimen_2,
    fontFamily: FontFamily = robotoMedium,
    enable: Boolean = true,
    hint: String,
    hintColor: Color,
    hintSize: Float,
    hintFontFamily: FontFamily,
    modifier: Modifier = Modifier,
) {

    //create container here
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        //create basic text field here
        BasicTextField(
            value = input,
            onValueChange = onChange,
            textStyle = TextStyle.Default.copy(
                fontFamily = fontFamily,
                fontSize = fontSize.sp,
                color = textColor
            ),
            enabled = enable,
            singleLine = maxLines == 1,
            maxLines = maxLines,
            modifier = Modifier
                .fillMaxWidth()
        )

        //create hint if value is empty
        if (input.isEmpty()) {

            Text(
                text = hint,
                color = hintColor,
                fontFamily = hintFontFamily,
                fontSize = hintSize.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end if

    }//end Box

}//end BasicTextFieldView