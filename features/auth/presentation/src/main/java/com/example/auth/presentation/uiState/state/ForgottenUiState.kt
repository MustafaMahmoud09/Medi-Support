package com.example.auth.presentation.uiState.state


data class ForgottenUiState(
    val emailKey: String = "",
    val firstCodeKey: String = "",
    val secondCodeKey: String = "",
    val thirdCodeKey: String = "",
    val fourthCodeKey: String = "",
    val newPasswordKey: String = "",
    val confirmNewPasswordKey: String = "",
    val sendEmailEventStatus: EventStatus = EventStatus(),
    val verifyCodeEventStatus: EventStatus = EventStatus(),
    val resetPasswordEventStatus: EventStatus = EventStatus()
)

