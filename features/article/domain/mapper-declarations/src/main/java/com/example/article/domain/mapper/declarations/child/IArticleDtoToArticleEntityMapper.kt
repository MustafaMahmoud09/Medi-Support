package com.example.article.domain.mapper.declarations.child

import com.example.article.domain.dto.declarations.article.IArticleDataDto
import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.article.domain.mapper.declarations.IListMapper

interface IArticleDtoToArticleEntityMapper : IListMapper<IArticleDataDto, IArticleEntity>