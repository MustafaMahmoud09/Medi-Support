package com.example.onlinebooking.presentation.uiState.viewModel

import androidx.lifecycle.SavedStateHandle
import com.example.onlinebooking.presentation.uiElement.screens.booking.OnlineBookingArgs
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.example.onlinebooking.presentation.uiState.state.OnlineBookingUiState

@HiltViewModel
internal class OnlineBookingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.example.onlinebooking.presentation.uiState.state.OnlineBookingUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking arguments here
    private val doctorId: com.example.onlinebooking.presentation.uiElement.screens.booking.OnlineBookingArgs =
        com.example.onlinebooking.presentation.uiElement.screens.booking.OnlineBookingArgs(
            savedStateHandle
        )

    //function for make booking successfully dialog is visible
    fun onShowBookingSuccessfullyDialog(){

        //update booking successfully visibility state here
        _state.update {
            it.copy(
                bookingSuccessfullyDialogIsVisible = true
            )
        }//update

    }//end onShowBookingSuccessfullyDialog

}//end OfflineBookingViewModel