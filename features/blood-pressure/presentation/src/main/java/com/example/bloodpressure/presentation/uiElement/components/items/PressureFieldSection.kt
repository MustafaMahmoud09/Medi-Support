package com.example.bloodpressure.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun PressureFieldSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    containerHeight: Float = dimen.dimen_5_5,
    hint: String,
    hintColor: Color = theme.black,
    hintSize: Float = dimen.dimen_2,
    hintShape: Shape = RectangleShape,
    inputShape: Shape = RoundedCornerShape(
        size = (dimen.dimen_0_5 + dimen.dimen_0_125).dp
    ),
    input: Int,
    inputSize: Float = dimen.dimen_1_75,
    inputColor: Color = theme.black,
    onClickOnOperation: (Boolean) -> Unit,
    minusIcon: Painter = painterResource(
        id = com.example.sharedui.R.drawable.minus
    ),
    plusIcon: Painter = painterResource(
        id = com.example.sharedui.R.drawable.plus
    ),
    tint: Color = theme.black,
    operationHeight: Float = dimen.dimen_0_75,
    operationWidth: Float = dimen.dimen_1_5,
    borderColor: Color = theme.redDark,
    borderWidth: Float = dimen.dimen_0_125,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components
        val (hintId, inputFieldId) = createRefs()
        //create guides here
        val guideFromStart70P = createGuidelineFromStart(0.7f)


        //create hint box here
        Box(
            modifier = Modifier
                .constrainAs(hintId) {
                    start.linkTo(parent.start)
                    end.linkTo(guideFromStart70P)
                    width = Dimension.fillToConstraints
                }
                .height(
                    height = containerHeight.dp
                )
                .appBorder(
                    borderWidth = borderWidth,
                    borderColor = borderColor,
                    shape = hintShape
                )
                .padding(
                    dimen.dimen_1.dp
                ),
            contentAlignment = Alignment.Center,
        ) {

            //create hint here
            TextNormalView(
                theme = theme,
                dimen = dimen,
                text = hint,
                size = hintSize,
                fontColor = hintColor
            )

        }//end Box

        //create input field here
        ConstraintLayout(
            modifier = Modifier
                .constrainAs(inputFieldId) {
                    start.linkTo(
                        hintId.end,
                        dimen.dimen_3.dp
                    )
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .height(
                    height = containerHeight.dp
                )
                .appBorder(
                    borderWidth = borderWidth,
                    borderColor = borderColor,
                    shape = inputShape
                )
        ) {
            //create ids for components here
            val (operationId, inputId) = createRefs()

            //create operations column here
            Column(
                modifier = Modifier
                    .constrainAs(operationId) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(
                            parent.end,
                            dimen.dimen_1.dp
                        )
                        height = Dimension.fillToConstraints
                    },
                verticalArrangement = Arrangement.Center
            ) {

                //create minus button here
                Icon(
                    painter = minusIcon,
                    contentDescription = "minus button",
                    tint = tint,
                    modifier = Modifier
                        .size(
                            width = operationWidth.dp,
                            height = operationHeight.dp
                        )
                        .clickableWithoutHover {
                            onClickOnOperation(false)
                        }
                )

                //create padding here
                Spacer(
                    modifier = Modifier
                        .height(
                            height = dimen.dimen_0_75.dp
                        )
                )

                //create plus button here
                Icon(
                    painter = plusIcon,
                    contentDescription = "plus button",
                    tint = tint,
                    modifier = Modifier
                        .size(
                            width = operationWidth.dp,
                            height = operationHeight.dp
                        )
                        .clickableWithoutHover {
                            onClickOnOperation(true)
                        }
                )

            }//end Row

            //create input text here
            TextNormalView(
                theme = theme,
                dimen = dimen,
                text = "$input",
                size = inputSize,
                fontColor = inputColor,
                modifier = Modifier
                    .constrainAs(inputId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_0_5.dp
                        )
                        end.linkTo(
                            operationId.start,
                            dimen.dimen_0_5.dp
                        )
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }//end constrainAs
            )

        }//end ConstraintLayout

    }//end ConstraintLayout

}//end InputPressureFieldSection