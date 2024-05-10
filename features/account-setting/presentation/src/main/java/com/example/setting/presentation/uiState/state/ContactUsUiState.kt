package com.example.setting.presentation.uiState.state

data class ContactUsUiState(
    val userNameValue: String = "",
    val emailValue: String = "",
    val messageValue: String = "",
    val sendContactUsEventStatus: SendContactUsEventStatus = SendContactUsEventStatus()
)

data class SendContactUsEventStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val emailError: Boolean = false,
    val dataError: Boolean = false
)