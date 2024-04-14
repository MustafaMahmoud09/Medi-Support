package com.example.auth.presentation.uiState.viewModel

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.domain.usecase.declarations.ILoginWithSocialUseCase
import com.example.auth.presentation.uiState.state.RegisterUiState
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
class RegisterViewModel @Inject constructor(
    private val createNewUserUseCase: ICreateNewUserUseCase,
    private val loginWithSocialUseCase: ILoginWithSocialUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(RegisterUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {

            delay(250)
            _state.update {
                it.copy(
                    firstRunningState = false
                )
            }//end update

        }//end launch

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

    fun onFirstNameChanged(newValue: String) {

        //update first name here
        _state.update {
            it.copy(
                firstNameKey = newValue
            )
        }//end update

    }//end onFirstNameChanged

    fun onLastNameChange(newValue: String) {

        //update last name here
        _state.update {
            it.copy(
                lastNameKey = newValue
            )
        }//end update

    }//end onLastNameChange

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


    //function for make register event
    fun onUserAccountCreated() {

        val firstName = state.value.firstNameKey.trim()
        val lastName = state.value.lastNameKey.trim()
        val password = state.value.passwordKey.trim()
        val email = state.value.emailKey.trim()

        //if user user data not empty
        if (
            firstName.length >= 2 &&
            lastName.length >= 2 &&
            password.length >= 8 &&
            email.isNotEmpty()
        ) {

            //create coroutine builder here
            viewModelScope.launch(Dispatchers.IO) {

                try {

                    //make register use case here
                    //collect register status code here
                    createNewUserUseCase(
                        name = firstName,
                        lastName = lastName,
                        email = email,
                        password = password,
                        confirmPassword = password
                    ).collectLatest { status ->

                        //if request status equal success
                        when (status) {

                            is Status.Success -> {

                                Log.d("STATUS-CODE", status.toData().toString())

                                //if status code equal 422 make email error equal true
                                when (status.toData()?.statusCode) {

                                    422 -> {

                                        _state.update {
                                            it.copy(
                                                registerEventState = state.value.registerEventState.copy(
                                                    dataNotValid = !state.value.registerEventState.dataNotValid,
                                                    serverError = false,
                                                    loading = false,
                                                )
                                            )
                                        }//end update

                                    }
                                    //end if

                                    //if status code equal 201 make register success equal true
                                    201 -> {

                                        _state.update {
                                            it.copy(
                                                registerEventState = state.value.registerEventState.copy(
                                                    success = true,
                                                    loading = false,
                                                )
                                            )
                                        }//end update

                                    }
                                    //end else if

                                    //if status code equal 500 make server error equal true
                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                registerEventState = state.value.registerEventState.copy(
                                                    serverError = !state.value.registerEventState.serverError,
                                                    success = false,
                                                    loading = false,
                                                )
                                            )
                                        }//end update

                                    }
                                }//end else if

                            }//end if

                            //if request status equal loading
                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        registerEventState = state.value.registerEventState.copy(
                                            success = false,
                                            loading = true,
                                            loadingType = 1
                                        )
                                    )
                                }//end update

                            }//end else if

                            //if request status equal error
                            is Status.Error -> {

                                when (status.status) {

                                    400 -> {

                                        _state.update {
                                            it.copy(
                                                registerEventState = state.value.registerEventState.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.registerEventState.internetError,
                                                )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                registerEventState = state.value.registerEventState.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.registerEventState.serverError,
                                                )
                                            )
                                        }//end update

                                    }//end server error case

                                }//end when

                            }

                        }//end else if

                    }//end collectLatest

                }//end try
                catch (ex: IOException) {

                    _state.update {
                        it.copy(
                            registerEventState = state.value.registerEventState.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.registerEventState.internetError
                            )
                        )
                    }//end update

                }//end catch

            }//end launch

        }//end if

        //if inputs empty
        else {

            _state.update {
                it.copy(
                    registerEventState = state.value.registerEventState.copy(
                        success = false,
                        loading = false,
                        dataNotFound = !state.value.registerEventState.dataNotFound
                    )
                )
            }//end update

        }//end else

    }//end onAccountUserCreated


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
                                                    registerEventState = state.value.registerEventState.copy(
                                                        success = true,
                                                        loading = false
                                                    )
                                                )
                                            }//end update

                                        }//end case 200

                                        404 -> {

                                            _state.update {
                                                it.copy(
                                                    registerEventState = state.value.registerEventState.copy(
                                                        success = false,
                                                        loading = false,
                                                        alreadyAuthorized = !state.value.registerEventState.alreadyAuthorized
                                                    )
                                                )
                                            }//end update

                                        }//end case 404

                                        500 -> {

                                            _state.update {
                                                it.copy(
                                                    registerEventState = state.value.registerEventState.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.registerEventState.serverError
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
                                            registerEventState = state.value.registerEventState.copy(
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
                                                    registerEventState = state.value.registerEventState.copy(
                                                        success = false,
                                                        loading = false,
                                                        internetError = !state.value.registerEventState.internetError
                                                    )
                                                )
                                            }//end update

                                        }//end internet error case

                                        500 -> {

                                            _state.update {
                                                it.copy(
                                                    registerEventState = state.value.registerEventState.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.registerEventState.serverError
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
                            registerEventState = state.value.registerEventState.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.registerEventState.internetError
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

}//end RegisterViewModel