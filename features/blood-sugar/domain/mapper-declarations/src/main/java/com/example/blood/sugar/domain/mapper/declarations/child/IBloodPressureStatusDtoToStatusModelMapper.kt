package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.blood.sugar.domain.dto.declarations.status.IBloodSugarStatusDto
import com.example.blood.sugar.domain.mapper.declarations.IListMapper
import com.example.blood.sugar.domain.model.StatusModel

interface IBloodPressureStatusDtoToStatusModelMapper :
    IListMapper<IBloodSugarStatusDto, StatusModel>