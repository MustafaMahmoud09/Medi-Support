package com.example.heart.rate.data.source.dto.execution.lastRecords

import com.example.heart.rate.domain.dto.declarations.IHeartRateDto

interface ILastHeartRateResponseDto {

    val `data`: List<IHeartRateDto?>?

    val error: Boolean?

    val message: String?

}//end ILastHeartRateResponseDto