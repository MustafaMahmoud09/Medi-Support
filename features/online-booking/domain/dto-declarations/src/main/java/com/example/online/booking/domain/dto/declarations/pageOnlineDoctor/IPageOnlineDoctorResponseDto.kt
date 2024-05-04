package com.example.online.booking.domain.dto.declarations.pageOnlineDoctor


interface IPageOnlineDoctorResponseDto {

    val `data`: IOnlineDoctorData?

    val error: Boolean?

    val message: String?

}//end IPageOnlineDoctorResponseDto