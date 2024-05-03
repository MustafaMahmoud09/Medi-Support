package com.example.offline.booking.domain.dto.declarations.doctorTime

interface IDoctorTimeResponseDto {

    val `data`: List<ITime?>?

    val error: Boolean?

    val message: String?

}//end IDoctorTimeResponseDto