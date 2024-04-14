package com.example.auth.presentation.uiState.viewModel

import com.example.auth.domain.usecase.declarations.IResetPasswordUseCase
import com.example.auth.domain.usecase.declarations.ISendUserEmailUseCase
import com.example.auth.domain.usecase.declarations.IVerifyCodeUseCase
import com.example.auth.presentation.uiState.state.EventStatus
import com.example.auth.presentation.uiState.state.ForgottenUiState
import com.example.libraries.core.remote.data.response.status.Status
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ForgottenViewModel @Inject constructor(
    private val resetPasswordUseCase: IResetPasswordUseCase,
    private val sendUserEmailUseCase: ISendUserEmailUseCase,
    private val verifyCodeUseCase: IVerifyCodeUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ForgottenUiState())

    //for observe by screen
    val state = _state.asStateFlow()


    fun onEmailChanged(newValue: String) {

        //update email here
        _state.update {
            it.copy(
                emailKey = newValue
            )
        }

    }//end onEmailChanged


    fun onFirstCodeChanged(newValue: String) {

        //check first key is empty or no or value is empty or no
        //for store single value in first code
        if (newValue.isEmpty() || _state.value.firstCodeKey.isEmpty()) {

            //update first code here
            _state.update {
                it.copy(
                    firstCodeKey = newValue
                )
            }

        }//end if

    }//end onFirstCodeChanged

    fun onSecondCodeChanged(newValue: String) {

        //check second key is empty or no or value is empty or no
        //for store single value in second code
        if (newValue.isEmpty() || _state.value.secondCodeKey.isEmpty()) {

            //update second code here
            _state.update {
                it.copy(
                    secondCodeKey = newValue
                )
            }

        }//end if

    }//end onSecondCodeChanged

    fun onThirdCodeChanged(newValue: String) {

        //check third key is empty or no or value is empty or no
        //for store single value in third code
        if (newValue.isEmpty() || _state.value.thirdCodeKey.isEmpty()) {

            //update third code here
            _state.update {
                it.copy(
                    thirdCodeKey = newValue
                )
            }

        }//end if

    }//end onThirdCodeChanged

    fun onFourthCodeChanged(newValue: String) {

        //check fourth key is empty or no or value is empty or no
        //for store single value in fourth code
        if (newValue.isEmpty() || _state.value.fourthCodeKey.isEmpty()) {

            //update fourth code here
            _state.update {
                it.copy(
                    fourthCodeKey = newValue
                )
            }

        }//end if

    }//end onFourthCodeChanged


    fun onNewPasswordChanged(newValue: String) {

        //update new password here
        _state.update {
            it.copy(
                newPasswordKey = newValue
            )
        }

    }//end onNewPasswordChanged

    fun onConfirmPasswordChanged(newValue: String) {

        //update confirm password here
        _state.update {
            it.copy(
                confirmNewPasswordKey = newValue
            )
        }

    }//end onConfirmPasswordChanged


    //function for send code on user email
    fun onUserEmailSending() {

        val email = _state.value.emailKey.trim()

        if (email.isNotEmpty()) {

            //create coroutine builder contain on suspend functions here
            getCoroutineScope().launch(Dispatchers.IO) {

                try {

                    //use send email use case here
                    //observe send email result here
                    sendUserEmailUseCase(
                        email = email
                    ).collectLatest { status ->

                        //if send email status equal success
                        when (status) {

                            is Status.Success -> {

                                //if send email status equal 200
                                when (status.toData()?.statusCode) {

                                    200 -> {

                                        //update send email event status to success here
                                        _state.update {
                                            it.copy(
                                                sendEmailEventStatus = state.value.sendEmailEventStatus.copy(
                                                    success = true,
                                                    loading = false,
                                                )
                                            )
                                        }//end update

                                    }
                                    //end if

                                    //if send email equal 404
                                    404, 400 -> {

                                        //update send email event status to account not found
                                        _state.update {
                                            it.copy(
                                                sendEmailEventStatus = state.value.sendEmailEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    dataNotValid = !state.value.sendEmailEventStatus.dataNotValid,
                                                )
                                            )
                                        }//end update

                                    }
                                    //end else if

                                    //if send email equal 404
                                    500 -> {

                                        //update send email event status to server error
                                        _state.update {
                                            it.copy(
                                                sendEmailEventStatus = state.value.sendEmailEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.sendEmailEventStatus.serverError,
                                                )
                                            )
                                        }//end update

                                    }
                                }//end else if

                            }//end if

                            //if send email status equal loading
                            is Status.Loading -> {

                                //update send email event status to loading
                                _state.update {
                                    it.copy(
                                        sendEmailEventStatus = state.value.sendEmailEventStatus.copy(
                                            success = false,
                                            loading = true,
                                        )
                                    )
                                }//end update

                            }//end else if

                            //if send email status equal error
                            is Status.Error -> {

                                when (status.status) {

                                    400 -> {

                                        //update send email event status to account not found
                                        _state.update {
                                            it.copy(
                                                sendEmailEventStatus = state.value.sendEmailEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.sendEmailEventStatus.internetError,
                                                )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        //update send email event status to account not found
                                        _state.update {
                                            it.copy(
                                                sendEmailEventStatus = state.value.sendEmailEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.sendEmailEventStatus.serverError,
                                                )
                                            )
                                        }//end update

                                    }//end server error case

                                }//end when

                            }

                        }//end else if

                    }//end sendUserEmailUseCase

                }//end coroutine builder
                catch (ex: IOException) {

                    //update send email event status to account not found
                    _state.update {
                        it.copy(
                            sendEmailEventStatus = state.value.sendEmailEventStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.sendEmailEventStatus.internetError,
                            )
                        )
                    }//end update

                }//end catch

            }//end try

        }//end if

        //if data is empty
        else {

            //update send email event status to account not found
            _state.update {
                it.copy(
                    sendEmailEventStatus = state.value.sendEmailEventStatus.copy(
                        success = false,
                        loading = false,
                        dataNotFound = !state.value.sendEmailEventStatus.dataNotFound
                    )
                )
            }//end update

        }//end else

    }//end onUserEmailSending

    //function for send verify code
    fun onVerifyCodeSending() {

        //get verify code here
        val code = "${
            state.value.firstCodeKey
        }${
            state.value.secondCodeKey
        }${
            state.value.thirdCodeKey
        }${
            state.value.fourthCodeKey
        }".trim()

        val email = state.value.emailKey.trim()

        //check verify code size equal 4 or no
        if (code.length == 4 && email.isNotEmpty()) {

            //create coroutine builder contain verify code use case
            getCoroutineScope().launch(Dispatchers.IO) {

                try {

                    //observe verify code result here
                    verifyCodeUseCase(
                        email = email,
                        code = code
                    ).collectLatest { status ->

                        //if verify code status is success
                        //execute body code
                        when (status) {

                            is Status.Success -> {

                                //if verify code status equal 200
                                //update event status to success
                                when (status.toData()?.statusCode) {
                                    200 -> {

                                        _state.update {
                                            it.copy(
                                                verifyCodeEventStatus = state.value.verifyCodeEventStatus.copy(
                                                    success = true,
                                                    loading = false,
                                                )
                                            )
                                        }//end update

                                    }
                                    //end if

                                    //if verify code status equal 422
                                    //update event status to code not valid
                                    422 -> {

                                        _state.update {
                                            it.copy(
                                                verifyCodeEventStatus = state.value.verifyCodeEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    dataNotValid = !state.value.verifyCodeEventStatus.dataNotValid,
                                                )
                                            )
                                        }//end update

                                    }
                                    //end if

                                    //if verify code status equal 500
                                    //update event status to server error
                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                verifyCodeEventStatus = state.value.verifyCodeEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = state.value.verifyCodeEventStatus.serverError,
                                                )
                                            )
                                        }//end update

                                    }
                                }//end if

                            }//end if

                            //if verify code status is loading
                            //update event status to loading
                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        verifyCodeEventStatus = state.value.verifyCodeEventStatus.copy(
                                            success = false,
                                            loading = true,
                                        )
                                    )
                                }//end update

                            }//end else if

                            //if verify code status is error
                            //update event status to error
                            is Status.Error -> {

                                when (status.status) {

                                    400 -> {

                                        _state.update {
                                            it.copy(
                                                verifyCodeEventStatus = state.value.verifyCodeEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.verifyCodeEventStatus.internetError,
                                                )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                verifyCodeEventStatus = state.value.verifyCodeEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.verifyCodeEventStatus.serverError,
                                                )
                                            )
                                        }//end update

                                    }//end server error case

                                }//end when

                            }
                        }//end else if

                    }//end collect latest

                }//end try
                catch (ex: IOException) {

                    _state.update {
                        it.copy(
                            verifyCodeEventStatus = state.value.verifyCodeEventStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.verifyCodeEventStatus.internetError,
                            )
                        )
                    }//end update

                }//end catch

            }//end coroutine builder

        }//end if

        //if data is empty
        else {

            _state.update {
                it.copy(
                    verifyCodeEventStatus = state.value.verifyCodeEventStatus.copy(
                        success = false,
                        loading = false,
                        dataNotFound = !state.value.verifyCodeEventStatus.dataNotFound
                    )
                )
            }//end update

        }//end else

    }//end onVerifyCodeSending

    //function for reset user password
    fun onResetUserPassword() {

        //get reset password inputs
        val password = state.value.newPasswordKey.trim()
        val passwordConfirmation = state.value.confirmNewPasswordKey.trim()
        val email = state.value.emailKey.trim()

        //if data is not empty
        if (
            password.length >= 8 &&
            passwordConfirmation.length >= 9 &&
            passwordConfirmation == password &&
            email.isNotEmpty()
        ) {

            //create coroutine builder here
            getCoroutineScope().launch(Dispatchers.IO) {

                try {

                    //observe reset password result here
                    resetPasswordUseCase(
                        email = email,
                        password = password,
                        passwordConfirmation = passwordConfirmation
                    ).collectLatest { status ->

                        //if verify code status is success
                        //execute body code
                        when (status) {

                            is Status.Success -> {

                                //if verify code status equal 200
                                //update event status to success
                                when (status.toData()?.statusCode) {
                                    200 -> {

                                        _state.update {
                                            it.copy(
                                                newPasswordKey = "",
                                                confirmNewPasswordKey = "",
                                                resetPasswordEventStatus = state.value.resetPasswordEventStatus.copy(
                                                    success = true,
                                                    loading = false,
                                                ),
                                            )
                                        }//end update

                                    }
                                    //end if

                                    //if verify code status equal 422
                                    //update event status to code not valid
                                    422 -> {

                                        _state.update {
                                            it.copy(
                                                resetPasswordEventStatus = state.value.resetPasswordEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    dataNotValid = !state.value.resetPasswordEventStatus.dataNotValid,
                                                )
                                            )
                                        }//end update

                                    }
                                    //end if

                                    //if verify code status equal 500
                                    //update event status to server error
                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                resetPasswordEventStatus = state.value.resetPasswordEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.resetPasswordEventStatus.serverError,
                                                )
                                            )
                                        }//end update

                                    }
                                }//end if

                            }//end if

                            //if verify code status is loading
                            //update event status to loading
                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        resetPasswordEventStatus = state.value.resetPasswordEventStatus.copy(
                                            success = false,
                                            loading = true,
                                        )
                                    )
                                }//end update

                            }//end else if

                            //if verify code status is error
                            //update event status to error
                            is Status.Error -> {

                                when (status.status) {

                                    400 -> {

                                        _state.update {
                                            it.copy(
                                                resetPasswordEventStatus = state.value.resetPasswordEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.resetPasswordEventStatus.internetError
                                                )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                resetPasswordEventStatus = state.value.resetPasswordEventStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.resetPasswordEventStatus.serverError
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
                            resetPasswordEventStatus = state.value.resetPasswordEventStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.resetPasswordEventStatus.internetError,
                            )
                        )
                    }//end update

                }//end catch

            }//end coroutine builder

        }//end if

        //if data is empty
        else {

            _state.update {
                it.copy(
                    resetPasswordEventStatus = state.value.resetPasswordEventStatus.copy(
                        success = false,
                        loading = false,
                        dataNotFound = !state.value.resetPasswordEventStatus.dataNotFound
                    )
                )
            }//end update

        }//end else

    }//end onResetUserPassword


    //function for return inputs for default value
    private fun onClearedForgottenFeatureState() {

        //clear all inputs here
        _state.update {
            it.copy(
                emailKey = "",
                firstCodeKey = "",
                secondCodeKey = "",
                thirdCodeKey = "",
                fourthCodeKey = "",
                newPasswordKey = "",
                confirmNewPasswordKey = "",
                sendEmailEventStatus = EventStatus(),
                verifyCodeEventStatus = EventStatus(),
                resetPasswordEventStatus = EventStatus()
            )
        }//end update

    }//end onClearedForgottenFeatureState

    override fun onCleared() {
        super.onCleared()

        if (state.value.resetPasswordEventStatus.success) {
            onClearedForgottenFeatureState()
        }//end if

    }//end onCleared

}//end ForgottenViewModel