package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.bmi.domain.mapper.declarations.IListMapper
import com.example.bmi.domain.model.AdviceBMIModel

interface IBMIEntityToAdviceBMIModelMapper
    : IListMapper<IBMIEntity, AdviceBMIModel>