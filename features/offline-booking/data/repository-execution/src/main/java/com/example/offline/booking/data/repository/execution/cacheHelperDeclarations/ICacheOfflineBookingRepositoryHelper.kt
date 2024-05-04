package com.example.online.booking.data.repository.execution.cacheHelperDeclarations

import com.example.online.booking.domain.dto.declarations.bookingDetails.IBookingDetailsResponseDto

interface ICacheOnlineBookingRepositoryHelper {

    suspend fun cachePageOnlineBookingRecords(
        records: IBookingDetailsResponseDto,
        pageSize: Int,
        userId: Long
    ): Int


    suspend fun getLocalPageCount(
        pageSize: Int,
        userId: Long
    ): Int

}//end ICacheBloodSugarRepositoryHelper