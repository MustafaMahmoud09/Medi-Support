package com.example.auth.presentation.uiState.viewModel

import com.example.auth.presentation.uiState.state.RegisterUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class RegisterViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.example.auth.presentation.uiState.state.RegisterUiState())

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

}//end RegisterViewModel