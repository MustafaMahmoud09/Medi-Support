package com.example.bmi.domain.dto.declarations.pageRecords

interface IPageBMIResponseDto {

    val `data`: IData?

    val error: Boolean?

    val message: String?

}//end IPageBMIResponseDto