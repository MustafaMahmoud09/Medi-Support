package com.example.bmi.presentation.uiElement.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.google.accompanist.placeholder.placeholder

@Composable
internal fun BMIResultSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    border: Painter = painterResource(
        id = R.drawable.progress_bmi
    ),
    result: String,
    resultSize: Float = dimen.dimen_4,
    resultColor: Color = theme.black333333,
    typeStateUser: String,
    typeStateUserColor: Color = theme.redDark,
    typeStateUserSize: Float = dimen.dimen_3_25,
    modifier: Modifier = Modifier,
    placeHolderState: Boolean = false,
    placeHolderColor: Color = theme.background,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (resultBoxId, typeState) = createRefs()

        //create result box here
        Box(
            modifier = Modifier
                .constrainAs(resultBoxId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(
                    ratio = 1f
                ),
            contentAlignment = Alignment.Center
        ) {

            //create border here
            Image(
                painter = border,
                contentDescription = "first image",
                modifier = Modifier
                    .fillMaxSize()
            )

            //create result text here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = result,
                size = resultSize,
                fontColor = resultColor,
                modifier = Modifier
                    .placeholder(
                        visible = placeHolderState,
                        color = placeHolderColor
                    )
            )

        }//end Box

        //create type state user here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = typeStateUser,
            size = typeStateUserSize,
            fontColor = typeStateUserColor,
            modifier = Modifier
                .constrainAs(typeState) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        resultBoxId.bottom,
                        dimen.dimen_3_5.dp
                    )
                }
                .placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )
        )

    }//end ConstraintLayout

}//end BMIResultSection