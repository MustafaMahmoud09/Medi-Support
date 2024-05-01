package com.example.heartrate.data.repository.cacheHelperDeclarations

interface IServerHeartRateRepositoryHelper {

    suspend fun getLastWeekHeartRateRecordsFromServer(
        userAuthId: Long
    )


    suspend fun getPageHeartRateRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int

}//end IServerHeartRateRepositoryHelper