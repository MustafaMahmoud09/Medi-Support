package com.damanhour.Graduation.medisupport.ui.uiState.viewModel

import androidx.lifecycle.SavedStateHandle
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.BookingDetailsArgs
import com.damanhour.Graduation.medisupport.ui.uiState.state.HistoryUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(HistoryUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get history arguments here
    private val historyArgs: BookingDetailsArgs = BookingDetailsArgs(savedStateHandle)

    init {

        //change current booking details page here
        onCurrentHealthCarePageChanged(
            newPage = historyArgs.page
        )

    }//end init

    //function for change current doctor page
    fun onCurrentHealthCarePageChanged(newPage: Int) {

        //check current page not equal new page
        //if not equal change current page
        if (newPage != _state.value.currentPage) {

            //update current doctors page here
            _state.update {
                it.copy(
                    currentPage = newPage
                )
            }//end update

        }//end if

    }//end onCurrentHealthCareChanged

    //function for change drop menus state page
    fun onDropMenusExpandedChanged() {

        //update menus expanded here
        _state.update {
            it.copy(
                menusExpanded = !state.value.menusExpanded
            )
        }//end update

    }//end onDropMenusExpandedChanged

}//end HistoryViewModel