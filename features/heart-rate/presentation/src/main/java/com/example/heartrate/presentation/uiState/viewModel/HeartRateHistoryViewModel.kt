package com.example.heartrate.presentation.uiState.viewModel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.heart.rate.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.heart.rate.paginations.HeartRateDataSource
import com.example.heartrate.presentation.uiState.state.HeartRateHistoryUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

        //change reminders state here
        _state.update {
            it.copy(
                heartRateRecords = bloodSugarPaginationFlow
            )
        }//end update

    }//end onGetArticles

}//end HeartRateHistoryViewModel