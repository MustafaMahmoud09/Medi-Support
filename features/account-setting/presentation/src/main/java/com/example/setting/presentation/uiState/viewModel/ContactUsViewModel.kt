package com.example.setting.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.account.setting.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.account.setting.domain.usecase.declarations.ISendContactUsMessageUseCase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.setting.presentation.uiState.state.ContactUsUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
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
class ContactUsViewModel @Inject constructor(
    private val sendContactUsMessageUseCase: ISendContactUsMessageUseCase,
    private val logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ContactUsUiState())

    //for observe by screen
    val state = _state.asStateFlow()


    //function for update password
    fun onUserNameChanged(newValue: String) {

        //update password state here
        _state.update {
            it.copy(
                userNameValue = newValue
            )
        }//end update

    }//end onPasswordChanged


    //function for update password confirmation
    fun onEmailChanged(newValue: String) {

        //update password confirmation here
        _state.update {
            it.copy(
                emailValue = newValue
            )
        }//end update

    }//end onPasswordConfirmationChanged


    //function for update password confirmation
    fun onMessageChanged(newValue: String) {

        //update password confirmation here
        _state.update {
            it.copy(
                messageValue = newValue
            )
        }//end update

    }//end onPasswordConfirmationChanged


    //function for send contact us message
    fun onSendContactUsMessage() {

        //create coroutine builder for call suspend function in it
        viewModelScope.launch(Dispatchers.IO) {

            if (
                state.value.emailValue.trim().length in 1..255 &&
                state.value.userNameValue.trim().length in 1..255 &&
                state.value.messageValue.trim().isNotEmpty()
            ) {

                try {

                    //make request for send contact us message
                    //collect status request
                    sendContactUsMessageUseCase(
                        userName = state.value.userNameValue.trim(),
                        email = state.value.emailValue.trim(),
                        message = state.value.messageValue.trim()
                    ).collectLatest { status ->

                        when (status) {

                            is Status.Success -> {

                                when (status.toData()?.statusCode) {

                                    200 -> {
                                        _state.update {
                                            it.copy(
                                                sendContactUsEventStatus = state.value
                                                    .sendContactUsEventStatus.copy(
                                                        success = true,
                                                        loading = false
                                                    ),
                                                emailValue = "",
                                                userNameValue = "",
                                                messageValue = ""
                                            )
                                        }//end update

                                        delay(1000)

                                        _state.update {
                                            it.copy(
                                                sendContactUsEventStatus = state.value
                                                    .sendContactUsEventStatus.copy(
                                                        success = false,
                                                        loading = false
                                                    )
                                            )
                                        }//end update

                                    }//end success case

                                    404, 500 -> {
                                        _state.update {
                                            it.copy(
                                                sendContactUsEventStatus = state.value
                                                    .sendContactUsEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.sendContactUsEventStatus.serverError
                                                    )
                                            )
                                        }//end update
                                    }//end error server case

                                    401 -> {

                                        logoutFromLocalDatabaseUseCase()

                                        _state.update {
                                            it.copy(
                                                sendContactUsEventStatus = state.value
                                                    .sendContactUsEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        unAuthorized = true
                                                    )
                                            )
                                        }//end update

                                    }//end unAuthorized case

                                    422 -> {
                                        _state.update {
                                            it.copy(
                                                sendContactUsEventStatus = state.value
                                                    .sendContactUsEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        emailError = !state.value.sendContactUsEventStatus.emailError
                                                    )
                                            )
                                        }//end update
                                    }//end error server case

                                }//end when

                            }//end success case

                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        sendContactUsEventStatus = state.value
                                            .sendContactUsEventStatus.copy(
                                                success = false,
                                                loading = true
                                            )
                                    )
                                }//end update

                            }//end loading case

                            is Status.Error -> {

                                when (status.status) {

                                    400 -> {

                                        _state.update {
                                            it.copy(
                                                sendContactUsEventStatus = state.value
                                                    .sendContactUsEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        internetError = !state.value.sendContactUsEventStatus.internetError
                                                    )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                sendContactUsEventStatus = state.value
                                                    .sendContactUsEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.sendContactUsEventStatus.serverError
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

                    //failed connected with internet
                    _state.update {
                        it.copy(
                            sendContactUsEventStatus = state.value.sendContactUsEventStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.sendContactUsEventStatus.internetError
                            )
                        )
                    }//end update

                }//end catch

            }//end if
            else {

                _state.update {
                    it.copy(
                        sendContactUsEventStatus = state.value.sendContactUsEventStatus.copy(
                            dataError = !state.value.sendContactUsEventStatus.dataError
                        )
                    )
                }//end update

            }//end else

        }//end coroutine builder scope

    }//end onSendContactUsMessage

}//end ContactUsViewModel