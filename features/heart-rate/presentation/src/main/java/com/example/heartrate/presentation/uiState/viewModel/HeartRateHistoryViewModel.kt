package com.example.heartrate.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.heart.rate.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.heart.rate.paginations.HeartRateDataSource
import com.example.heartrate.presentation.uiState.state.HeartRateHistoryUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HeartRateHistoryViewModel @Inject constructor(
    private val getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(HeartRateHistoryUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetHeartRateRecords()
    }//end init

    //function get articles
    private fun onGetHeartRateRecords() {

        //get current page reminders here
        val bloodSugarPaginationFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            HeartRateDataSource(
                getPageHistoryRecordsUseCase = getPageHistoryRecordsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change reminders state here
        _state.update {
            it.copy(
                heartRateRecords = bloodSugarPaginationFlow
            )
        }//end update

    }//end onGetArticles


    //function
    fun onRefreshHeartRateRecords() {

        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        onGetHeartRateRecords()

        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshBloodSugarRecords


    //function
    fun onHeartRateBackupCreated() {

        _state.update {
            it.copy(
                heartRateRecordsBackup = state.value.heartRateRecords
            )
        }//end update

    }//end onBloodSugarBackupCreated

}//end HeartRateHistoryViewModel