package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.heart.rate.domain.dto.declarations.IHeartRateDto
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity
import com.example.heart.rate.domain.mapper.declarations.IListMapper

interface IHeartRateDtoToHeartRateEntityMapper
    : IListMapper<IHeartRateDto, IHeartRateEntity>