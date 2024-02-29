package com.example.heartrate.presentation.uiElement.components.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.heartrate.presentation.uiElement.components.composable.StatusView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun StatusResultSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_2.dp
    ),
    borderSize: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    status: String,
    statusWidth: Float = dimen.dimen_8,
    result: String,
    unit: String,
    resultSize: Float = dimen.dimen_9,
    unitSize: Float = dimen.dimen_4_5,
    unitColor: Color = theme.black,
    resultColor: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .appBorder(
                borderWidth = borderSize,
                borderColor = borderColor,
                shape = shape
            )
    ) {
        //create ids for components here
        val (statusId, resultId, unitId) = createRefs()

        //create status here
        StatusView(
            dimen = dimen,
            theme = theme,
            status = status,
            width = statusWidth,
            modifier = Modifier
                .constrainAs(statusId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2_25.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2.dp
                    )
                }
        )


        //create result here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = result,
            size = resultSize,
            color = resultColor,
            modifier = Modifier
                .constrainAs(resultId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2_25.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_3_5.dp
                    )
                    bottom.linkTo(
                        parent.bottom,
                        dimen.dimen_3_5.dp
                    )
                }
        )

        //create unit here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = unit,
            size = unitSize,
            color = unitColor,
            modifier = Modifier
                .constrainAs(unitId) {
                    baseline.linkTo(resultId.baseline)
                    start.linkTo(
                        resultId.end,
                        dimen.dimen_1_5.dp
                    )
                }
        )

    }//end ConstraintLayout

}//end StatusResultSection