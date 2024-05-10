package com.example.setting.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.account.setting.domain.usecase.declarations.IDeleteAccountUseCase
import com.example.account.setting.domain.usecase.declarations.ILogoutUseCase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.setting.presentation.uiState.state.MoreUiState
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
class MoreViewModel @Inject constructor(
    private val deleteAccountUseCase: IDeleteAccountUseCase,
    private val logoutUseCase: ILogoutUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(MoreUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //function for update delete dialog state
    fun onDeleteDialogStateChanged(state: Boolean) {

        _state.update {
            it.copy(
                deleteDialogState = state
            )
        }//end update

    }//end onDeleteDialogStateChanged


    //function for update delete dialog state
    fun onLogoutDialogStateChanged(state: Boolean) {

        _state.update {
            it.copy(
                logoutDialogState = state
            )
        }//end update

    }//end onDeleteDialogStateChanged


    //function for delete user account
    fun onDeleteUserAccount(){

        //create coroutine builder for call suspend function in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make request for delete user account
                //collect status request
                deleteAccountUseCase().collectLatest {status->

                    when (status) {

                        is Status.Success -> {

                            when (status.toData()?.statusCode) {

                                200,401 -> {
                                    _state.update {
                                        it.copy(
                                            deleteUserAccountStatus = state.value
                                                .deleteUserAccountStatus.copy(
                                                    success = true,
                                                    loading = false
                                                )
                                        )
                                    }//end update

                                }//end success case

                                404, 500 -> {
                                    _state.update {
                                        it.copy(
                                            deleteUserAccountStatus = state.value
                                                .deleteUserAccountStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.deleteUserAccountStatus.serverError
                                                )
                                        )
                                    }//end update
                                }//end error server case

                            }//end when

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    deleteUserAccountStatus = state.value
                                        .deleteUserAccountStatus.copy(
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
                                            deleteUserAccountStatus = state.value
                                                .deleteUserAccountStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.deleteUserAccountStatus.internetError
                                                )
                                        )
                                    }//end update

                                }//end internet error case

                                500 -> {

                                    _state.update {
                                        it.copy(
                                            deleteUserAccountStatus = state.value
                                                .deleteUserAccountStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.deleteUserAccountStatus.serverError
                                                )
                                        )
                                    }//end update

                                }//end server error case

                            }//end when

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: IOException){

                //failed connected with internet
                _state.update {
                    it.copy(
                        deleteUserAccountStatus = state.value.deleteUserAccountStatus.copy(
                            success = false,
                            loading = false,
                            internetError = !state.value.deleteUserAccountStatus.internetError
                        )
                    )
                }//end update

            }//end catch

        }//end coroutine builder scope

    }//end onDeleteUserAccount


    //function for delete user account
    fun onLogout(){

        //create coroutine builder for call suspend function in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make request for delete user account
                //collect status request
                logoutUseCase().collectLatest {status->

                    when (status) {

                        is Status.Success -> {

                            when (status.toData()?.statusCode) {

                                200,401 -> {
                                    _state.update {
                                        it.copy(
                                            logoutStatus = state.value
                                                .logoutStatus.copy(
                                                    success = true,
                                                    loading = false
                                                )
                                        )
                                    }//end update

                                }//end success case

                                404, 500 -> {
                                    _state.update {
                                        it.copy(
                                            logoutStatus = state.value
                                                .logoutStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.logoutStatus.serverError
                                                )
                                        )
                                    }//end update
                                }//end error server case

                            }//end when

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    logoutStatus = state.value
                                        .logoutStatus.copy(
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
                                            logoutStatus = state.value
                                                .logoutStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.logoutStatus.internetError
                                                )
                                        )
                                    }//end update

                                }//end internet error case

                                500 -> {

                                    _state.update {
                                        it.copy(
                                            logoutStatus = state.value
                                                .logoutStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.logoutStatus.serverError
                                                )
                                        )
                                    }//end update

                                }//end server error case

                            }//end when

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: IOException){

                //failed connected with internet
                _state.update {
                    it.copy(
                        logoutStatus = state.value.logoutStatus.copy(
                            success = false,
                            loading = false,
                            internetError = !state.value.logoutStatus.internetError
                        )
                    )
                }//end update

            }//end catch

        }//end coroutine builder scope

    }//end onDeleteUserAccount

}//end MoreViewModel