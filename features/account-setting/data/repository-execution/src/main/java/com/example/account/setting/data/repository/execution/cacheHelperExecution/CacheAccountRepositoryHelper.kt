package com.example.account.setting.data.repository.execution.cacheHelperExecution

import com.example.account.setting.data.repository.execution.cacheHelperDeclarations.ICacheAccountRepositoryHelper
import com.example.account.setting.domain.dto.declarations.profileInfo.IProfileInfoResponseDto
import com.example.database_creator.MediSupportDatabase

class CacheAccountRepositoryHelper(
    private val localDatabase: MediSupportDatabase
) : ICacheAccountRepositoryHelper {

    //function for make request on local data base for update user info
    override suspend fun cacheUserAccountInfo(
        accountInfo: IProfileInfoResponseDto,
        accessToken: String
    ) {

        //update user auth by new profile info here
        localDatabase.userDao().updateAccountWhereToken(
            email = accountInfo.data?.email ?: "",
            lastName = accountInfo.data?.lastName ?: "",
            firstName = accountInfo.data?.firstName ?: "",
            avatar = accountInfo.data?.avatar ?: "",
            token = accessToken
        )

    }//end cacheUserAccountInfo


    //update user auth by new profile info here
    override suspend fun cacheUpdateAccountInfo(
        lastName: String?,
        firstName: String?,
        accessToken: String
    ) {


        if (lastName != null) {
            localDatabase.userDao().updateLastName(
                lastName = lastName,
                token = accessToken
            )
        }//end if

        if (firstName != null) {
            localDatabase.userDao().updateFirstName(
                firstName = firstName,
                token = accessToken
            )
        }//end if

    }//end cacheUpdateAccountInfo

}//end CacheAccountRepositoryHelper