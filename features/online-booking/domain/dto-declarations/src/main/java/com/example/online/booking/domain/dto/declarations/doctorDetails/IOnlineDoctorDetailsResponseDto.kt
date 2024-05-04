package com.example.online.booking.domain.dto.declarations.doctorDetails


interface IOnlineDoctorDetailsResponseDto {

    val `data`: IOnlineDoctorDetailsDto?

    val error: Boolean?

    val message: String?

}//end IOnlineDoctorDetailsResponseDto