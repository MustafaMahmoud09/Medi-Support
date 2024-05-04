package com.example.online.booking.domain.dto.declarations.topOnlineDoctors

import com.example.online.booking.domain.dto.declarations.IOnlineDoctorDto


interface ITopOnlineDoctorResponseDto {

    val `data`: List<IOnlineDoctorDto?>?

    val error: Boolean?

    val message: String?

}//end ITopOnlineDoctorResponseDto