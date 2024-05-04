package com.example.offline.booking.domain.entity.declarations

interface IOnlineBookingEntity {

    val id: Long

    val userId: Long

    val doctorName: String

    val time: String

    val date: String

    val specialization: String

    val createdAt: String

}//end IOnlineBookingEntity