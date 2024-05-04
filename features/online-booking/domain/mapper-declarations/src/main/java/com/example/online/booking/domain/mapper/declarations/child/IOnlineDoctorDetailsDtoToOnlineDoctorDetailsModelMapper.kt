package com.example.online.booking.domain.mapper.declarations.child

import com.example.online.booking.domain.dto.declarations.doctorDetails.IOnlineDoctorDetailsDto
import com.example.online.booking.domain.mapper.declarations.IObjectMapper
import com.example.online.booking.domain.model.OnlineDoctorDetailsModel

interface IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
    : IObjectMapper<IOnlineDoctorDetailsDto, OnlineDoctorDetailsModel>