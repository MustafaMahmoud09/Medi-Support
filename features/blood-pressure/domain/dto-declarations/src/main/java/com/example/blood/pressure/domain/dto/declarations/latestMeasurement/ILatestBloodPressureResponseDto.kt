package com.example.blood.pressure.domain.dto.declarations.latestMeasurement

import com.example.blood.pressure.domain.dto.declarations.IBloodPressureDto

interface ILatestBloodPressureResponseDto {

    val `data`: List<IBloodPressureDto?>?

    val message: String?

}//end ILatestBloodPressureResponseDto