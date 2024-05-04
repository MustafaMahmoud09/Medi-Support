package com.example.online.booking.domain.entity.declarations

interface IOnlineBookingEntity {

    val id: Long

    val userId: Long

    val doctorName: String

    val specialization: String

    val bookingStatus: Int

    val doctorActiveStatus: Int

    val createdAt: String

}//end IOnlineBookingEntity