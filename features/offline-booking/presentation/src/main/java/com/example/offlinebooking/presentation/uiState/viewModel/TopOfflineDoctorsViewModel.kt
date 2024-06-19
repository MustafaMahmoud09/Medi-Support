package com.example.offlinebooking.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.usecase.declarations.IGetTopOfflineDoctorsUseCase
import com.example.offlinebooking.presentation.uiState.state.TopOfflineDoctorsUiState
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
class TopOfflineDoctorsViewModel @Inject constructor(
    private val getTopOfflineDoctorsUseCase: IGetTopOfflineDoctorsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(TopOfflineDoctorsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetTopOfflineDoctors()
    }//end init

    //function for make request on use case for get top offline doctors
    private fun onGetTopOfflineDoctors() {

        //create coroutine builder for call suspend functions in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make request on use case here
                //collect top offline doctors status here
                getTopOfflineDoctorsUseCase().collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            if (status.toData()?.statusCode == 200) {

                                //update top offline doctors status to success
                                _state.update {
                                    it.copy(
                                        getTopOfflineDoctorsStatus = state.value
                                            .getTopOfflineDoctorsStatus.copy(
                                                loading = false,
                                                data = status.toData()?.body ?: emptyList()
                                            ),
                                        numberOfSuccessRequest = 1
                                    )
                                }//end update

                            }//end if

                        }//end success case

                        is Status.Error -> {

                        }//end error case

                        is Status.Loading -> {

                            //update top offline doctors status to loading
                            _state.update {
                                it.copy(
                                    getTopOfflineDoctorsStatus = state.value
                                        .getTopOfflineDoctorsStatus.copy(
                                            loading = true
                                        )
                                )
                            }//end update

                        }//end error case

                    }//end when

                }//end collect latest

            }//end try
            catch (ex: Exception) {
                ex.message?.let { Log.d("ERROR", it) }
            }//end catch

        }//end coroutine builder scope

    }//end onGetTopOfflineDoctors


    //function
    fun onRefreshTopOnlineDoctors() {

        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        onGetTopOfflineDoctors()

        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshTopOnlineDoctors

}//end TopOfflineDoctorsViewModel