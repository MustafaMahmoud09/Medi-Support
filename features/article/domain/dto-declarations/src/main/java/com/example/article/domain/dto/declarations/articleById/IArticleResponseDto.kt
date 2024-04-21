package com.example.article.domain.dto.declarations.articleById

import com.example.article.domain.dto.declarations.IArticleDataDto

interface IArticleResponseDto {

    val `data`: IArticleDataDto?

    val message: Any?

}//end IArticleResponseDto