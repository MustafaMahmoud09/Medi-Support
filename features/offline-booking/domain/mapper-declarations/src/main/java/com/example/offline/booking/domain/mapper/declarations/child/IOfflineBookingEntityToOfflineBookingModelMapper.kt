package com.example.online.booking.domain.mapper.declarations.child

import com.example.online.booking.domain.entity.declarations.IOnlineBookingEntity
import com.example.online.booking.domain.mapper.declarations.IListMapper
import com.example.online.booking.domain.model.OnlineBookingModel

interface IOnlineBookingEntityToOnlineBookingModelMapper
    : IListMapper<IOnlineBookingEntity, OnlineBookingModel>