package com.example.auth.presentation.uiState.state

data class RegisterUiState(
    val firstNameKey: String = "",
    val lastNameKey: String = "",
    val emailKey: String = "",
    val passwordKey: String = "",
    val rememberKey: Boolean = false,
    val registerEventState: RegisterEventState = RegisterEventState()
)


data class RegisterEventState(
    val emailNotValid: Boolean = false,
    val success: Boolean = false,
    val serverError: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val inputsError: Boolean = false
)