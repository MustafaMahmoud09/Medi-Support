package com.example.bmi.domain.dto.declarations.lastReocrds

import com.example.bmi.domain.dto.declarations.IBMIDto

interface ILastBMIResponseDto {

    val `data`: List<IBMIDto?>?

    val error: Boolean?

    val message: String?

}//end ILastBMIResponseDto