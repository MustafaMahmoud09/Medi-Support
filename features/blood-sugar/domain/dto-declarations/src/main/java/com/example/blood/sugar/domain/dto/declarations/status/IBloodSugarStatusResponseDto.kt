package com.example.blood.sugar.domain.dto.declarations.status

interface IBloodSugarStatusResponseDto {

    val `data`: List<IBloodSugarStatusDto?>?

    val error: Boolean?

    val message: String?

}//end BloodSugarStatusResponseDto