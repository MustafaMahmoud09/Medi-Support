package com.example.blood.sugar.domain.dto.declarations


interface IBloodSugarDto {

    val advice: IAdvice?

    val createdAt: String?

    val dayName: String?

    val id: Long?

    val level: Double?

    val userId: Long?

}//end IBloodSugarDto