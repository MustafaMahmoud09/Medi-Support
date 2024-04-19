package com.example.article.domain.dto.declarations.article

interface IArticleDto {

    val `data`: List<IData?>?

    val links: ILinks?

    val meta: IMeta?

}//end IArticleDto