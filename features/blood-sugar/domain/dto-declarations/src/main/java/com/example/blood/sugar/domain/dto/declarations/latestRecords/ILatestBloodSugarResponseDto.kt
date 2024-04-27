package com.example.blood.sugar.domain.dto.declarations.latestRecords

import com.example.blood.sugar.domain.dto.declarations.IBloodSugarDto


interface ILatestBloodSugarResponseDto {

    val `data`: List<IBloodSugarDto?>?

    val error: Boolean?

    val message: String?

}//end ILatestBloodSugarResponseDto