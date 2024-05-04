package com.example.online.booking.domain.mapper.declarations.child

import com.example.online.booking.domain.dto.declarations.IOnlineDoctorDto
import com.example.online.booking.domain.mapper.declarations.IListMapper
import com.example.online.booking.domain.model.OnlineDoctorModel


interface IOnlineDoctorDtoToOnlineDoctorModelMapper
    : IListMapper<IOnlineDoctorDto, OnlineDoctorModel>