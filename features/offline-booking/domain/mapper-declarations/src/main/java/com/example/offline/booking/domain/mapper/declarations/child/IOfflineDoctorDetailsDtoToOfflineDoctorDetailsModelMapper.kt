package com.example.offline.booking.domain.mapper.declarations.child

import com.example.offline.booking.domain.dto.declarations.doctorDetails.IDoctorDetailsDto
import com.example.offline.booking.domain.mapper.declarations.IListMapper
import com.example.offline.booking.domain.mapper.declarations.IObjectMapper
import com.example.offline.booking.domain.model.OfflineDoctorDetailsModel

interface IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
    : IObjectMapper<IDoctorDetailsDto, OfflineDoctorDetailsModel>