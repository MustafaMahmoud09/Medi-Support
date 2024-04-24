package com.example.blood.pressure.domain.dto.declarations.descMeasurement


interface IDescBloodPressureResponseDto{

    val `data`: Map<String, Long>?

    val error: Boolean?

    val message: String?

}//end IDescBloodPressureResponseDto