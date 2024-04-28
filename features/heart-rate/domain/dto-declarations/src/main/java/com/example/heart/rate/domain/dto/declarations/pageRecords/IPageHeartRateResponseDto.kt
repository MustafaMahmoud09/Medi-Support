package com.example.heart.rate.data.source.dto.execution.pageRecords

interface IPageHeartRateResponseDto {

    val `data`: IData?

    val error: Boolean?

    val message: String?

}//end IPageHeartRateResponseDto