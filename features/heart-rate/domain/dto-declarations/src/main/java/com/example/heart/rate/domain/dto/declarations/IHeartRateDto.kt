package com.example.heart.rate.domain.dto.declarations

interface IHeartRateDto {

    val advice: IAdvice?

    val createdAt: String?

    val dayName: String?

    val heartRate: Long?

    val id: Long?

    val userId: Long?

}//end IHeartRateDto