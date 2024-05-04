package com.example.offline.booking.domain.mapper.declarations.child

import com.example.offline.booking.domain.entity.declarations.IOfflineBookingEntity
import com.example.offline.booking.domain.mapper.declarations.IListMapper
import com.example.offline.booking.domain.model.OfflineBookingModel

interface IOfflineBookingEntityToOfflineBookingModelMapper
    : IListMapper<IOfflineBookingEntity, OfflineBookingModel>