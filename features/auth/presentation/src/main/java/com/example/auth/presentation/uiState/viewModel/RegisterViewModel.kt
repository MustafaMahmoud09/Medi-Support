package com.example.auth.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.presentation.uiState.state.RegisterUiState
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
            firstName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            password.isNotEmpty() &&
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
                    ).collectLatest { statusCode ->

                        //if status code equal 422 make email error equal true
                        if (statusCode == 422) {

                            _state.update {
                                it.copy(
                                    registerEventState = state.value.registerEventState.copy(
                                        inputsError = true,
                                        registerSuccess = false,
                                        serverError = false
                                    )
                                )
                            }//end update

                        }//end if

                        //if status code equal 201 make register success equal true
                        else if (statusCode == 201) {

                            _state.update {
                                it.copy(
                                    registerEventState = state.value.registerEventState.copy(
                                        registerSuccess = true,
                                        inputsError = false,
                                        serverError = false
                                    )
                                )
                            }//end update

                        }//end else if

                        //if status code equal 500 make server error equal true
                        else if (statusCode == 500) {

                            _state.update {
                                it.copy(
                                    registerEventState = state.value.registerEventState.copy(
                                        serverError = true,
                                        inputsError = false,
                                        registerSuccess = false
                                    )
                                )
                            }//end update

                        }//end else if

                        //if status code equal 501 make internet error equal true
                        else if(statusCode == 501){



                        }//end else if


                    }//end collectLatest


                }//end try
                catch (ex: Exception) {

                    ex.message?.let { Log.d("ERROR", it) }

                }//end catch

            }//end launch

        }//end if

    }//end onAccountUserCreated

}//end RegisterViewModel