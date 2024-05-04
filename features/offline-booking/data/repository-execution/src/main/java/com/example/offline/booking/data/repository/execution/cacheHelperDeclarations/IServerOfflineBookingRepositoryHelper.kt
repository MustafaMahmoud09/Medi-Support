package com.example.offline.booking.data.repository.execution.cacheHelperDeclarations

interface IServerOfflineBookingRepositoryHelper {

    suspend fun getPageOfflineBookingRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int

}//end IServerOfflineBookingRepositoryHelper