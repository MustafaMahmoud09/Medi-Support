package com.example.auth.presentation.uiState.state

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import kotlinx.coroutines.flow.MutableStateFlow

data class RegisterUiState(
    val firstNameKey: String = "",
    val lastNameKey: String = "",
    val emailKey: String = "",
    val passwordKey: String = "",
    val rememberKey: Boolean = false,
    val firstRunningState: Boolean = true,
    val registerEventState: EventStatus = EventStatus(),
    val socialAccessToken: MutableStateFlow<String> = MutableStateFlow(""),
    val provider: String = "",
    val facebookLoginManager: LoginManager? = null,
    val facebookCallbackManager: CallbackManager? = null,
)
