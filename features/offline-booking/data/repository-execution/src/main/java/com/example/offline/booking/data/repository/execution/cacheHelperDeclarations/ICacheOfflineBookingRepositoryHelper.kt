package com.example.offline.booking.data.repository.execution.cacheHelperDeclarations

import com.example.offline.booking.domain.dto.declarations.bookingDetails.IBookingDetailResponseDto

interface ICacheOfflineBookingRepositoryHelper {

    suspend fun cachePageOfflineBookingRecords(
        records: IBookingDetailResponseDto,
        pageSize: Int,
        userId: Long
    ): Int


    suspend fun getLocalPageCount(
        pageSize: Int,
        userId: Long
    ): Int

}//end ICacheOfflineBookingRepositoryHelper