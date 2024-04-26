package com.example.blood.pressure.domain.dto.declarations.pageMeasurement


interface IMeta {

    val currentPage: Int?

    val from: Int?

    val lastPage: Int?

    val links: List<ILink?>?

    val path: String?

    val perPage: Int?

    val to: Int?

    val total: Int?

}//end IMeta