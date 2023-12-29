package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextGrayNormalView
import com.example.sharedui.uiElement.components.composable.TextNormalBlackFailedView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun FailedHintSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    title: String,
    hint: String,
    value: String,
    password: Boolean = false,
    visibleIconColor: Color = theme.redDark,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(true) }
    var input by rememberSaveable { mutableStateOf(value) }

    Column(
        modifier = modifier
    ) {

        TextNormalBlackFailedView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = dimen.dimen_1_75
        )

        Spacer(
            modifier = Modifier
                .height(dimen.dimen_0_75.dp)
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimen.dimen_7.dp)
                .clip(
                    shape = RoundedCornerShape(
                        dimen.dimen_1_25.dp
                    )
                )
                .border(
                    width = dimen.dimen_0_125.dp,
                    shape = RoundedCornerShape(
                        dimen.dimen_1_25.dp
                    ),
                    color = theme.redDark
                )
                .padding(
                    horizontal = dimen.dimen_2.dp
                ),
        ) {
            val (boxFailed, passwordVisible, hintId) = createRefs()

            if (password) {

                Icon(
                    painter = if (isPasswordVisible) painterResource(
                        id = R.drawable.visible
                    ) else painterResource(
                        id = R.drawable.invisible
                    ),
                    contentDescription = "visible",
                    tint = visibleIconColor,
                    modifier = Modifier
                        .constrainAs(passwordVisible) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .size(
                            dimen.dimen_3.dp
                        )
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        ) {
                            isPasswordVisible = !isPasswordVisible
                        }
                )

            }

            if (password) {

                BasicTextField(
                    value = input,
                    onValueChange = onChange,
                    textStyle = TextStyle.Default.copy(
                        fontFamily = robotoMedium,
                        fontSize = dimen.dimen_2.sp,
                        color = theme.black
                    ),
                    modifier = Modifier
                        .constrainAs(boxFailed) {
                            start.linkTo(parent.start)
                            end.linkTo(passwordVisible.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        },
                    maxLines = 1
                )

            } else {

                BasicTextField(
                    value = input,
                    onValueChange = onChange,
                    textStyle = TextStyle.Default.copy(
                        fontFamily = robotoMedium,
                        fontSize = dimen.dimen_2.sp,
                        color = theme.black
                    ),
                    modifier = Modifier
                        .constrainAs(boxFailed) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        },
                    maxLines = 1
                )

            }

            if (value.isEmpty()) {

                TextGrayNormalView(
                    theme = theme,
                    dimen = dimen,
                    text = hint,
                    size = dimen.dimen_2,
                    modifier = Modifier
                        .constrainAs(hintId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        }
                )

            }//end if

        }//end Box

    }//end Column

    LaunchedEffect(
        key1 = isPasswordVisible,
        key2 = value
    ) {

        input = if (isPasswordVisible) {
            value
        } else {
            generateString(value, '.')
        }

    }//end LaunchedEffect

}//end FailedHintSection


private fun generateString(value: String, char: Char): String {

    var result = ""
    for (count in value.indices) {
        result += char
    }

    return result
}//end generateString