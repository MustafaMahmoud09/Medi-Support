package com.example.setting.presentation.uiState.state

data class EventStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false
)
