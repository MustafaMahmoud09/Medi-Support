package com.example.booking.uiState.viewModel

import com.example.booking.uiState.state.TopDoctorsUiState
import com.example.sharedui.uiState.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TopDoctorsViewModel : BaseViewModel(){

    //for manage screen state from view model
    private val _state = MutableStateFlow(TopDoctorsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

}//end TopDoctorsViewModel