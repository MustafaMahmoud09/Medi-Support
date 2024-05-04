package com.example.online.booking.domain.dto.declarations.bookingDetails

import com.example.online.booking.domain.dto.declarations.pageOnlineDoctor.IPagination

interface IBookingDetailData {

    val `data`: List<IBookingDetailDto>?

    val pagination: IPagination?

}//end IBookingDetailData