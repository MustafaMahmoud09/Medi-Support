package com.example.heartprediction.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun InputFieldSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleColorOnFocus: Color = theme.black,
    titleColorOnNotFocus: Color = theme.grayD7D7D7,
    hint: String,
    inputColorOnFocus: Color = theme.grayC1C7CD,
    inputColorOnNotFocus: Color = theme.grayLight,
    inputSize: Float = dimen.dimen_2,
    value: String,
    onChange: (String) -> Unit,
    height: Float = dimen.dimen_6_5,
    maxLines: Int = 1,
    focusRequester: FocusRequester,
    contentCenter: Boolean = true,
    borderWidth: Float = dimen.dimen_0_125,
    borderColorOnFocus: Color = theme.redDark,
    borderColorOnNotFocus: Color = theme.grayD7D7D7,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    operationColorOnFocus: Color = theme.redDark,
    operationColorOnNotFocus: Color = theme.grayLightF2F2F2,
    keyboardType: KeyboardType = KeyboardType.Text,
    backgroundOnFocus: Color = theme.background,
    backgroundOnNotFocus: Color = theme.grayLightF2F2F2,
    onClickOnOperation: () -> Unit = {},
    numberField: Int,
    modifier: Modifier = Modifier,
    numberFieldIsFocusNow: Int,
) {
    //state have state field to execute changes
    var isFocus by rememberSaveable {
        mutableStateOf(false)
    }

    //create container here
    Column(
        modifier = modifier
    ) {

        //create title here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = dimen.dimen_1_75,
            fontColor = if (isFocus) titleColorOnFocus else titleColorOnNotFocus
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .height(dimen.dimen_0_75.dp)
        )

        //create field here
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp)
                .appBorder(
                    borderWidth = borderWidth,
                    borderColor = if (isFocus) borderColorOnFocus else borderColorOnNotFocus,
                    shape = shape
                )
                .background(
                    color = if (isFocus) backgroundOnFocus else backgroundOnNotFocus
                )
                .padding(
                    horizontal = dimen.dimen_2.dp
                ),
        ) {
            //create ids for components here
            val (boxFailed, hintId, operationId) = createRefs()

            //if keyboard type is number create icon operations
            if (keyboardType == KeyboardType.Number) {
                //create container here
                Column(
                    modifier = Modifier
                        .constrainAs(operationId) {
                            end.linkTo(
                                parent.end,
                                dimen.dimen_1.dp
                            )
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                ) {
                    //create minus button here
                    Icon(
                        painter = painterResource(
                            id = com.example.sharedui.R.drawable.minus
                        ),
                        contentDescription = "icon",
                        tint = if (isFocus) operationColorOnFocus else operationColorOnNotFocus,
                        modifier = Modifier
                            .width(
                                dimen.dimen_1_5.dp
                            )
                            .height(
                                dimen.dimen_0_75.dp
                            )
                            .clickableWithoutHover {
                                onClickOnOperation()
                            }
                    )

                    //create padding here
                    Spacer(
                        modifier = Modifier
                            .height(
                                dimen.dimen_0_25.dp
                            )
                    )

                    Icon(
                        painter = painterResource(
                            id = com.example.sharedui.R.drawable.plus
                        ),
                        contentDescription = "icon",
                        tint = if (isFocus) operationColorOnFocus else operationColorOnNotFocus,
                        modifier = Modifier
                            .width(
                                dimen.dimen_1_5.dp
                            )
                            .height(
                                dimen.dimen_0_75.dp
                            )
                            .clickableWithoutHover {
                                onClickOnOperation()
                            }
                    )

                }//end Column

            }//end if

            //create field here
            BasicTextField(
                value = value,
                onValueChange = onChange,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType
                ),
                textStyle = TextStyle.Default.copy(
                    fontFamily = robotoMedium,
                    fontSize = inputSize.sp,
                    color = if (isFocus) inputColorOnFocus else inputColorOnNotFocus
                ),
                //if this field is focus make enable equal true
                enabled = numberField == numberFieldIsFocusNow,
                modifier = Modifier
                    .constrainAs(boxFailed) {
                        start.linkTo(parent.start)

                        //if keyboard type is number create field end before operations
                        if (keyboardType == KeyboardType.Number) {

                            end.linkTo(
                                operationId.start,
                                dimen.dimen_1.dp
                            )

                        } else {
                            end.linkTo(parent.end)
                        }
                        //if content in center make text input in center
                        if (contentCenter) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        //else make text input in top
                        else {
                            top.linkTo(
                                parent.top,
                                dimen.dimen_2.dp
                            )
                        }
                        width = Dimension.fillToConstraints
                    }
                    .focusRequester(
                        focusRequester = focusRequester
                    )
                    .onFocusChanged {
                        isFocus = it.isFocused
                    },
                maxLines = maxLines
            )

            //if value in field is empty create hint
            if (value.isEmpty()) {
                //create hint here
                TextNormalView(
                    theme = theme,
                    dimen = dimen,
                    text = hint,
                    size = inputSize,
                    textAlign = null,
                    fontColor = if (isFocus) inputColorOnFocus else inputColorOnNotFocus,
                    modifier = Modifier
                        .constrainAs(hintId) {
                            start.linkTo(parent.start)

                            //if keyboard type is number create field end before operation
                            if (keyboardType == KeyboardType.Number) {

                                end.linkTo(
                                    operationId.start,
                                    dimen.dimen_1.dp
                                )
                            } else {
                                end.linkTo(parent.end)
                            }

                            //if content in center make hint in center
                            if (contentCenter) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                            //else make hint in top
                            else {
                                top.linkTo(
                                    parent.top,
                                    dimen.dimen_2.dp
                                )
                            }
                            width = Dimension.fillToConstraints
                        }
                )

            }//end if

        }//end Box

    }//end Column

}//end InputMenuFieldSection