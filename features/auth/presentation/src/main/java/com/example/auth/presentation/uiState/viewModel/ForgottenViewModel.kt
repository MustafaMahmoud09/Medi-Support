package com.example.auth.presentation.uiState.viewModel

import com.example.auth.presentation.uiState.state.ForgottenUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ForgottenViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.example.auth.presentation.uiState.state.ForgottenUiState())

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

}//end ForgottenViewModel