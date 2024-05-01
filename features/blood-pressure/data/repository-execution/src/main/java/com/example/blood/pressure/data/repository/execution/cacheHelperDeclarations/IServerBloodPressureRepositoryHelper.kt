package com.example.blood.pressure.data.repository.execution.cacheHelperDeclarations

interface IServerBloodPressureRepositoryHelper {

    suspend fun getLastBloodPressureRecordsFromServer(
        userAuthId: Long
    )


    suspend fun getPageBloodPressureRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int

}//end IServerBloodPressureRepositoryHelper