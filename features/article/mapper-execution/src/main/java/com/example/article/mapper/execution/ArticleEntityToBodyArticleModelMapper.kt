package com.example.article.mapper.execution

import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.article.domain.mapper.declarations.child.IArticleEntityToBodyArticleModelMapper
import com.example.article.domain.model.BodyArticleModel

class ArticleEntityToBodyArticleModelMapper(
    private val imageUrl: String
) : IArticleEntityToBodyArticleModelMapper {

    //function for convert list of article entity to list of article model
    override fun listConvertor(list: List<IArticleEntity>): List<BodyArticleModel> {

        return list.map { articleEntity ->
            objectConvertor(articleEntity)
        }//end map

    }//end listConvertor

    //function for convert article entity object to article model object
    override fun objectConvertor(obj: IArticleEntity): BodyArticleModel {

        return BodyArticleModel(
            id = obj.id ?: 0,
            title = obj.title ?: "",
            body = obj.body ?: "",
            image = "$imageUrl${obj.image.toString()}"
        )

    }//end objectConvertor

}//end ArticleEntityToBodyArticleModelMapper