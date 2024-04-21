package com.example.article.domain.dto.declarations.article

interface IMeta {

    val currentPage: Int?

    val from: Int?

    val lastPage: Int?

    val links: List<ILink?>?

    val path: String?

    val perPage: Int?

    val to: Int?

    val total: Int?

}//end IMeta