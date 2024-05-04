package com.example.online.booking.data.source.remote.data.dto.execution.bookingDetails

import com.example.online.booking.domain.dto.declarations.bookingDetails.IBookingDetailsResponseDto
import com.google.gson.annotations.SerializedName

data class BookingDetailsResponseDto(
    @SerializedName("data")
    override val `data`: BookingDetailData?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IBookingDetailsResponseDto