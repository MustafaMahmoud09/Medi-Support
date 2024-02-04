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
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.AxisRenderer
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.axis.formatter.DecimalFormatAxisValueFormatter
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.ChartEntryModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ColumnChartView(
    dimen: CustomDimen,
    theme: CustomTheme,
    spacingBetweenColumns: Float = dimen.dimen_3,
    isZoomEnabled: Boolean = false,
    xAxisData: List<String> = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
    data: ChartEntryModel,
    maxValue: Float,
    maxItemCount: Int = 8,
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
    bottomLabel: TextComponent = axisLabelComponent(
        color = theme.black,
        textSize = dimen.dimen_1_5.sp
    ),
    startLabel: TextComponent = axisLabelComponent(
        color = theme.black,
        textSize = dimen.dimen_1_75.sp
    ),
    columnStyle: LineComponent = LineComponent(
        color = theme.redDark.toArgb(),
        thicknessDp = dimen.dimen_1_75,
        shape = Shapes.roundedCornerShape(90),
        strokeWidthDp = dimen.dimen_0_125,
        strokeColor = theme.redDark.toArgb()
    ),
    emptyColumnStyle: LineComponent = LineComponent(
        color = theme.background.toArgb(),
        thicknessDp = dimen.dimen_1_75,
        shape = Shapes.roundedCornerShape(90),
        strokeWidthDp = dimen.dimen_0_125,
        strokeColor = theme.redDark.toArgb()
    ),
    bottomValueFormatter: AxisValueFormatter<AxisPosition.Horizontal.Bottom> = AxisValueFormatter { x, _ ->
        xAxisData[x.toInt() % xAxisData.size]
    },
    startAxis: AxisRenderer<AxisPosition.Vertical.Start>? = rememberStartAxis(
        axis = axis,
        tick = tick,
        guideline = guideLine,
        label = startLabel,
        itemPlacer = AxisItemPlacer.Vertical.default(
            maxItemCount,
            false
        ),
        valueFormatter = DecimalFormatAxisValueFormatter("0")
    ),
    bottomAxis: AxisRenderer<AxisPosition.Horizontal.Bottom>? = rememberBottomAxis(
        valueFormatter = bottomValueFormatter,
        axis = axis,
        tick = tick,
        guideline = guideLine,
        label = bottomLabel
    ),
    hintBottomAxis: AxisRenderer<AxisPosition.Horizontal.Bottom>? = rememberBottomAxis(
        valueFormatter = bottomValueFormatter,
        axis = axis,
        tick = tick,
        guideline = guideLine,
        label = axisLabelComponent(
            color = if (maxValue == 0f) theme.black else theme.background,
            textSize = dimen.dimen_1_5.sp
        )
    ),
    hintStartAxis: AxisRenderer<AxisPosition.Vertical.Start>? = rememberStartAxis(
        axis = axis,
        tick = tick,
        guideline = guideLine,
        label = axisLabelComponent(
            color = if (maxValue == 0f) theme.black else theme.background,
            textSize = dimen.dimen_1_75.sp
        ),
        itemPlacer = AxisItemPlacer.Vertical.default(
            8,
            false
        ),
        valueFormatter = DecimalFormatAxisValueFormatter("0")
    ),
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
    ) {

        Chart(
            chart = columnChart(
                columns = listOf(emptyColumnStyle),
                spacing = spacingBetweenColumns.dp
            ),
            model = if (maxValue == 0f) {
                entryModelOf(350f, 350f, 350f, 350f, 350f, 350f, 350f)
            } else {
                entryModelOf(maxValue, maxValue, maxValue, maxValue, maxValue, maxValue, maxValue)
            },
            startAxis = hintStartAxis,
            bottomAxis = hintBottomAxis,
            isZoomEnabled = isZoomEnabled,
            modifier = Modifier
                .fillMaxSize()
        )


        //if max value not equal 0 draw chart
        if (maxValue != 0f) {

            Chart(
                chart = columnChart(
                    columns = listOf(columnStyle),
                    spacing = spacingBetweenColumns.dp
                ),
                model = data,
                startAxis = startAxis,
                bottomAxis = bottomAxis,
                isZoomEnabled = isZoomEnabled,
                modifier = Modifier
                    .fillMaxSize()
            )

        }//end if

    }//end Box

}//end ColumnChartSection


//
//class CustomVerticalAxisItemPlacer(
//    private val items: List<Float>,
//    private val shiftTopLines: Boolean
//) : AxisItemPlacer.Vertical {
//
//
//    override fun getShiftTopLines(chartDrawContext: ChartDrawContext): Boolean = shiftTopLines
//
//    override fun getLabelValues(
//        context: ChartDrawContext,
//        axisHeight: Float,
//        maxLabelHeight: Float,
//        position: AxisPosition.Vertical,
//    ) = getWidthMeasurementLabelValues(context, axisHeight, maxLabelHeight, position)
//
//    override fun getWidthMeasurementLabelValues(
//        context: MeasureContext,
//        axisHeight: Float,
//        maxLabelHeight: Float,
//        position: AxisPosition.Vertical,
//    ): List<Float> = items
//
//    override fun getHeightMeasurementLabelValues(
//        context: MeasureContext,
//        position: AxisPosition.Vertical,
//    ): List<Float> {
//        val chartValues = context.chartValuesManager.getChartValues(position)
//        return listOf(0f, (350f).half, 350f)
//    }
//
//    override fun getTopVerticalAxisInset(
//        verticalLabelPosition: VerticalAxis.VerticalLabelPosition,
//        maxLabelHeight: Float,
//        maxLineThickness: Float,
//    ) = when (verticalLabelPosition) {
//        VerticalAxis.VerticalLabelPosition.Top ->
//            maxLabelHeight + (if (shiftTopLines) maxLineThickness else -maxLineThickness).half
//
//        VerticalAxis.VerticalLabelPosition.Center ->
//            (max(maxLabelHeight, maxLineThickness) + if (shiftTopLines) maxLineThickness else -maxLineThickness).half
//
//        VerticalAxis.VerticalLabelPosition.Bottom -> if (shiftTopLines) maxLineThickness else 0f
//    }
//
//    override fun getBottomVerticalAxisInset(
//        verticalLabelPosition: VerticalAxis.VerticalLabelPosition,
//        maxLabelHeight: Float,
//        maxLineThickness: Float,
//    ): Float = when (verticalLabelPosition) {
//        VerticalAxis.VerticalLabelPosition.Top -> maxLineThickness
//        VerticalAxis.VerticalLabelPosition.Center -> (maxOf(maxLabelHeight, maxLineThickness) + maxLineThickness).half
//        VerticalAxis.VerticalLabelPosition.Bottom -> maxLabelHeight + maxLineThickness.half
//    }
//
//}
