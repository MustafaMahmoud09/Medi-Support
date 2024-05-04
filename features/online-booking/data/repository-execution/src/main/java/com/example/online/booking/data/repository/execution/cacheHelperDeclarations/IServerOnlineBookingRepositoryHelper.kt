package com.example.online.booking.data.repository.execution.cacheHelperDeclarations

interface IServerOnlineBookingRepositoryHelper {

    suspend fun getPageOnlineBookingRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int

}//end IServerBloodSugarRepositoryHelper