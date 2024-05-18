package com.example.online.booking.data.source.remote.data.dto.execution.payment


import com.example.online.booking.domain.dto.declarations.payment.IPaymentResponseDto
import com.google.gson.annotations.SerializedName

data class PaymentResponseDto(
    @SerializedName("response")
    override val response: PaymentDto?
): IPaymentResponseDto