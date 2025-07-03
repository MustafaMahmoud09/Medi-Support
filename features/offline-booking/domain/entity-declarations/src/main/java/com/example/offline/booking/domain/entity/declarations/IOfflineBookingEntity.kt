package com.example.offline.booking.domain.entity.declarations

interface IOfflineBookingEntity {

    val id: Long

    val userId: Long

    val doctorName: String

    val time: String

    val date: String

    val doctorId: Long

    val clinicLocation: String

    val specialization: String

    val createdAt: String

}//end IOnlineBookingEntity