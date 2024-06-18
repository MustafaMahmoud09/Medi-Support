package com.example.onlinebooking.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.online.booking.domain.usecase.declarations.IGetTotalOnlineDoctorsUseCase
import com.example.online.booking.pagination.TotalOnlineDoctorDataSource
import com.example.onlinebooking.presentation.uiState.state.TotalOnlineDoctorsUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TotalOnlineDoctorsViewModel @Inject constructor(
    private val getTotalOnlineDoctorsUseCase: IGetTotalOnlineDoctorsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(TotalOnlineDoctorsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetTotalOnlineDoctors()
    }//end init

    //function for get total offline doctors
    private fun onGetTotalOnlineDoctors() {

        //get total offline doctors flow here
        val totalOnlineDoctorFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            TotalOnlineDoctorDataSource(
                getTotalOnlineDoctorsUseCase = getTotalOnlineDoctorsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change total offline doctor state here
        _state.update {
            it.copy(
                totalOnlineDoctorsStatus = totalOnlineDoctorFlow
            )
        }//end update

    }//end onGetTotalOnlineDoctors


    //function
    fun onRefreshOnlineDoctors() {

        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        onGetTotalOnlineDoctors()

        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshOnlineDoctors


    //function
    fun onTotalOnlineDoctorsBackupCreated() {

        _state.update {
            it.copy(
                cacheOnlineDoctorsStatus = state.value.totalOnlineDoctorsStatus
            )
        }//end update

    }//end onTotalOnlineDoctorsBackupCreated

}//end TotalOfflineDoctorsViewModel