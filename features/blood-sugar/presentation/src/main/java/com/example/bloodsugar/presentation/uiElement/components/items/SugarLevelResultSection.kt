package com.example.bloodsugar.presentation.uiElement.components.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun SugarLevelResultSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    status: String,
    statusColor: Color = theme.black,
    statusSize: Float = dimen.dimen_2,
    level: String,
    levelColor: Color = theme.redDark,
    levelSize: Float = dimen.dimen_4,
    unit: String,
    unitColor: Color = theme.redDark,
    unitSize: Float = dimen.dimen_2,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (statusId, levelId, unitId) = createRefs()

        //create status text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = status,
            size = statusSize,
            fontColor = statusColor,
            modifier = Modifier
                .constrainAs(statusId) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        //create level here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = level,
            size = levelSize,
            fontColor = levelColor,
            modifier = Modifier
                .constrainAs(levelId) {
                    start.linkTo(
                        statusId.end,
                        dimen.dimen_0_5.dp
                    )
                    top.linkTo(statusId.top)
                    bottom.linkTo(statusId.bottom)
                }
        )

        //create unit here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = unit,
            size = unitSize,
            fontColor = unitColor,
            modifier = Modifier
                .constrainAs(unitId) {
                    baseline.linkTo(levelId.baseline)
                    start.linkTo(
                        levelId.end,
                        dimen.dimen_1.dp
                    )
                }
        )

    }//end ConstraintLayout

}//end StatusResultSection