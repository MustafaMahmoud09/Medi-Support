package com.example.article.presentation.uiState.state

import androidx.paging.PagingData
import com.example.article.domain.model.TitleArticleModel
import kotlinx.coroutines.flow.Flow

data class ArticlesUiState(
    val articles: Flow<PagingData<TitleArticleModel>>? = null,
    val placeHolderArticle: TitleArticleModel = TitleArticleModel(
        id = 0,
        title = "place holder title",
        image = "ss"
    ),
)
