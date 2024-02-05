package com.example.auth.uiState.state

internal data class RegisterUiState(
    val firstNameKey: String = "",
    val lastNameKey: String = "",
    val emailKey: String = "",
    val passwordKey: String = "",
    val rememberKey: Boolean = false
)
