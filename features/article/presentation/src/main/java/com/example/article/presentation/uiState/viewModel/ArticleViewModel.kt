package com.example.article.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.article.domain.usecase.declarations.IGetArticleByIdUseCase
import com.example.article.presentation.uiElement.screens.article.SingleArticleArgs
import com.example.article.presentation.uiState.state.ArticleUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticleByIdUseCase: IGetArticleByIdUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ArticleUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    private val articleArguments: SingleArticleArgs = SingleArticleArgs(
        savedStateHandle = savedStateHandle
    )

    init {

        onGetArticleId()
        onGetArticleById()

    }//end init

    //function for get article id from arguments
    private fun onGetArticleId() {

        //update article id state here
        _state.update {
            it.copy(
                articleId = articleArguments.articleId
            )
        }

    }//end onGetArticleId


    //function for get article by id
    private fun onGetArticleById() {

        //make coroutine builder scope here
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //get article by id here
                //observe use case flow here for collect article
                getArticleByIdUseCase(
                    id = state.value.articleId
                ).collectLatest { article ->

                    //if article is not empty
                    //update article content state
                    if (article.isNotEmpty()) {
                        _state.update {
                            it.copy(
                                article = article[0]
                            )
                        }//end update
                    }//end if

                    //if articles is empty
                    //make article deleted state true
                    else {
                        _state.update {
                            it.copy(
                                articleDeleted = true
                            )
                        }
                    }//end else

                }//end collectLatest

            }
            catch (ex: Exception) {
                ex.message?.let { Log.d("ERROR", it) }
            }

        }//end coroutine builder scope

    }//end onGetArticleById

}//end ArticleViewModel