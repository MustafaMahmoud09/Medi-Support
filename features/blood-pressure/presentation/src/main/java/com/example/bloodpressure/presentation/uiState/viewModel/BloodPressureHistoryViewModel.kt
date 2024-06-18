package com.example.bloodpressure.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.blood.pressure.domain.usecase.declarations.IGetPageHistoryRecordUseCase
import com.example.blood.pressure.pagination.BloodPressureDataSource
import com.example.bloodpressure.presentation.uiState.state.BloodPressureHistoryUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BloodPressureHistoryViewModel @Inject constructor(
    private val getPageHistoryRecordUseCase: IGetPageHistoryRecordUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(BloodPressureHistoryUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetBloodPressureRecords()
    }//end init

    //function get articles
    private fun onGetBloodPressureRecords() {

        //get current page reminders here
        val bloodPressurePaginationFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            BloodPressureDataSource(
                getPageHistoryRecordUseCase = getPageHistoryRecordUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change reminders state here
        _state.update {
            it.copy(
                bloodPressureRecords = bloodPressurePaginationFlow
            )
        }

    }//end onGetArticles


    //function
    fun onRefreshBloodPressureRecords() {

        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        onGetBloodPressureRecords()

        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshBloodSugarRecords


    //function
    fun onBloodPressureBackupCreated() {

        _state.update {
            it.copy(
                bloodPressureRecordsBackup = state.value.bloodPressureRecords
            )
        }//end update

    }//end onBloodSugarBackupCreated

}//end BloodPressureHistoryViewModel