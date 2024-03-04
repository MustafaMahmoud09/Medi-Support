package com.example.reminder.presentation.uiState.state

import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel

data class RemindersUiState(
    val reminders: List<ReminderPresentationModel> = emptyList()
)