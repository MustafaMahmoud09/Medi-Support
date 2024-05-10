package com.example.setting.presentation.uiState.state

data class MoreUiState(
    val deleteDialogState: Boolean = false,
    val logoutDialogState: Boolean = false,
    val deleteUserAccountStatus: EventStatus = EventStatus(),
    val logoutStatus: EventStatus = EventStatus()
)