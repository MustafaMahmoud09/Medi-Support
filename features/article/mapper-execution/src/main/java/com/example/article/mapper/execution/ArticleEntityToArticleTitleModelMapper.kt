package com.example.article.mapper.execution

import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.article.domain.mapper.declarations.child.IArticleEntityToArticleTitleModelMapper
import com.example.article.domain.model.TitleArticleModel

class ArticleEntityToArticleTitleModelMapper : IArticleEntityToArticleTitleModelMapper {

    //function for convert list of article entity to list of article model
    override fun listConvertor(list: List<IArticleEntity>): List<TitleArticleModel> {

        return list.map { article ->
            objectConvertor(article)
        }//end map

    }//end listConvertor

    //function for convert article entity object to article model object
    override fun objectConvertor(obj: IArticleEntity): TitleArticleModel {

        return TitleArticleModel(
            id = obj.id,
            title = obj.title.toString(),
            image = obj.image.toString()
        )

    }//end objectConvertor

}//end ArticleEntityToArticleTitleModelMapper