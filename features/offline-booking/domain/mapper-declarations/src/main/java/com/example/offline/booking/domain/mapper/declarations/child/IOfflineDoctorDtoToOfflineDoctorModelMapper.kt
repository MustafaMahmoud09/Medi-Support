package com.example.blood.sugar.domain.mapper.declarations.child

import com.example.offline.booking.domain.dto.declarations.IOfflineDoctorDto
import com.example.offline.booking.domain.mapper.declarations.IListMapper
import com.example.offline.booking.domain.model.OfflineDoctorModel

interface IOfflineDoctorDtoToOfflineDoctorModelMapper
    : IListMapper<IOfflineDoctorDto, OfflineDoctorModel>