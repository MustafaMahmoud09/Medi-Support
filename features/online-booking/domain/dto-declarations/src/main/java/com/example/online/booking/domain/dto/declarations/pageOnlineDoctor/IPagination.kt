package com.example.online.booking.domain.dto.declarations.pageOnlineDoctor

interface IPagination {

    val currentPage: Int?

    val firstPageUrl: String?

    val lastPage: Int?

    val lastPageUrl: String?

    val nextPageUrl: Any?

    val prevPageUrl: Any?

    val total: Int?

}//end IPagination