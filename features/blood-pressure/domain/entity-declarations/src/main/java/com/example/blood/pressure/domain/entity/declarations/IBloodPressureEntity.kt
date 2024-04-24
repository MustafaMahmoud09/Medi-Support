package com.example.blood.pressure.domain.entity.declarations

interface IBloodPressureEntity {

    val id: Long

    val userId: Long

    val systolic: Long

    val diastolic: Long

    val advice: String?

    val type: String

    val createdAt: String

}//end IBloodPressureEntity