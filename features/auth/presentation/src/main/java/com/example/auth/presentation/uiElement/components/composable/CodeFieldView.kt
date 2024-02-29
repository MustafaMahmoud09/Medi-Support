package com.example.auth.presentation.uiElement.components.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun CodeFieldView(
    dimen: CustomDimen,
    theme: CustomTheme,
    value: String,
    onChange: (String) -> Unit,
    size: Float = dimen.dimen_8,
    fontSize: Float = dimen.dimen_2,
    fontColor: Color = theme.gray,
    modifier: Modifier = Modifier
) {

    var isFocused by rememberSaveable {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = modifier
            .size(
                size = size.dp
            )
            .clip(
                shape = RoundedCornerShape(
                    dimen.dimen_2.dp
                )
            )
            .border(
                width = dimen.dimen_0_125.dp,
                color = if (isFocused) theme.redDark else theme.grayDark,
                shape = RoundedCornerShape(
                    dimen.dimen_2.dp
                )
            )
            .padding(
                horizontal = dimen.dimen_1.dp,
                vertical = dimen.dimen_0_5.dp
            )
    ) {
        val basic = createRef()

        BasicTextField(
            value = value,
            onValueChange = onChange,
            textStyle = TextStyle.Default.copy(
                fontFamily = robotoRegular,
                fontSize = fontSize.sp,
                color = fontColor,
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .constrainAs(basic) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .onFocusChanged {
                    isFocused = it.isFocused
                }
        )

    }//end Box

}//end NumberFailedView