package com.example.blood.pressure.domain.dto.declarations.adviceMeasurement

import com.example.blood.pressure.domain.dto.declarations.IBloodPressureDto


interface IAdviceBloodPressureResponseDto {

    val data: IBloodPressureDto?

    val message: String?

}//end LatestBloodPressureResponseDto