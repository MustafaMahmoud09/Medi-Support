package com.example.payment.presentation.uiState.viewModel

import androidx.lifecycle.SavedStateHandle
import com.example.payment.presentation.uiState.state.PaymentUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class PaymentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.example.payment.presentation.uiState.state.PaymentUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking arguments here
//    private val doctorId: OnlineBookingArgs = OnlineBookingArgs(savedStateHandle)

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