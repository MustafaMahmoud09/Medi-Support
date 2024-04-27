package com.example.blood.sugar.domain.dto.declarations.pageRecords

interface IPageBloodSugarResponseDto {

    val `data`: IData?

    val error: Boolean?

    val message: String?

}//end IPageBloodSugarResponseDto