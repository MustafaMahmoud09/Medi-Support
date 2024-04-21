package com.example.article.domain.dto.declarations.articles

import com.example.article.domain.dto.declarations.IArticleDataDto

interface IArticlesResponseDto {

    val `data`: List<IArticleDataDto?>?

    val links: ILinks?

    val meta: IMeta?

}//end IArticleDto