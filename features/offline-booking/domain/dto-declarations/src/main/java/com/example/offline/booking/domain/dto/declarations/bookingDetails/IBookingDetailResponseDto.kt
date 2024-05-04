package com.example.offline.booking.domain.dto.declarations.bookingDetails

interface IBookingDetailResponseDto {

    val `data`: IBookingDetailData?

    val error: Boolean?

    val message: String?

}//end IBookingDetailResponseDto