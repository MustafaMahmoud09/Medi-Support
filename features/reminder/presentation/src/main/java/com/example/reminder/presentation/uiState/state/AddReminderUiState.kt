package com.example.reminder.presentation.uiState.state

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.reminder.domaim.domain.model.Day
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
data class AddReminderUiState(
    val isDatePickerVisible: Boolean = false,
    val medicamentName: String = "",
    val daysValue: String = "",
    val timeValue: String = "",
    val timeSelected: LocalTime = LocalTime.now(),
    val timeSelectedBackup: LocalTime = LocalTime.now(),
    val weekDays: List<Day> = emptyList(),
    val daysSelected: List<Long> = emptyList(),
    val daysSelectedBackup: List<Long> = emptyList(),
)



