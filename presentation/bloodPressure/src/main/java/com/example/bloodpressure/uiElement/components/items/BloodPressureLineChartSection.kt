package com.example.bloodpressure.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.LineChartView
import com.example.sharedui.uiElement.components.composable.IconTitleView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.appShadow
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModel

@Composable
fun BloodPressureLineChartSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    xAxisData: List<String>,
    firstData: ChartEntryModel,
    secondData: ChartEntryModel,
    maxValueForFirstData: Float,
    maxValueForSecondData: Float,
    titleForFirstChart: String,
    titleForSecondChart: String,
    maxYLinesForFirstChart: Int = 5,
    maxYLinesForSecondChart: Int = 4,
    unit: String,
    bottomAxisVisibility: Boolean = true,
    endAxisVisibility: Boolean = true,
    startAxisVisibility: Boolean = true,
    guideLine: LineComponent? = axisGuidelineComponent(
        color = theme.grayECECEC,
        strokeColor = theme.grayECECEC,
        shape = currentChartStyle.axis.axisLineShape,
        margins = emptyDimensions(),
    ),
    roundSize: Float = dimen.dimen_1_25,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .appShadow(
                shape = RoundedCornerShape(
                    size = roundSize.dp
                ),
                spotColor = theme.blackTR25,
                ambientColor = theme.blackTR25,
                elevation = dimen.dimen_0_5
            )
            .padding(
                dimen.dimen_0_5.dp
            )
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .appBorder(
                    shape = RoundedCornerShape(
                        size = roundSize.dp
                    ),
                    borderWidth = dimen.dimen_0_125,
                    borderColor = theme.redDark
                )
                .background(
                    color = theme.background
                )
                .padding(
                    bottom = dimen.dimen_1_75.dp
                ),
        ) {
            val (firstTitleId, unitOneId, firstChartId, lineId, secondTitleId, unitTwoId, secondChartId) = createRefs()


            IconTitleView(
                theme = theme,
                dimen = dimen,
                title = titleForFirstChart,
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
                data = firstData,
                maxValue = maxValueForFirstData,
                xAxisData = xAxisData,
                maxYLines = maxYLinesForFirstChart,
                bottomAxisVisibility = bottomAxisVisibility,
                endAxisVisibility = endAxisVisibility,
                startAxisVisibility = startAxisVisibility,
                guideLine = guideLine,
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
                title = titleForSecondChart,
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
                data = secondData,
                maxValue = maxValueForSecondData,
                xAxisData = xAxisData,
                maxYLines = maxYLinesForSecondChart,
                bottomAxisVisibility = bottomAxisVisibility,
                endAxisVisibility = endAxisVisibility,
                startAxisVisibility = startAxisVisibility,
                guideLine = guideLine,
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

        }//end ConstraintLayout

    }//end Box

}//end BloodPressureLineChartSection