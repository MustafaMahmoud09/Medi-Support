package com.example.blood.sugar.domain.entity.declarations

interface IBloodSugarEntity {

    val id: Long

    val userId: Long

    val level: Double

    val advice: String?

    val type: String

    val createdAt: String

}//end IBloodSugarEntity