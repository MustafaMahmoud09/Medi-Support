package com.example.auth.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.auth.domain.usecase.declarations.ICheckUserAuthUseCase
import com.example.auth.domain.usecase.declarations.IUpdateUsersAuthCountUseCase
import com.example.auth.presentation.uiState.state.SplashUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkUserAuthUseCase: ICheckUserAuthUseCase,
    private val updateUsersAuthCountUseCase: IUpdateUsersAuthCountUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(SplashUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onUserRegisterStateOrganized()
    }//end init


    //function for define app contain on user is auth or no
    private fun onUserRegisterStateOrganized() {

        //create coroutine builder contain on use cases
        viewModelScope.launch(Dispatchers.IO) {

            //update count register for users here
            updateUsersAuthCountUseCase()

            //check user is auth or no
            val userIsAuth = checkUserAuthUseCase()
            if (userIsAuth) {

                delay(250)
                _state.update {
                    it.copy(
                        userIsAuth = true
                    )
                }//end update
            }//end if
            else {

                delay(1000)
                _state.update {
                    it.copy(
                        userIsAuth = false
                    )
                }//end update

            }//end else
        }//end coroutine builder

    }//end userAuthStateDefined

}//end WelcomeViewModel