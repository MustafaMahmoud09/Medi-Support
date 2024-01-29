package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
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
        maxLines = maxLines
    )

}//end BasicTextFieldView