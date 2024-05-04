package com.example.offline.booking.domain.dto.declarations.bookingDetails


interface IBookingDetailData {

    val currentPage: Int?

    val `data`: List<IOfflineBookingDto>?

    val lastPage: Int?

}//end IBookingDetailData