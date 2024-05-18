package com.example.online.booking.domain.mapper.declarations.child

import com.example.online.booking.domain.dto.declarations.payment.IPaymentDto
import com.example.online.booking.domain.mapper.declarations.IListMapper
import com.example.online.booking.domain.model.PaymentModel

interface IPaymentDtoToPaymentModelMapper
    : IListMapper<IPaymentDto, PaymentModel>