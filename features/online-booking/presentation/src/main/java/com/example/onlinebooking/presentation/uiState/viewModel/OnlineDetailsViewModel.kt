package com.example.onlinebooking.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.online.booking.domain.usecase.declarations.IGetPageOnlineBookingsUseCase
import com.example.online.booking.pagination.OnlineBookingDataSource
import com.example.onlinebooking.presentation.uiState.state.OnlineDetailsUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnlineDetailsViewModel @Inject constructor(
    private val getPageOnlineBookingsUseCase: IGetPageOnlineBookingsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(OnlineDetailsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetTotalOnlineBookings()
    }//end init


    //function for get total offline doctors
    private fun onGetTotalOnlineBookings() {

        //get total offline doctors flow here
        val totalOnlineBookingFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            OnlineBookingDataSource(
                getPageOnlineBookingsUseCase = getPageOnlineBookingsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change total offline doctor state here
        _state.update {
            it.copy(
                totalOnlineBookingStatus = totalOnlineBookingFlow
            )
        }//end update

    }//end onGetTotalOnlineDoctors

}//end OnlineDetailsViewModel