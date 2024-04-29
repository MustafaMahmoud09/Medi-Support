package com.example.bmi.presentation.uiState.state

import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf

data class GetChartDataStatus(
    val load: Boolean = true,
    val xAxisData: List<String> = emptyList(),
    val dataResult: ChartEntryModel = entryModelOf(0f),
    val maxValue: Long = 0
)