package com.example.activity.uiElement.components.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberEndAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.axis.formatter.DecimalFormatAxisValueFormatter
import com.patrykandpatrick.vico.core.chart.DefaultPointConnector
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
internal fun LineChartView(
    dimen: CustomDimen,
    theme: CustomTheme,
    axis: LineComponent = axisGuidelineComponent(
        color = theme.gray4,
        strokeColor = theme.gray4,
        shape = currentChartStyle.axis.axisLineShape,
        margins = emptyDimensions(),
    ),
    tick: LineComponent = axisLineComponent(
        color = theme.transparent,
        thickness = dimen.dimen_0.dp,
        strokeWidth = dimen.dimen_0.dp,
        strokeColor = theme.transparent,
        brush = null,
        margins = emptyDimensions(),
    ),
    guideLine: LineComponent = axisGuidelineComponent(
        color = theme.gray4,
        strokeColor = theme.gray4,
        shape = currentChartStyle.axis.axisLineShape,
        margins = emptyDimensions(),
    ),
    label: TextComponent = axisLabelComponent(
        color = theme.black,
        textSize = dimen.dimen_1_5.sp
    ),
    lineStyle: LineChart.LineSpec = LineChart.LineSpec(
        lineColor = theme.redDark.toArgb(),
        lineThicknessDp = dimen.dimen_0_25,
        pointConnector = DefaultPointConnector(
            cubicStrength = 0f
        )

    ),
    isZoomEnabled: Boolean = false,
    xAxisData: List<String> = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
    data: ChartEntryModel,
    maxValue: Float,
    maxYLines: Int = 6,
    modifier: Modifier = Modifier
) {

    val bottomAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, _ -> xAxisData[x.toInt() % xAxisData.size] }

    Box(
        modifier = modifier
    ) {


        if (maxValue != 0f) {

            Chart(
                chart = lineChart(
                    lines = listOf(lineStyle)
                ),
                model = data,
                startAxis = rememberStartAxis(
                    axis = axis,
                    tick = tick,
                    guideline = guideLine,
                    label = label,
                    itemPlacer = AxisItemPlacer.Vertical.default(
                        maxYLines,
                        false
                    ),
                    valueFormatter = DecimalFormatAxisValueFormatter("0")
                ),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = bottomAxisValueFormatter,
                    axis = axis,
                    guideline = guideLine,
                    tick = guideLine,
                    tickLength = 0.25.dp,
                    label = label
                ),
                endAxis = rememberEndAxis(
                    axis = axis,
                    guideline = axisLineComponent(
                        color = theme.transparent,
                        thickness = dimen.dimen_0.dp,
                        strokeWidth = dimen.dimen_0.dp,
                        strokeColor = theme.transparent,
                        brush = null,
                        margins = emptyDimensions(),
                    ),
                    tick = tick,
                    label = axisLabelComponent(
                        color = theme.background,
                        textSize = dimen.dimen_0.sp
                    )
                ),
                isZoomEnabled = isZoomEnabled,
                modifier = Modifier
                    .fillMaxSize()
            )

        }//end if
        else {

            Chart(
                chart = lineChart(
                    lines = listOf(
                        LineChart.LineSpec(
                            lineColor = theme.transparent.toArgb(),
                            lineThicknessDp = dimen.dimen_0_25,
                            pointConnector = DefaultPointConnector(
                                cubicStrength = 0f
                            )
                        )
                    )
                ),
                model = entryModelOf(120f, 120f, 120f, 120f, 120f, 120f, 120f),
                startAxis = rememberStartAxis(
                    axis = axis,
                    tick = tick,
                    guideline = guideLine,
                    label = label,
                    itemPlacer = AxisItemPlacer.Vertical.default(
                        maxYLines,
                        false
                    ),
                    valueFormatter = DecimalFormatAxisValueFormatter("0")
                ),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = bottomAxisValueFormatter,
                    axis = axis,
                    guideline = guideLine,
                    tick = guideLine,
                    tickLength = 0.25.dp,
                    label = label
                ),
                endAxis = rememberEndAxis(
                    axis = axis,
                    guideline = axisLineComponent(
                        color = theme.transparent,
                        thickness = dimen.dimen_0.dp,
                        strokeWidth = dimen.dimen_0.dp,
                        strokeColor = theme.transparent,
                        brush = null,
                        margins = emptyDimensions(),
                    ),
                    tick = tick,
                    label = axisLabelComponent(
                        color = theme.background,
                        textSize = dimen.dimen_0.sp
                    )
                ),
                isZoomEnabled = isZoomEnabled,
                modifier = Modifier
                    .fillMaxSize()
            )

        }//end else

    }//end Box

}//end LineChartView