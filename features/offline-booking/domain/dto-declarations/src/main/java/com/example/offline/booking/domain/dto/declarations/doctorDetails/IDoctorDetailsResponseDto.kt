package com.example.offline.booking.domain.dto.declarations.doctorDetails


interface IDoctorDetailsResponseDto {

    val doctorDetailsDto: IDoctorDetailsDto?

    val error: Boolean?

    val message: String?

}//end IDoctorDetailsResponseDto