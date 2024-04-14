package com.example.auth.presentation.uiState.state

data class EventStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val dataNotValid: Boolean = false,
    val serverError: Boolean = false,
    val dataNotFound: Boolean = false,
    val alreadyAuthorized: Boolean = false,
    val loadingType: Int = -1
)