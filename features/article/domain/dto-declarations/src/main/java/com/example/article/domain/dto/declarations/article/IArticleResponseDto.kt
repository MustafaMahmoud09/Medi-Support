package com.example.article.domain.dto.declarations.article

interface IArticleResponseDto {

    val `data`: List<IArticleDataDto?>?

    val links: ILinks?

    val meta: IMeta?

}//end IArticleDto