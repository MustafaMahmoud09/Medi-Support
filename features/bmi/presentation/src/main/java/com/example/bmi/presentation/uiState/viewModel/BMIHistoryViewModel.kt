package com.example.bmi.presentation.uiState.viewModel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.bmi.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.bmi.pagination.BMIDataSource
import com.example.bmi.presentation.uiState.state.BloodSugarHistoryUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BMIHistoryViewModel @Inject constructor(
    private val getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase,
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(BloodSugarHistoryUiState())

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

        //change reminders state here
        _state.update {
            it.copy(
                bmiRecords = bmiPaginationFlow
            )
        }//end update

    }//end onGetArticles

}//end BloodSugarHistoryViewModel