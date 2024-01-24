package com.example.activity.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.activity.uiElement.components.composable.LineChartView
import com.example.sharedui.uiElement.components.composable.IconTitleView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.core.entry.ChartEntryModel

@Composable
internal fun DoubleLineChartSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    xAxisData: List<String> = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
    data1: ChartEntryModel,
    data2: ChartEntryModel,
    maxValue1: Float,
    maxValue2: Float,
    titleChart1: String,
    titleChart2: String,
    unit: String,
    roundSize: Float = dimen.dimen_1_25,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    size = roundSize.dp
                )
            )
            .border(
                width = dimen.dimen_0_125.dp,
                color = theme.redDark,
                shape = RoundedCornerShape(
                    size = roundSize.dp
                )
            )
            .padding(
                bottom = dimen.dimen_1_75.dp
            )
    ) {
        val (firstTitleId, unitOneId, firstChartId, lineId, secondTitleId, unitTwoId, secondChartId) = createRefs()


        IconTitleView(
            theme = theme,
            dimen = dimen,
            title = titleChart1,
            modifier = Modifier
                .constrainAs(firstTitleId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_1_75.dp
                    )
                }
        )

        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = unit,
            size = dimen.dimen_1_75,
            fontColor = theme.black,
            modifier = Modifier
                .constrainAs(unitOneId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_1_75.dp
                    )
                }
        )

        LineChartView(
            dimen = dimen,
            theme = theme,
            data = data1,
            maxValue = maxValue1,
            xAxisData = xAxisData,
            maxYLines = 5,
            modifier = Modifier
                .constrainAs(firstChartId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_25.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        firstTitleId.bottom,
                        dimen.dimen_3_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(1.8f)
        )

        LineView(
            dimen = dimen,
            theme = theme,
            color = theme.redDark,
            modifier = Modifier
                .constrainAs(lineId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        firstChartId.bottom,
                        dimen.dimen_1_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        IconTitleView(
            theme = theme,
            dimen = dimen,
            title = titleChart2,
            modifier = Modifier
                .constrainAs(secondTitleId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(
                        lineId.top,
                        dimen.dimen_1.dp
                    )
                }
        )

        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = unit,
            size = dimen.dimen_1_75,
            fontColor = theme.black,
            modifier = Modifier
                .constrainAs(unitTwoId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )

                    top.linkTo(
                        lineId.top,
                        dimen.dimen_1.dp
                    )
                }
        )

        LineChartView(
            dimen = dimen,
            theme = theme,
            data = data2,
            maxValue = maxValue2,
            xAxisData = xAxisData,
            maxYLines = 4,
            modifier = Modifier
                .constrainAs(secondChartId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_25.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        secondTitleId.bottom,
                        dimen.dimen_3_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(2f)
        )

    }//end Box

}//end DoubleLineChartSection