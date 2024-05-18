package com.example.online.booking.domain.model

data class PaymentModel(
    val paymentIntent: String,
    val bookingId: Long
)
