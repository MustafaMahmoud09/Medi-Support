package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.heart.rate.domain.domain.model.ChartHeartRateModel
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity
import com.example.heart.rate.domain.mapper.declarations.IListMapper

interface IHeartRateEntityToChartHeartRateModelMapper
    : IListMapper<IHeartRateEntity, ChartHeartRateModel>