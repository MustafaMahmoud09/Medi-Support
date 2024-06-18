package com.example.bloodsugar.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.blood.sugar.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.blood.sugar.pagination.BloodSugarDataSource
import com.example.bloodsugar.presentation.uiState.state.BloodSugarHistoryUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BloodSugarHistoryViewModel @Inject constructor(
    private val getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase,
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(BloodSugarHistoryUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetBloodSugarRecords()
    }//end init

    //function get articles
    private fun onGetBloodSugarRecords() {

        //get current page reminders here
        val bloodSugarPaginationFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            BloodSugarDataSource(
                getPageHistoryRecordsUseCase = getPageHistoryRecordsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change reminders state here
        _state.update {
            it.copy(
                bloodSugarRecords = bloodSugarPaginationFlow
            )
        }

    }//end onGetArticles


    //function
    fun onRefreshBloodSugarRecords() {

        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        onGetBloodSugarRecords()

        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshBloodSugarRecords


    //function
    fun onBloodSugarBackupCreated() {

        _state.update {
            it.copy(
                bloodSugarRecordsBackup = state.value.bloodSugarRecords
            )
        }//end update

    }//end onBloodSugarBackupCreated

}//end BloodSugarHistoryViewModel