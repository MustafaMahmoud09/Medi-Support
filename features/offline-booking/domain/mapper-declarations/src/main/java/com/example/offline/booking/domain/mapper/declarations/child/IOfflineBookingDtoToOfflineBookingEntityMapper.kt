package com.example.offline.booking.domain.mapper.declarations.child

import com.example.offline.booking.domain.dto.declarations.bookingDetails.IOfflineBookingDto
import com.example.offline.booking.domain.entity.declarations.IOfflineBookingEntity
import com.example.offline.booking.domain.mapper.declarations.IListMapper

interface IOfflineBookingDtoToOfflineBookingEntityMapper
    : IListMapper<IOfflineBookingDto, IOfflineBookingEntity>