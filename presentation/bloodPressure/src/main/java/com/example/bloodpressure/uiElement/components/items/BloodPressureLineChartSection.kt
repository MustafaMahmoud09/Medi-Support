package com.example.bloodpressure.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.LineChartView
import com.example.sharedui.uiElement.components.composable.IconTitleView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.compose.component.ChartShape
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.entry.ChartEntryModel

@Composable
fun BloodPressureLineChartSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    xAxisData: List<String> = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
    data1: ChartEntryModel,
    data2: ChartEntryModel,
    maxValue1: Float,
    maxValue2: Float,
    titleChart1: String,
    titleChart2: String,
    yLinesChart1: Int = 5,
    yLinesChart2: Int = 4,
    unit: String,
    bottomAxisVisibility: Boolean = true,
    endAxisVisibility: Boolean = true,
    startAxisVisibility: Boolean = true,
    guideLineShape: ChartShape = currentChartStyle.axis.axisLineShape,
    guideLineColor: Color = theme.grayECECEC,
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
            maxYLines = yLinesChart1,
            bottomAxisVisibility = bottomAxisVisibility,
            endAxisVisibility = endAxisVisibility,
            startAxisVisibility = startAxisVisibility,
            guideLineShape = guideLineShape,
            guideLineColor = guideLineColor,
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
                .aspectRatio(1.9f)
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
                },
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
            maxYLines = yLinesChart2,
            bottomAxisVisibility = bottomAxisVisibility,
            endAxisVisibility = endAxisVisibility,
            startAxisVisibility = startAxisVisibility,
            guideLineShape = guideLineShape,
            guideLineColor = guideLineColor,
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
                .aspectRatio(2.7f)
        )

    }//end Box

}//end BloodPressureLineChartSection