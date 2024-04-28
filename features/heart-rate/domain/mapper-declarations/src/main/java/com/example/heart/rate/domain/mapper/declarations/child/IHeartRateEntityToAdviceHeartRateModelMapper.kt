package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.heart.rate.domain.domain.model.AdviceHeartRateModel
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity
import com.example.heart.rate.domain.mapper.declarations.IListMapper

interface IHeartRateEntityToAdviceHeartRateModelMapper
    : IListMapper<IHeartRateEntity, AdviceHeartRateModel>