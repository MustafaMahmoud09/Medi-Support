package com.example.booking.uiState.viewModel.details

import androidx.lifecycle.SavedStateHandle
import com.example.booking.uiElement.screens.details.BookingDetailsArgs
import com.example.booking.uiState.state.details.BookingDetailsUiState
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
    private val _state = MutableStateFlow(BookingDetailsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    private val bookingDetailsArgs: BookingDetailsArgs = BookingDetailsArgs(savedStateHandle)

    init {

        onBookingDetailsPageUpdated()

    }//end init

    private fun onBookingDetailsPageUpdated() {

        //update booking details page here
        _state.update {
            it.copy(
                bookingDetailsPage = bookingDetailsArgs.page
            )
        }//end update

    }//end onBookingDetailsPageUpdated

}//end BookingDetailsViewModel