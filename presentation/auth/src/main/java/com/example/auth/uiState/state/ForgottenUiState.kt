package com.example.auth.uiState.state

internal data class ForgottenUiState(
    val emailKey: String = "",
    val firstCodeKey: String = "",
    val secondCodeKey: String = "",
    val thirdCodeKey: String = "",
    val fourthCodeKey: String = "",
    val newPasswordKey: String = "",
    val confirmNewPasswordKey: String = ""
)
