package com.example.blood.sugar.data.repository.execution.cacheHelperDeclarations

interface IServerBloodSugarRepositoryHelper {

    suspend fun getLastWeekBloodSugarRecordsFromServer(
        userAuthId: Long
    )


    suspend fun getPageBloodSugarRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int

}//end IServerBloodSugarRepositoryHelper