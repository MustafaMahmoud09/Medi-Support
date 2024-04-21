package com.example.article.mapper.execution

import com.example.article.data.source.local.entity.execution.entities.article.ArticleEntity
import com.example.article.domain.dto.declarations.IArticleDataDto
import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.article.domain.mapper.declarations.child.IArticleDtoToArticleEntityMapper

class ArticleDtoToArticleEntityMapper(

) : IArticleDtoToArticleEntityMapper {

    //function for convert list of article dto to list of article entity
    override fun listConvertor(list: List<IArticleDataDto>): List<IArticleEntity> {

        return list.map { articleDto ->
            objectConvertor(articleDto)
        }//end map

    }//end listConvertor

    //function for convert article dto object to article entity object
    override fun objectConvertor(obj: IArticleDataDto): IArticleEntity {

        return ArticleEntity(
            id = obj.id!!.toLong(),
            title = obj.title,
            body = obj.body,
            image = obj.image,
            createdAt = obj.createdAt.toString()
        )

    }//end objectConvertor

}//end ArticleDtoToArticleEntityMapper