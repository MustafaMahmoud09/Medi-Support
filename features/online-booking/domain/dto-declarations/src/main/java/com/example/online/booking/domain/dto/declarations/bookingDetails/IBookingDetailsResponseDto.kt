package com.example.online.booking.domain.dto.declarations.bookingDetails


interface IBookingDetailsResponseDto {

    val `data`: IBookingDetailData?

    val error: Boolean?

    val message: String?

}//end IBookingDetailsResponseDto