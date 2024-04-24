package com.example.blood.pressure.domain.mapper.declarations.child

import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.ILatestBloodPressureDto
import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.blood.pressure.domain.mapper.declarations.IListMapper

interface ILatestBloodPressureDtoToBloodPressureEntityMapper :
    IListMapper<ILatestBloodPressureDto, IBloodPressureEntity>