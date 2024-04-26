package com.example.blood.pressure.domain.mapper.declarations.child

import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.blood.pressure.domain.mapper.declarations.IListMapper
import com.example.blood.pressure.domain.model.SimpleBloodPressureModel

interface IBloodPressureEntityToSimpleBloodPressureModelMapper
    : IListMapper<IBloodPressureEntity, SimpleBloodPressureModel>