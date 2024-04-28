package com.example.heartrate.presentation.uiElement.components.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun CalculateHeartRateSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    buttonHeight: Float = dimen.dimen_6,
    buttonColor: Color = theme.redDark,
    unit: String,
    unitColor: Color = theme.background,
    unitSize: Float = dimen.dimen_1_75,
    rate: String,
    rateSize: Float = dimen.dimen_3,
    rateColor: Color = theme.background,
    load: Boolean = false,
    strokeWidth: Float = dimen.dimen_0_125,
    progressLoadSize: Float = dimen.dimen_4,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .clip(
                shape = shape
            )
            .height(
                height = buttonHeight.dp
            )
            .background(
                color = buttonColor
            )
    ) {
        //create ids for components here
        val (rateId, progressBarLoadId) = createRefs()

        AnimatedVisibility (
           visible = !load,
           enter = fadeIn(
               animationSpec = tween(
                   durationMillis = 50
               )
           ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            modifier = Modifier
                .constrainAs(rateId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
        ) {

            //create rate row here
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                //create rate text here
                TextSemiBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = rate,
                    size = rateSize,
                    fontColor = rateColor
                )

                //create padding here
                Spacer(
                    modifier = Modifier
                        .width(
                            width = dimen.dimen_1_25.dp
                        )
                )

                //create unit text here
                TextSemiBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = unit,
                    size = unitSize,
                    fontColor = unitColor
                )

            }//end Row

        }//end AnimatedVisibility

        AnimatedVisibility (
            visible = load,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            modifier = Modifier
                .constrainAs(progressBarLoadId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
        ) {

            CircularProgressIndicator(
                color = theme.background,
                trackColor = buttonColor,
                strokeWidth = strokeWidth.dp,
                modifier = Modifier
                    .size(
                        size = progressLoadSize.dp
                    )
            )

        }//end AnimatedVisibility

    }//end ConstraintLayout

}//end CalculateButtonSection