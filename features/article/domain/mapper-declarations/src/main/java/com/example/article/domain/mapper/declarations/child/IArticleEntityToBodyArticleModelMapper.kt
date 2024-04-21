package com.example.article.domain.mapper.declarations.child

import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.article.domain.mapper.declarations.IListMapper
import com.example.article.domain.model.BodyArticleModel

interface IArticleEntityToBodyArticleModelMapper : IListMapper<IArticleEntity, BodyArticleModel>