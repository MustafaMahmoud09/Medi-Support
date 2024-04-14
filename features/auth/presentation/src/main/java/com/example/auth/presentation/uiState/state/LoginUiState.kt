package com.example.auth.presentation.uiState.state

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import kotlinx.coroutines.flow.MutableStateFlow

data class LoginUiState(
    val emailKey: String = "",
    val passwordKey: String = "",
    val rememberKey: Boolean = false,
    val pageLoad: Boolean = true,
    val facebookCallbackManager: CallbackManager? = null,
    val firstRunningState: Boolean = true,
    val socialAccessToken: MutableStateFlow<String> = MutableStateFlow(""),
    val provider: String = "",
    val loginEventStatus: LoginWithEmailEventStatus = LoginWithEmailEventStatus(),
    val facebookLoginManager: LoginManager? = null
)

data class LoginWithEmailEventStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val dataNotValid: Boolean = false,
    val unAuthorize: Boolean = false,
    val serverError: Boolean = false,
    val dataNotFound: Boolean = false,
    val loadingType: Int = -1,
    val alreadyAuthorized: Boolean = false
)


