package com.example.blood.pressure.domain.dto.declarations.pageMeasurement


import com.example.blood.pressure.domain.dto.declarations.IBloodPressureDto

interface IPageBloodPressureResponseDto {

    val `data`: List<IBloodPressureDto?>?

    val links: ILinks?

    val meta: IMeta?

}//end IPageBloodPressureResponseDto