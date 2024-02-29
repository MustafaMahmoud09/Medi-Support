package com.example.auth.presentation.uiState.viewModel

import com.example.auth.presentation.uiState.state.LoginUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.example.auth.presentation.uiState.state.LoginUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        //finish page load after 900 millis here
        getCoroutineScope().launch {

            delay(900)
            onPageLoadFinished()

        }//end Launch

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

}//end LoginViewModel