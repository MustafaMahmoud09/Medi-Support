package com.example.auth.presentation.uiState.state

internal data class LoginUiState(
    val emailKey: String = "",
    val passwordKey: String = "",
    val rememberKey: Boolean = false,
    val pageLoad: Boolean = true
)
