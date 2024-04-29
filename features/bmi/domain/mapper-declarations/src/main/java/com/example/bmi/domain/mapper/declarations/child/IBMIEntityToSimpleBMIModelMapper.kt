package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.bmi.domain.mapper.declarations.IListMapper
import com.example.bmi.domain.model.SimpleBMIModel

interface IBMIEntityToSimpleBMIModelMapper
    : IListMapper<IBMIEntity, SimpleBMIModel>