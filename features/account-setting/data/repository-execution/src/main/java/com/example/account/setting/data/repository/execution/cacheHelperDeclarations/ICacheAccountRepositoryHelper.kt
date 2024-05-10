package com.example.account.setting.data.repository.execution.cacheHelperDeclarations

import com.example.account.setting.domain.dto.declarations.profileInfo.IProfileInfoResponseDto

interface ICacheAccountRepositoryHelper {

    suspend fun cacheUserAccountInfo(
        accountInfo: IProfileInfoResponseDto,
        accessToken: String
    )


    suspend fun cacheUpdateAccountInfo(
        lastName: String?,
        firstName: String?,
        accessToken: String
    )

}//end ICacheAccountRepositoryHelper