package com.example.account.setting.domain.dto.declarations.profileInfo


interface IProfileInfoResponseDto {

    val `data`: IProfileInfoDto?

    val error: Boolean?

    val message: String?

}//end IProfileInfoResponseDto