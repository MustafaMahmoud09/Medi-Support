package com.example.article.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.article.domain.usecase.declarations.IGetPageArticlesUseCase
import com.example.article.pagination.ArticleDataSource
import com.example.article.presentation.uiState.state.ArticlesUiState
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
        val articlePagersFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            ArticleDataSource(
                getPageArticlesUseCase = getPageArticlesUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change reminders state here
        _state.update {
            it.copy(
                articles = articlePagersFlow
            )
        }

    }//end onGetArticles


}//end ArticlesViewModel