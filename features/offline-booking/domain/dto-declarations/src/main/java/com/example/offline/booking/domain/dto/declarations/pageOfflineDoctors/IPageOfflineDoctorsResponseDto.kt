package com.example.offline.booking.domain.dto.declarations.pageOfflineDoctors

import com.example.offline.booking.domain.dto.declarations.pageOfflineDoctors.IData


interface IPageOfflineDoctorsResponseDto {

    val `data`: IData?

    val error: Boolean?

    val message: String?

}//end IPageOfflineDoctorsResponseDto