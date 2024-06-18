package com.example.bmi.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.bmi.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.bmi.pagination.BMIDataSource
import com.example.bmi.presentation.uiState.state.BMIHistoryUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BMIHistoryViewModel @Inject constructor(
    private val getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase,
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(BMIHistoryUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetBMIRecords()
    }//end init

    //function get articles
    private fun onGetBMIRecords() {

        //get current page reminders here
        val bmiPaginationFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            BMIDataSource(
                getPageHistoryRecordsUseCase = getPageHistoryRecordsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change reminders state here
        _state.update {
            it.copy(
                bmiRecords = bmiPaginationFlow
            )
        }//end update

    }//end onGetArticles


    //function
    fun onRefreshBMIRecords() {

        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        onGetBMIRecords()

        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshBloodSugarRecords


    //function
    fun onBMIBackupCreated() {

        _state.update {
            it.copy(
                bmiRecordsBackup = state.value.bmiRecords
            )
        }//end update

    }//end onBloodSugarBackupCreated

}//end BloodSugarHistoryViewModel