package com.example.online.booking.domain.dto.declarations.pageOnlineDoctor

import com.example.online.booking.domain.dto.declarations.IOnlineDoctorDto


interface IOnlineDoctorData {

    val `data`: List<IOnlineDoctorDto>?

    val pagination: IPagination?

}//end IData