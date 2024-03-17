package com.example.reminder.presentation.uiState.state

import androidx.paging.PagingData
import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel
import kotlinx.coroutines.flow.Flow

data class RemindersUiState(
    val reminders: Flow<PagingData<ReminderPresentationModel>>? = null,
    val remindersBackup: Flow<PagingData<ReminderPresentationModel>>? = null
)