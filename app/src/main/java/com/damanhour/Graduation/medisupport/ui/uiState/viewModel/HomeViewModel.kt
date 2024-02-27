package com.damanhour.Graduation.medisupport.ui.uiState.viewModel

import com.damanhour.Graduation.medisupport.ui.uiState.state.HomeUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(HomeUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    fun onScrollToTotalDoctorsPage() {

        //scroll to total doctors page here
        _state.update {
            it.copy(
                prevPage = state.value.currentPage,
                currentPage = 2,
                searchKey = ""
            )
        }//end update

    }//end onScrollToSearchPage

    fun onBack() {

        //update state here
        _state.update {
            it.copy(
                currentPage = _state.value.prevPage,
                prevPage = 0,
                focusOnSearch = false,
                searchKey = ""
            )
        }

    }//end on Back

    fun onFocusOnSearchField() {

        //if not exist in search screen focus on search field
        if (state.value.currentPage != 1) {

            //update focus here
            _state.update {
                it.copy(
                    prevPage = state.value.currentPage,
                    currentPage = 1,
                    focusOnSearch = true
                )
            }//end update

        }//end if

    }//end onFocusOnSearchField


    fun onSearchKeyChanged(newValue: String) {

        //update search key by new value here
        _state.update {
            it.copy(
                searchKey = newValue
            )
        }

    }//end onSearchKeyChanged

}//end HomeViewModel