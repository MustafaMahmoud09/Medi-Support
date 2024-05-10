package com.example.account.setting.data.repository.execution.cacheHelperDeclarations

interface IServerAccountRepositoryHelper {

    suspend fun getAccountInfoServer(
        accessToken: String
    )

}//end IServerAccountRepositoryHelper