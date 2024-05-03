package com.example.offline.booking.domain.dto.declarations.doctorDetails

interface IDoctorDetailsDto {

    val avatar: String?

    val avgRating: Float?

    val bio: String?

    val clinicLocation: String?

    val dates: List<IDate?>?

    val firstName: String?

    val id: Long?

    val photo: Any?

    val lastName: String?

    val price: Int?

    val specialization: String?

    val userRating: Float?

}//end IDoctorDetailsDto