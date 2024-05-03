package com.example.offlinebooking.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.offline.booking.domain.usecase.declarations.ISearchOnOfflineDoctorsUseCase
import com.example.offline.booking.pagination.SearchOnOfflineDoctorDataSource
import com.example.offlinebooking.presentation.uiState.state.SearchUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.LinkedList
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchOnOfflineDoctorsUseCase: ISearchOnOfflineDoctorsUseCase,
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(SearchUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onSearchOnOfflineDoctors()
    }//end init

    private fun onSearchOnOfflineDoctors() {

        //create coroutine builder for call suspend functions in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                state.value.searchKey.debounce(
                    timeoutMillis = 300
                ).collectLatest { searchKey ->

                    if (searchKey.trim().isNotEmpty()) {

                        //get total offline doctors flow here
                        val searchOfflineDoctorFlow = Pager(
                            config = PagingConfig(
                                pageSize = 10
                            )
                        ) {
                            SearchOnOfflineDoctorDataSource(
                                searchOnOfflineDoctorsUseCase = searchOnOfflineDoctorsUseCase,
                                searchKey = searchKey
                            )
                        }.flow
                            .cachedIn(viewModelScope)
                            .flowOn(Dispatchers.IO)

                        _state.update {
                            it.copy(
                                searchOfflineDoctorsStatus = searchOfflineDoctorFlow
                            )
                        }//end update

                    }//end if
                    else {
                        _state.update {
                            it.copy(
                                searchOfflineDoctorsStatus = null
                            )
                        }//end update
                    }//end else

                }//end collectLatest

            }//end try
            catch (ex: Exception) {
                ex.message?.let { Log.d("TAG", it) }
            }//end catch

        }//end coroutine builder

    }//end onSearchOnOfflineDoctors

    //function for update search key
    fun onSearchKeyChanged(newValue: String) {

        //update search key by new value here
        _state.value.searchKey.update {
            newValue
        }

    }//end onSearchKeyChanged

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

            onSearchKeyChanged(
                newValue = ""
            )

            //update ui stack by new pager stack
            _state.update {
                it.copy(
                    pagerStack = newPagerStack,
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

            onSearchKeyChanged(
                newValue = ""
            )

            //update state here
            _state.update {
                it.copy(
                    pagerStack = newPagerStack,
                    focusOnSearch = newPagerStack.last() == 1
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

}//end SearchViewModel