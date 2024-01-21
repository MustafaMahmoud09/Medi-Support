package com.example.sharedui.uiElement.components.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.axis.formatter.DecimalFormatAxisValueFormatter
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions
import com.patrykandpatrick.vico.core.entry.entryModelOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ColumnChartView(
    dimen: CustomDimen,
    theme: CustomTheme,
    axis: LineComponent = axisLineComponent(
        color = theme.transparent,
        thickness = dimen.dimen_0.dp,
        strokeWidth = dimen.dimen_0.dp,
        strokeColor = theme.transparent,
        brush = null,
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
    guideLine: LineComponent = axisLineComponent(
        color = theme.transparent,
        thickness = dimen.dimen_0.dp,
        strokeWidth = dimen.dimen_0.dp,
        strokeColor = theme.transparent,
        brush = null,
        margins = emptyDimensions(),
    ),
    label: TextComponent = axisLabelComponent(
        color = theme.black,
        textSize = dimen.dimen_1_5.sp
    ),
    columnStyle: LineComponent = LineComponent(
        color = theme.redDark.toArgb(),
        thicknessDp = dimen.dimen_1_75,
        shape = Shapes.roundedCornerShape(90),
        strokeWidthDp = dimen.dimen_0_125,
        strokeColor = theme.redDark.toArgb()
    ),
    isZoomEnabled: Boolean = false,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
    ) {

        Chart(
            chart = columnChart(
                columns = listOf(columnStyle),
                spacing = dimen.dimen_3.dp
            ),
            model = entryModelOf(4f, 12f, 8f, 16f, 12f, 8f, 16f),
            startAxis = rememberStartAxis(
                axis = axis,
                tick = tick,
                guideline = guideLine,
                label = label,
                valueFormatter = DecimalFormatAxisValueFormatter("10")
            ),
            bottomAxis = rememberBottomAxis(
                axis = axis,
                tick = tick,
                guideline = guideLine,
                label = label,
                valueFormatter = DecimalFormatAxisValueFormatter("10")
            ),
            isZoomEnabled = isZoomEnabled,
            modifier = Modifier
                .fillMaxSize()
        )

    }//end Box

}//end ColumnChartSection
