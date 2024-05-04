package com.example.online.booking.domain.dto.declarations.bookingDetails


interface IBookingDetailDto {

    val createdAt: String?

    val doctorName: String?

    val id: Long?

    val userId: Long?

    val status: Int?

    val username: String?

    val specialization: String?

    val activeStatus: Int?

}//end IBookingDetailDto