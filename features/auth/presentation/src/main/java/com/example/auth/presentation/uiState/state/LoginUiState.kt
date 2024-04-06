package com.example.auth.presentation.uiState.state

data class LoginUiState(
    val emailKey: String = "",
    val passwordKey: String = "",
    val rememberKey: Boolean = false,
    val pageLoad: Boolean = true,
)
