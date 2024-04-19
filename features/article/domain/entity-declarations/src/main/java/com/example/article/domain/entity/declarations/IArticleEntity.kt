package com.example.article.domain.entity.declarations

import java.sql.Date

interface IArticleEntity {

    val id: Long

    val title: String?

    val body: String?

    val image: String?

    val createdAt: String

}//end IArticleEntity