package com.example.bmi.domain.entity.declarations

interface IBMIEntity {

    val id: Long

    val userId: Long

    val result: Double

    val advice: String?

    val type: String

    val createdAt: String

}//end IBMIEntity