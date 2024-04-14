package com.example.auth.presentation.uiState.viewModel

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.usecase.declarations.ILoginWithEmailUseCase
import com.example.auth.domain.usecase.declarations.ILoginWithSocialUseCase
import com.example.auth.presentation.uiState.state.LoginUiState
import com.example.libraries.core.remote.data.response.status.Status
import com.example.sharedui.uiState.viewModel.BaseViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithEmailUserCase: ILoginWithEmailUseCase,
    private val loginWithSocialUseCase: ILoginWithSocialUseCase,
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(LoginUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        //finish page load after 900 millis here
        viewModelScope.launch {

            delay(900)
            onPageLoadFinished()

        }//end Launch

        //get object from facebook call back manager
        _state.update {
            it.copy(
                facebookCallbackManager = CallbackManager.Factory.create(),
                facebookLoginManager = LoginManager.getInstance()
            )
        }

        //make subscribe for callback manager
        handleFacebookCallbackResult()

        //execute login with social here
        onExecuteLoginWithSocial()

    }//end init

    fun onEmailChanged(newValue: String) {

        //update email here
        _state.update {
            it.copy(
                emailKey = newValue
            )
        }//end update

    }//end onEmailChanged

    fun onPasswordChanged(newValue: String) {

        //update password here
        _state.update {
            it.copy(
                passwordKey = newValue
            )
        }//end update

    }//end onPasswordChanged

    fun onRememberChanged(newValue: Boolean) {

        //update remember here
        _state.update {
            it.copy(
                rememberKey = newValue
            )
        }//end update

    }//end onRememberChanged

    private fun onPageLoadFinished() {

        //update page load here
        _state.update {
            it.copy(
                pageLoad = false
            )
        }//end update

    }//end onPageLoadFinished


    //function for login with email
    fun onExecuteLoginWithEmail() {

        onFirstRunningStateChanged(false)

        //get user data
        val email = state.value.emailKey
        val password = state.value.passwordKey
        val remember = state.value.rememberKey

        //if user is not empty execute login request
        if (
            email.isNotEmpty() &&
            password.length >= 8
        ) {

            //create coroutine builder here
            viewModelScope.launch(Dispatchers.IO) {

                try {

                    //execute login request here
                    //observe flow of status request here
                    loginWithEmailUserCase(
                        email = email,
                        password = password,
                        remember = remember
                    ).collectLatest { status ->

                        when (status) {

                            //if status is success
                            is Status.Success -> {

                                when (status.toData()) {

                                    //if status code equal 200
                                    200 -> {

                                        _state.update {
                                            it.copy(
                                                loginEventStatus = state.value.loginEventStatus.copy(
                                                    success = true,
                                                    loading = false
                                                )
                                            )
                                        }//end update

                                    }//end success case

                                    //if status code equal 401
                                    //data is unAuthorize
                                    401 -> {

                                        _state.update {
                                            it.copy(
                                                loginEventStatus = state.value.loginEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    unAuthorize = !state.value.loginEventStatus.unAuthorize
                                                )
                                            )
                                        }//end update

                                    }//end

                                    //status code equal 422
                                    //status is data not valid
                                    422 -> {

                                        _state.update {
                                            it.copy(
                                                loginEventStatus = state.value.loginEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    dataNotValid = !state.value.loginEventStatus.dataNotValid
                                                )
                                            )
                                        }

                                    }//end failed error case

                                    //status code equal 500
                                    //status is server error
                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                loginEventStatus = state.value.loginEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.loginEventStatus.serverError
                                                )
                                            )
                                        }//end update

                                    }//end server error case

                                }//end when

                            }//end success case

                            //if status is loading
                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        loginEventStatus = state.value.loginEventStatus.copy(
                                            success = false,
                                            loading = true,
                                            loadingType = 1
                                        )
                                    )
                                }//end update

                            }//end loading case

                            //if status is error
                            is Status.Error -> {

                                when (status.status) {

                                    400 -> {

                                        _state.update {
                                            it.copy(
                                                loginEventStatus = state.value.loginEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.loginEventStatus.internetError
                                                )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                loginEventStatus = state.value.loginEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.loginEventStatus.serverError
                                                )
                                            )
                                        }//end update

                                    }//end server error case

                                }//end when

                            }//end error case

                        }//end when

                    }//end collectLatest

                }//end try
                catch (ex: IOException) {

                    ex.message?.let { Log.d("ERROR", it) }
                    Log.d("TAG", "ERROR")

                    //failed connected with internet
                    _state.update {
                        it.copy(
                            loginEventStatus = state.value.loginEventStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.loginEventStatus.internetError
                            )
                        )
                    }//end update

                }//end catch

            }//end coroutine builder

        }//end if

        //if user data is empty
        else {

            _state.update {
                it.copy(
                    loginEventStatus = state.value.loginEventStatus.copy(
                        success = false,
                        loading = false,
                        dataNotFound = !state.value.loginEventStatus.dataNotFound
                    )
                )
            }//end update

        }//end else

    }//end onExecuteLoginWithEmail

    //function for execute login with social
    private fun onExecuteLoginWithSocial() {

        //create coroutine builder here for contain on suspend functions
        viewModelScope.launch(Dispatchers.IO) {

            //function for collect latest social token
            _state.value.socialAccessToken.collectLatest { accessToken ->

                Log.d("TAG", accessToken)
                Log.d("TAG", state.value.provider)

                try {

                    //if access token is not empty make login request
                    if (accessToken.isNotEmpty()) {

                        onFirstRunningStateChanged(false)

                        //make social login request here
                        //collect social login request status
                        loginWithSocialUseCase(
                            accessToken = accessToken,
                            provider = state.value.provider
                        ).collectLatest { status ->

                            //make status conditions here
                            when (status) {

                                //on request status is success
                                is Status.Success -> {

                                    //make success status code conditions here
                                    when (status.toData()) {

                                        200 -> {

                                            _state.update {
                                                it.copy(
                                                    loginEventStatus = state.value.loginEventStatus.copy(
                                                        success = true,
                                                        loading = false
                                                    )
                                                )
                                            }//end update

                                        }//end case 200

                                        404 -> {

                                            _state.update {
                                                it.copy(
                                                    loginEventStatus = state.value.loginEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        alreadyAuthorized = !state.value.loginEventStatus.alreadyAuthorized
                                                    )
                                                )
                                            }//end update

                                        }//end case 404

                                        500 -> {

                                            _state.update {
                                                it.copy(
                                                    loginEventStatus = state.value.loginEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.loginEventStatus.serverError
                                                    )
                                                )
                                            }//end update

                                        }//end case 500

                                    }//end when

                                }//end success case

                                //on request status is loading
                                is Status.Loading -> {

                                    _state.update {
                                        it.copy(
                                            loginEventStatus = state.value.loginEventStatus.copy(
                                                success = false,
                                                loading = true,
                                                loadingType = if (state.value.provider == "google") 2 else 3
                                            )
                                        )
                                    }//end update

                                }//end loading case

                                //on request status is error
                                is Status.Error -> {

                                    when (status.status) {

                                        400 -> {

                                            _state.update {
                                                it.copy(
                                                    loginEventStatus = state.value.loginEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        internetError = !state.value.loginEventStatus.internetError
                                                    )
                                                )
                                            }//end update

                                        }//end internet error case

                                        500 -> {

                                            _state.update {
                                                it.copy(
                                                    loginEventStatus = state.value.loginEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.loginEventStatus.serverError
                                                    )
                                                )
                                            }//end update

                                        }//end server error case

                                    }//end when

                                }//end error case

                            }//end when

                        }//end collectLatest

                    }//end if

                }//end try
                catch (ex: IOException) {

                    //failed connected with internet
                    _state.update {
                        it.copy(
                            loginEventStatus = state.value.loginEventStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.loginEventStatus.internetError
                            )
                        )
                    }//end update

                }//end catch

            }//end collectLatest

        }//end launch

    }//end onExecuteLoginWithSocial

    //function for handle google sign in with result
    fun handleGoogleSignInResult(result: ActivityResult) {

        //if result code is ok
        if (result.resultCode == Activity.RESULT_OK) {

            //get google user data from result
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            //get user account
            val account = task.getResult(ApiException::class.java)
            //get access token from account data
            val accessToken = account.idToken

            _state.update {
                it.copy(
                    provider = "google"
                )
            }
            _state.value.socialAccessToken.update {
                accessToken!!
            }

        }//end if

    }//end handleGoogleSignInResult

    private fun handleFacebookCallbackResult() {

        // تكوين عملية تسجيل الدخول باستخدام Facebook SDK
        state.value.facebookLoginManager?.registerCallback(
            state.value.facebookCallbackManager,
            object : FacebookCallback<LoginResult> {

                override fun onCancel() {

                }

                override fun onError(error: FacebookException) {

                }

                override fun onSuccess(result: LoginResult) {

                    //get facebook access token from result
                    _state.update {
                        it.copy(
                            provider = "facebook"
                        )
                    }//end update
                    _state.value.socialAccessToken.update {
                        result.accessToken.token
                    }//end update

                }//end onSuccess

            }//end FacebookCallback class
        )

    }//end handleFacebookCallbackResult


    //function for change first running state
    fun onFirstRunningStateChanged(newValue: Boolean) {

        _state.update {
            it.copy(
                firstRunningState = newValue
            )
        }//end update

    }//end onFirstRunningStateChange

}//end LoginViewModel