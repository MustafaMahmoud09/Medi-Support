package com.example.online.booking.domain.mapper.declarations.child

import com.example.online.booking.domain.dto.declarations.bookingDetails.IBookingDetailDto
import com.example.online.booking.domain.entity.declarations.IOnlineBookingEntity
import com.example.online.booking.domain.mapper.declarations.IListMapper

interface IOnlineBookingDtoToOnlineBookingEntityMapper
    : IListMapper<IBookingDetailDto, IOnlineBookingEntity>