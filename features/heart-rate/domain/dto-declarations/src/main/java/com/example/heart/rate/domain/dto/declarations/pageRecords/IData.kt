package com.example.heart.rate.data.source.dto.execution.pageRecords

import com.example.heart.rate.domain.dto.declarations.IHeartRateDto

interface IData {

    val currentPage: Int?

    val lastPage: Int?

    val records: List<IHeartRateDto?>?

}//end IData