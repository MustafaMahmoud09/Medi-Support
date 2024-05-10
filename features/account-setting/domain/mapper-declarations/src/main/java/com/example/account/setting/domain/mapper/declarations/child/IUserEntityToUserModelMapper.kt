package com.example.blood.pressure.domain.mapper.declarations.child

import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.blood.pressure.domain.mapper.declarations.IListMapper
import com.example.blood.pressure.domain.model.AdviceBloodPressureModel

interface IBloodPressureEntityToAdviceBloodPressureModelMapper :
    IListMapper<IBloodPressureEntity, AdviceBloodPressureModel>