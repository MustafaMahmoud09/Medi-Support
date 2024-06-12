package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.bmi.domain.dto.declarations.IBMIDto
import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.bmi.domain.mapper.declarations.IListMapper

interface IBMIDtoToBMIEntityMapper
    : IListMapper<IBMIDto, IBMIEntity>