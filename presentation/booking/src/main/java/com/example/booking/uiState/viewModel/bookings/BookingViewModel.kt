package com.example.booking.uiState.viewModel.bookings

import androidx.lifecycle.SavedStateHandle
import com.example.booking.uiState.state.bookings.BookingUiState
import com.example.sharedui.uiState.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class BookingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(BookingUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking type
    private val bookingType: Boolean = checkNotNull(savedStateHandle["booking_type"])

    init {
        onBookingTypeUpdated()
    }//end init

    private fun onBookingTypeUpdated() {

        //update booking type here
        _state.update {
            it.copy(
                bookingType = bookingType
            )
        }

    }//end onBookingTypeUpdated

}//end BookingViewModel