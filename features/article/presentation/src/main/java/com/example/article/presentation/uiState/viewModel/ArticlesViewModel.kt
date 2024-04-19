package com.example.article.presentation.uiState.viewModel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.article.domain.usecase.declarations.IGetPageArticlesUseCase
import com.example.article.pagination.ArticleDataSource
import com.example.article.presentation.uiState.state.ArticlesUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getPageArticlesUseCase: IGetPageArticlesUseCase,
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ArticlesUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetArticles()
    }//end init

    //function get articles
    private fun onGetArticles() {

        //get current page reminders here
        val currentPageReminders = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            ArticleDataSource(
                getPageArticlesUseCase = getPageArticlesUseCase
            )
        }.flow

        //change reminders state here
        _state.update {
            it.copy(
                articles = currentPageReminders
            )
        }

    }//end onGetArticles


}//end ArticlesViewModel