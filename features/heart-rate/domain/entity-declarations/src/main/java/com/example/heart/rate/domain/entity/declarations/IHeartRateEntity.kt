package com.example.heart.rate.domain.entity.declarations

interface IHeartRateEntity {

    val id: Long

    val userId: Long

    val rate: Long

    val advice: String?

    val type: String

    val createdAt: String

}//end IHeartRateEntity