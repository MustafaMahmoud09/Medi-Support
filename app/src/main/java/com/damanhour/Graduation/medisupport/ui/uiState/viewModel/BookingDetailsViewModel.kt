package com.damanhour.Graduation.medisupport.ui.uiState.viewModel

import androidx.lifecycle.SavedStateHandle
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.BookingDetailsArgs
import com.damanhour.Graduation.medisupport.ui.uiState.state.BookingDetailsUiState
import com.example.sharedui.uiState.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class BookingDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.damanhour.Graduation.medisupport.ui.uiState.state.BookingDetailsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking details arguments here
    private val bookingDetailsArgs: com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.BookingDetailsArgs =
        com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.BookingDetailsArgs(
            savedStateHandle
        )

    init {

        //change current booking details page here
        onCurrentBookingDetailsPageChanged(
            newPage = bookingDetailsArgs.page
        )

    }//end init

    //function for change current doctor page
    fun onCurrentBookingDetailsPageChanged(newPage: Int) {

        //check current page not equal new page
        //if not equal change current page
        if (newPage != _state.value.currentBookingDetailsPage) {

            //update current doctors page here
            _state.update {
                it.copy(
                    currentBookingDetailsPage = newPage
                )
            }//end update

        }//end if

    }//end onCurrentBookingDetailsPageChanged

}//end BookingDetailsViewModel