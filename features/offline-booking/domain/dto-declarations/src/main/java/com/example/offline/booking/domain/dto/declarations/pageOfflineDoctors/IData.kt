package com.example.offline.booking.domain.dto.declarations.pageOfflineDoctors

import com.example.offline.booking.domain.dto.declarations.IOfflineDoctorDto


interface IData {

    val currentPage: Int?

    val `data`: List<IOfflineDoctorDto>?

    val lastPage: Int?

}//end IData