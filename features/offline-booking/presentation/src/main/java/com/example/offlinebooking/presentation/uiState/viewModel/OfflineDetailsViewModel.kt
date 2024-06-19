package com.example.offlinebooking.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.offline.booking.domain.usecase.declarations.IGetPageOfflineBookingsUseCase
import com.example.offline.booking.pagination.OfflineBookingDataSource
import com.example.offlinebooking.presentation.uiState.state.OfflineDetailsUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OfflineDetailsViewModel @Inject constructor(
    private val getPageOfflineBookingsUseCase: IGetPageOfflineBookingsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(OfflineDetailsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetTotalOfflineBookings()
    }//end init


    //function for get total offline doctors
    private fun onGetTotalOfflineBookings() {

        //get total offline doctors flow here
        val totalOfflineBookingFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            OfflineBookingDataSource(
                getPageOfflineBookingsUseCase = getPageOfflineBookingsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change total offline doctor state here
        _state.update {
            it.copy(
                totalOfflineBookingStatus = totalOfflineBookingFlow
            )
        }//end update

    }//end onGetTotalOfflineBookings


    //function
    fun onRefreshTotalOfflineBookings() {

        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        onGetTotalOfflineBookings()

        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshTotalOfflineBookings


    //function
    fun onTotalOfflineBookingsBackupCreated() {

        _state.update {
            it.copy(
                cacheTotalOfflineBookingStatus = state.value.totalOfflineBookingStatus
            )
        }//end update

    }//end onTotalOfflineBookingsBackupCreated

}//end OfflineDetailsViewModel