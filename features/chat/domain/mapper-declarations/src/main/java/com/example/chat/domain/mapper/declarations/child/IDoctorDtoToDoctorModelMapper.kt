package com.example.chat.domain.mapper.declarations.child

import com.example.chat.domain.dto.declarations.doctorInfo.IFetch
import com.example.chat.domain.mapper.declarations.IListMapper
import com.example.chat.domain.model.DoctorModel


interface IDoctorDtoToDoctorModelMapper
    : IListMapper<IFetch, DoctorModel>