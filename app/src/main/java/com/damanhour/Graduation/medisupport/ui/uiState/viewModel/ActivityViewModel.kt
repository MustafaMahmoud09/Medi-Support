package com.damanhour.Graduation.medisupport.ui.uiState.viewModel

import com.damanhour.Graduation.medisupport.ui.uiState.state.ActivityUiState
import com.example.sharedui.uiState.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ActivityUiState())

    //for observe by screen
    val state = _state.asStateFlow()

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

}//end ActivityViewModel