package com.example.article.domain.usecase.declarations

import com.example.article.domain.model.BodyArticleModel
import kotlinx.coroutines.flow.Flow

interface IGetArticleByIdUseCase {

    suspend operator fun invoke(id: Long): Flow<List<BodyArticleModel>>

}//end IGetArticleByIdUseCase