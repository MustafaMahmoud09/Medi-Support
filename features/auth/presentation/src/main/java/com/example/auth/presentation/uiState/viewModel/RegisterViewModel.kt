package com.example.auth.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.presentation.uiState.state.RegisterUiState
import com.example.libraries.core.remote.data.response.status.Status
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createNewUserUseCase: ICreateNewUserUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(RegisterUiState())

    //for observe by screen
    val state = _state.asStateFlow()


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
                        if (status is Status.Success) {

                            Log.d("STATUS-CODE", status.toData().toString())

                            //if status code equal 422 make email error equal true
                            if (status.toData() == 422) {

                                _state.update {
                                    it.copy(
                                        registerEventState = state.value.registerEventState.copy(
                                            emailNotValid = true,
                                            success = false,
                                            serverError = false,
                                            loading = false,
                                            internetError = false,
                                            inputsError = false
                                        )
                                    )
                                }//end update

                            }//end if

                            //if status code equal 201 make register success equal true
                            else if (status.toData() == 201) {

                                _state.update {
                                    it.copy(
                                        registerEventState = state.value.registerEventState.copy(
                                            success = true,
                                            emailNotValid = false,
                                            serverError = false,
                                            loading = false,
                                            internetError = false,
                                            inputsError = false
                                        )
                                    )
                                }//end update

                            }//end else if

                            //if status code equal 500 make server error equal true
                            else if (status.toData() == 500) {

                                _state.update {
                                    it.copy(
                                        registerEventState = state.value.registerEventState.copy(
                                            serverError = true,
                                            emailNotValid = false,
                                            success = false,
                                            loading = false,
                                            internetError = false,
                                            inputsError = false
                                        )
                                    )
                                }//end update

                            }//end else if

                        }//end if

                        //if request status equal loading
                        else if (status is Status.Loading) {

                            _state.update {
                                it.copy(
                                    registerEventState = state.value.registerEventState.copy(
                                        serverError = false,
                                        emailNotValid = false,
                                        success = false,
                                        loading = true,
                                        internetError = false,
                                        inputsError = false
                                    )
                                )
                            }//end update

                        }//end else if

                        //if request status equal error
                        else if (status is Status.Error) {

                            _state.update {
                                it.copy(
                                    registerEventState = state.value.registerEventState.copy(
                                        serverError = false,
                                        emailNotValid = false,
                                        success = false,
                                        loading = false,
                                        internetError = true,
                                        inputsError = false
                                    )
                                )
                            }//end update

                        }//end else if

                    }//end collectLatest

                }//end try
                catch (ex: Exception) {

                    _state.update {
                        it.copy(
                            registerEventState = state.value.registerEventState.copy(
                                serverError = false,
                                emailNotValid = false,
                                success = false,
                                loading = false,
                                internetError = true,
                                inputsError = false
                            )
                        )
                    }//end update

                }//end catch

            }//end launch

        }//end if

        //if inputs empty
        else{

            _state.update {
                it.copy(
                    registerEventState = state.value.registerEventState.copy(
                        serverError = false,
                        emailNotValid = false,
                        success = false,
                        loading = false,
                        internetError = false,
                        inputsError = true
                    )
                )
            }//end update

        }//end else

    }//end onAccountUserCreated


}//end RegisterViewModel