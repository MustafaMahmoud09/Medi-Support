package com.example.sharedui.uiElement.components.items

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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextNormalBlackFailedView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun BasicFieldSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    title: String,
    hint: String,
    value: String,
    fieldHeight: Float = dimen.dimen_6_5,
    maxLines: Int = 1,
    fieldIsPassword: Boolean = false,
    contentCenter: Boolean = true,
    endIconColor: Color = theme.redDark,
    onChange: (String) -> Unit,
    shape: Shape = RoundedCornerShape(
        dimen.dimen_1_25.dp
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    fontSize: Float = dimen.dimen_2,
    fontColor: Color = theme.gray,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(true) }

    //create container here
    Column(
        modifier = modifier
    ) {

        //create title here
        TextNormalBlackFailedView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = dimen.dimen_1_75
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .height(dimen.dimen_0_75.dp)
        )

        //create input container here
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(fieldHeight.dp)
                .appBorder(
                    shape = shape,
                    borderWidth = borderWidth,
                    borderColor = borderColor
                )
                .padding(
                    horizontal = dimen.dimen_2.dp
                ),
        ) {
            //create ids for components here
            val (boxFailed, passwordVisible, hintId) = createRefs()

            //if password is true create password icon
            if (fieldIsPassword) {

                //create password icon here
                Icon(
                    painter = if (isPasswordVisible) painterResource(
                        id = R.drawable.visible
                    ) else painterResource(
                        id = R.drawable.invisible
                    ),
                    contentDescription = "visible",
                    tint = endIconColor,
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

            }//end if

            //create box field here
            BasicTextField(
                value = value,
                onValueChange = onChange,
                textStyle = TextStyle.Default.copy(
                    fontFamily = robotoRegular,
                    fontSize = fontSize.sp,
                    color = fontColor
                ),
                visualTransformation = if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                modifier = Modifier
                    .constrainAs(boxFailed) {
                        start.linkTo(parent.start)
                        //is field type is password constraint end by password icon
                        if (fieldIsPassword) {
                            end.linkTo(passwordVisible.start)
                        }//end if
                        //is field not password constraint ent by parent
                        else {
                            end.linkTo(parent.end)
                        }//end else
                        //if content in center
                        if (contentCenter) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }//end if
                        //else
                        else {
                            top.linkTo(
                                parent.top,
                                dimen.dimen_2.dp
                            )
                        }//end else

                        width = Dimension.fillToConstraints
                    },
                singleLine = true,
                maxLines = maxLines
            )

            //if value is empty create hint
            if (value.isEmpty()) {

                //create hint here
                TextNormalView(
                    theme = theme,
                    dimen = dimen,
                    text = hint,
                    size = fontSize,
                    fontColor = fontColor,
                    textAlign = null,
                    modifier = Modifier
                        .constrainAs(hintId) {
                            start.linkTo(parent.start)

                            //is field type is password constraint end by password icon
                            if (fieldIsPassword) {
                                end.linkTo(passwordVisible.start)
                            }//end if
                            //is field not password constraint ent by parent
                            else {
                                end.linkTo(parent.end)
                            }//end else

                            if (contentCenter) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            } else {
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

}//end FailedHintSection
