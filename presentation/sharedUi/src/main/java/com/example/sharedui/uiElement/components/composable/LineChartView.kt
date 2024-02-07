package com.example.sharedui.uiElement.components.composable

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
import com.patrykandpatrick.vico.core.axis.AxisRenderer
import com.patrykandpatrick.vico.core.axis.formatter.DecimalFormatAxisValueFormatter
import com.patrykandpatrick.vico.core.chart.DefaultPointConnector
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun LineChartView(
    dimen: CustomDimen,
    theme: CustomTheme,
    data: ChartEntryModel,
    maxValue: Float,
    bottomAxisVisibility: Boolean = true,
    endAxisVisibility: Boolean = true,
    startAxisVisibility: Boolean = true,
    xAxisData: List<String>,
    isZoomEnabled: Boolean = false,
    maxYLines: Int = 6,
    guideLine: LineComponent?,
    axis: LineComponent? = axisGuidelineComponent(
        color = theme.grayECECEC,
        strokeColor = theme.grayECECEC,
        shape = currentChartStyle.axis.axisLineShape,
        margins = emptyDimensions(),
    ),
    tick: LineComponent? = axisLineComponent(
        color = theme.transparent,
        thickness = dimen.dimen_0.dp,
        strokeWidth = dimen.dimen_0.dp,
        strokeColor = theme.transparent,
        brush = null,
        margins = emptyDimensions(),
    ),
    startLabel: TextComponent? = axisLabelComponent(
        color = theme.black,
        textSize = dimen.dimen_1_5.sp
    ),
    bottomLabel: TextComponent? = axisLabelComponent(
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
    emptyLineStyle: LineChart.LineSpec = LineChart.LineSpec(
        lineColor = theme.transparent.toArgb(),
        lineThicknessDp = dimen.dimen_0_25,
        pointConnector = DefaultPointConnector(
            cubicStrength = 0f
        )
    ),
    startAxis: AxisRenderer<AxisPosition.Vertical.Start>? = if (startAxisVisibility) {
        rememberStartAxis(
            axis = if (bottomAxisVisibility) axis else null,
            tick = tick,
            guideline = guideLine,
            label = startLabel,
            itemPlacer = AxisItemPlacer.Vertical.default(
                maxYLines,
                false
            ),
            valueFormatter = DecimalFormatAxisValueFormatter("0")
        )
    } else null,
    endAxis: AxisRenderer<AxisPosition.Vertical.End>? = if (endAxisVisibility) {
        rememberEndAxis(
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
        )
    } else null,
    bottomAxis: AxisRenderer<AxisPosition.Horizontal.Bottom>? = if (bottomAxisVisibility) {
        rememberBottomAxis(
            valueFormatter = { x, _ -> xAxisData[x.toInt() % xAxisData.size] },
            axis = axis,
            guideline = guideLine,
            tick = guideLine,
            tickLength = 0.25.dp,
            label = bottomLabel
        )
    } else null,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
    ) {

        //if max value > 0 then draw chart
        if (maxValue != 0f) {

            //create chart here
            Chart(
                model = data,
                chart = lineChart(
                    lines = listOf(lineStyle)
                ),
                startAxis = startAxis,
                bottomAxis = bottomAxis,
                endAxis = endAxis,
                isZoomEnabled = isZoomEnabled,
                modifier = Modifier
                    .fillMaxSize()
            )

        }//end if

        //if max value = 0 then draw empty chart
        else {

            //create chart here
            Chart(
                model = entryModelOf(120f, 120f, 120f, 120f, 120f, 120f, 120f),
                chart = lineChart(
                    lines = listOf(emptyLineStyle)
                ),
                startAxis = startAxis,
                bottomAxis = bottomAxis,
                endAxis = endAxis,
                isZoomEnabled = isZoomEnabled,
                modifier = Modifier
                    .fillMaxSize()
            )

        }//end else

    }//end Box

}//end LineChartView