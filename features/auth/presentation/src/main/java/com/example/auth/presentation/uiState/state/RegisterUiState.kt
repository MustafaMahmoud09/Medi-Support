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
    val inputsError: Boolean = false,
    val registerSuccess: Boolean = false,
    val serverError: Boolean = false
)