package com.example.offlinebooking.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.offline.booking.domain.usecase.declarations.IGetTotalOfflineDoctorsUseCase
import com.example.offline.booking.pagination.TotalOfflineDoctorDataSource
import com.example.offlinebooking.presentation.uiState.state.TotalOfflineDoctorsUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TotalOfflineDoctorsViewModel @Inject constructor(
    private val getTotalOfflineDoctorsUseCase: IGetTotalOfflineDoctorsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(TotalOfflineDoctorsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetTotalOfflineDoctors()
    }//end init


    //function for get total offline doctors
    private fun onGetTotalOfflineDoctors() {

        //change total offline doctor state here
        _state.update {
            it.copy(
                totalOfflineDoctorsStatus = null
            )
        }//end update

        //get total offline doctors flow here
        val totalOfflineDoctorFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            TotalOfflineDoctorDataSource(
                getTotalOfflineDoctorsUseCase = getTotalOfflineDoctorsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change total offline doctor state here
        _state.update {
            it.copy(
                totalOfflineDoctorsStatus = totalOfflineDoctorFlow
            )
        }//end update

    }//end onGetTotalOfflineDoctors


    //function for refresh total offline doctors
    fun onRefreshTotalOfflineDoctors() {

        //update backup doctors state and refresh state here
        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        //get total offline doctors again
        onGetTotalOfflineDoctors()

        //update backup doctors state and refresh state here
        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshTotalOfflineDoctors


    //function
    fun onTotalOfflineDoctorsBackupCreated() {

        _state.update {
            it.copy(
                totalOfflineDoctorsBackupStatus = state.value.totalOfflineDoctorsStatus
            )
        }//end update

    }//end onTotalOfflineDoctorsBackupCreated

}//end TotalOfflineDoctorsViewModel