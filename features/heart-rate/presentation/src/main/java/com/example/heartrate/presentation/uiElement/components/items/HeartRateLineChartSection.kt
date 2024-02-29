package com.example.heartrate.presentation.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.components.composable.LineChartView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModel

@Composable
internal fun HeartRateLineChartSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    xAxisData: List<String>,
    data: ChartEntryModel,
    maxValue: Float,
    guideLine: LineComponent? = axisGuidelineComponent(
        color = theme.grayECECEC,
        strokeColor = theme.grayECECEC,
        shape = currentChartStyle.axis.axisLineShape,
        margins = emptyDimensions(),
    ),
    bottomLabel: TextComponent? = axisLabelComponent(
        color = theme.black,
        textSize = dimen.dimen_1_5.sp
    ),
    roundSize: Float = dimen.dimen_2,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
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
                horizontal = dimen.dimen_1_25.dp,
                vertical = dimen.dimen_2_25.dp
            )
    ) {

        //create line chart here
        LineChartView(
            dimen = dimen,
            theme = theme,
            data = data,
            maxValue = maxValue,
            xAxisData = xAxisData,
            guideLine = guideLine,
            bottomLabel = bottomLabel,
            modifier = Modifier
                .fillMaxSize()
        )

    }//end Box

}//end SingleLineChartSection