package com.example.bloodsuger.uiElement.components.composable


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes.roundedCornerShape
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryOf
import kotlin.random.Random

@Composable
fun ColumnChartView(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    axis: LineComponent = axisLineComponent(
        color = Color.Transparent,
        thickness = dimen.dimen_0.dp,
        strokeWidth = dimen.dimen_0.dp,
        strokeColor = Color.Transparent,
        brush = null,
        margins = emptyDimensions(),
    ),
    tick: LineComponent = axisLineComponent(
        color = Color.Transparent,
        thickness = dimen.dimen_0.dp,
        strokeWidth = dimen.dimen_0.dp,
        strokeColor = Color.Transparent,
        brush = null,
        margins = emptyDimensions(),
    ),
    guideLine: LineComponent = axisLineComponent(
        color = Color.Transparent,
        thickness = dimen.dimen_0.dp,
        strokeWidth = dimen.dimen_0.dp,
        strokeColor = Color.Transparent,
        brush = null,
        margins = emptyDimensions(),
    ),
    columnStyle: LineComponent = LineComponent(
        color = 0xFF939393.toInt(),
        thicknessDp = dimen.dimen_2,
        shape = roundedCornerShape(90)
    ),
    modifier: Modifier = Modifier
) {

    val chartEntryModelProducer = ChartEntryModelProducer(getRandomEntries())

    Chart(
        chart = columnChart(
            columns = listOf(columnStyle)
        ),
        chartModelProducer = chartEntryModelProducer,
        startAxis = rememberStartAxis(
            axis = axis,
            tick = tick,
            guideline = guideLine
        ),
        bottomAxis = rememberBottomAxis(
            axis = axis,
            tick = tick,
            guideline = guideLine
        ),
        isZoomEnabled = false,
        modifier = modifier
    )
}//end ColumnChartView


fun getRandomEntries(): List<FloatEntry> {

    return List(7) {
        entryOf(it+10, Random.nextFloat() * 100f )
    }
}