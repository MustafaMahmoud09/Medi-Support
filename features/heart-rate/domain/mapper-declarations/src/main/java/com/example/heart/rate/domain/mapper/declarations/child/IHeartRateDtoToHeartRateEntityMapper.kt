package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.blood.sugar.domain.dto.declarations.IBloodSugarDto
import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.mapper.declarations.IListMapper

interface IBloodSugarDtoToBloodSugarEntityMapper
    : IListMapper<IBloodSugarDto, IBloodSugarEntity>