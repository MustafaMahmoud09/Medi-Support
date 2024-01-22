package com.example.activity.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.activity.uiElement.components.composable.LineChartView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.core.entry.ChartEntryModel

@Composable
internal fun SingleLineChartSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    xAxisData: List<String> = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
    data: ChartEntryModel,
    maxValue: Float,
    roundSize: Float = dimen.dimen_2,
    modifier: Modifier = Modifier
) {

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

        LineChartView(
            dimen = dimen,
            theme = theme,
            data = data,
            maxValue = maxValue,
            xAxisData = xAxisData,
            modifier = Modifier
                .fillMaxSize()
        )

    }//end Box

}//end SingleLineChartSection