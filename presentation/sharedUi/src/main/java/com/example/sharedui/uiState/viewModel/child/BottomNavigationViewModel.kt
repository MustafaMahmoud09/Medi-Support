package com.example.sharedui.uiState.viewModel.child

import com.example.sharedui.uiState.state.BottomNavigationUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(BottomNavigationUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    fun onBottomNavigationVisibilityChanged(show: Boolean) {

        //check new value not equal current value to execute scope
        if (show != state.value.isVisible) {

            //change visibility state here
            _state.update {
                it.copy(
                    isVisible = show
                )
            }//end update

        }//end if

    }//end onBottomNavigationVisibilityChanged


//    companion object {
//
//        @Inject
//        private val viewModelObject: BottomNavigationViewModel = TODO()
//
//        fun provideViewModel(): BottomNavigationViewModel {
//            return viewModelObject
//        }
//
//    }//end companion object

}//end BottomNavigationViewModel