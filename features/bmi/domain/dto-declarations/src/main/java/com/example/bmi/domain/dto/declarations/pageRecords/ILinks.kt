package com.example.bmi.domain.dto.declarations.pageRecords

interface ILinks {

    val currentPage: Int?

    val firstPageUrl: String?

    val lastPage: Int?

    val lastPageUrl: String?

    val nextPageUrl: String?

    val prevPageUrl: Any?

    val total: Int?

}//end ILinks