package com.example.article.presentation.uiState.state

import com.example.article.domain.model.BodyArticleModel

data class ArticleUiState(
    val articleId: Long = 0,
    val article: BodyArticleModel = BodyArticleModel(
        id = 0,
        body = "",
        title = "",
        image = ""
    ),
    val articleDeleted: Boolean = false
)
