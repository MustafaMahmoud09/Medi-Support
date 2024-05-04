package com.example.onlinebooking.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.usecase.declarations.IGetTopOnlineDoctorsUseCase
import com.example.onlinebooking.presentation.uiState.state.TopOnlineDoctorsUiState
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
class TopOnlineDoctorsViewModel @Inject constructor(
    private val getTopOnlineDoctorsUseCase: IGetTopOnlineDoctorsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(TopOnlineDoctorsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetTopOnlineDoctors()
    }//end init

    //function for make request on use case for get top offline doctors
    private fun onGetTopOnlineDoctors() {

        //create coroutine builder for call suspend functions in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make request on use case here
                //collect top offline doctors status here
                getTopOnlineDoctorsUseCase().collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            if (status.toData()?.statusCode == 200) {

                                //update top offline doctors status to success
                                _state.update {
                                    it.copy(
                                        getTopOnlineDoctorsStatus = state.value
                                            .getTopOnlineDoctorsStatus.copy(
                                                loading = false,
                                                data = status.toData()?.body ?: emptyList()
                                            )
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
                                    getTopOnlineDoctorsStatus = state.value
                                        .getTopOnlineDoctorsStatus.copy(
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

}//end TopOfflineDoctorsViewModel