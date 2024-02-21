package com.example.booking.uiState.viewModel.doctors

import com.example.booking.uiState.state.doctors.TopDoctorsUiState
import com.example.sharedui.uiState.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TopDoctorsViewModel @Inject constructor(): BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(TopDoctorsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //function for change current doctor page
    fun onCurrentDoctorsPageChanged(newPage: Int) {

        //check current page not equal new page
        //if not equal change current page
        if (newPage != _state.value.currentDoctorsPage) {

            //update current doctors page here
            _state.update {
                it.copy(
                    currentDoctorsPage = newPage
                )
            }//end update

        }//end if

    }//end onCurrentDoctorPageChanged

}//end TopDoctorsViewModel