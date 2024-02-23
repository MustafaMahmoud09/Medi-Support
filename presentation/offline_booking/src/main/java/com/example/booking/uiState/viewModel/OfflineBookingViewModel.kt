package com.example.booking.uiState.viewModel

import androidx.lifecycle.SavedStateHandle
import com.example.sharedui.uiState.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OfflineBookingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(0)

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking type
    private val doctorId: Int = checkNotNull(savedStateHandle["doctor_id"])

    init {

        _state.update { doctorId }
    }

}//end OfflineBookingViewModel