package com.example.online.booking.data.source.remote.data.dto.execution.payment


import com.google.gson.annotations.SerializedName

data class PaymentResponseDto(
    @SerializedName("response")
    val response: PaymentDto?
)