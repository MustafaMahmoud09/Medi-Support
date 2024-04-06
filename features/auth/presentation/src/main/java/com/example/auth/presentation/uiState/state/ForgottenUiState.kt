package com.example.auth.presentation.uiState.state

data class ForgottenUiState(
    val emailKey: String = "",
    val firstCodeKey: String = "",
    val secondCodeKey: String = "",
    val thirdCodeKey: String = "",
    val fourthCodeKey: String = "",
    val newPasswordKey: String = "",
    val confirmNewPasswordKey: String = "",
    val sendEmailEventStatus: ForgottenEventStatus = ForgottenEventStatus(),
    val verifyCodeEventStatus: ForgottenEventStatus = ForgottenEventStatus(),
    val resetPasswordEventStatus: ForgottenEventStatus = ForgottenEventStatus()
)

data class ForgottenEventStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val dataNotValid: Boolean = false,
    val serverError: Boolean = false,
    val dataNotFound: Boolean = false
)
