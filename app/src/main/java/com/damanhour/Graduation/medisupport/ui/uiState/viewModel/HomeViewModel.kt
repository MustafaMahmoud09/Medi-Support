package com.damanhour.Graduation.medisupport.ui.uiState.viewModel

import android.util.Log
import com.damanhour.Graduation.medisupport.ui.uiState.state.HomeUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.LinkedList

class HomeViewModel : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(HomeUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //function for scroll to page in pager
    fun onNewPagePushed(
        newPage: Int,
        focusOnSearch: Boolean = false
    ) {

        if (state.value.pagerStack.last() != newPage && newPage <= 3) {

            //get pager stack
            val pagerStack = state.value.pagerStack

            //make new pager stack with new page here
            val newPagerStack = LinkedList(pagerStack)
            newPagerStack.remove(newPage)
            newPagerStack.add(newPage)

            //update ui stack by new pager stack
            _state.update {
                it.copy(
                    pagerStack = newPagerStack,
                    searchKey = "",
                    focusOnSearch = focusOnSearch
                )
            }//end update

        }//end if

    }//end onScrollToPagerScreen


    //function for pop last screen from back stack
    fun onLastScreenPopped() {

        //if pager stack size greater than 1 execute scope
        if (state.value.pagerStack.size > 1) {

            //get pager stack here
            val pagerStack = state.value.pagerStack

            //make new pager stack by remove last screen from back stack
            val newPagerStack = LinkedList(pagerStack)
            newPagerStack.removeLast()

            //update state here
            _state.update {
                it.copy(
                    pagerStack = newPagerStack,
                    focusOnSearch = newPagerStack.last() == 1,
                    searchKey = ""
                )
            }//end update

        }//end if

    }//end on onLastScreenPopped


    //function for provide prev page
    fun getPrevPage(): Int {

        if (state.value.pagerStack.size > 1) {

            return state.value.pagerStack[state.value.pagerStack.size - 2]

        }//end if

        return 0

    }//end getPrevPage

    //function for update search key
    fun onSearchKeyChanged(newValue: String) {

        //update search key by new value here
        _state.update {
            it.copy(
                searchKey = newValue
            )
        }//end update

    }//end onSearchKeyChanged

}//end HomeViewModel