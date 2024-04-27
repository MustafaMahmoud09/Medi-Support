package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.mapper.declarations.IListMapper
import com.example.blood.sugar.domain.model.SimpleBloodSugarModel

interface IBloodSugarEntityToSimpleBloodSugarModelMapper
    : IListMapper<IBloodSugarEntity, SimpleBloodSugarModel>