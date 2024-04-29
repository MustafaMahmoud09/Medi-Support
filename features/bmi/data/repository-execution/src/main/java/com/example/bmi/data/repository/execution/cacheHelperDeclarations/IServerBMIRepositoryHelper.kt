package com.example.bmi.data.repository.execution.cacheHelperDeclarations

interface IServerBMIRepositoryHelper {

    suspend fun getLastWeekBMIRecordsFromServer(
        userAuthId: Long
    )


    suspend fun getPageBMIRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int


}//end IServerBMIRepositoryHelper