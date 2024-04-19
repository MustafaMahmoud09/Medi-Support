package com.example.article.domain.mapper.declarations.child

import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.article.domain.mapper.declarations.IListMapper
import com.example.article.domain.model.TitleArticleModel

interface IArticleEntityToArticleTitleModelMapper : IListMapper<IArticleEntity, TitleArticleModel>