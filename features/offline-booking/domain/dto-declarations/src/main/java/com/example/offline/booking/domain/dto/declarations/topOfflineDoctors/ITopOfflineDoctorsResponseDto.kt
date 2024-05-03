package com.example.offline.booking.domain.dto.declarations.topOfflineDoctors

import com.example.offline.booking.domain.dto.declarations.IOfflineDoctorDto


interface ITopOfflineDoctorsResponseDto {

    val `data`: List<IOfflineDoctorDto?>?

    val error: Boolean?

    val message: String?

}//end TopOfflineDoctorsResponseDto