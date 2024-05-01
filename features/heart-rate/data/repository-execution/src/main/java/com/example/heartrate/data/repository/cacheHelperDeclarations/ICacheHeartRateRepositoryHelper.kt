package com.example.heartrate.data.repository.cacheHelperDeclarations

import com.example.heart.rate.data.source.dto.execution.HeartRateDto
import com.example.heart.rate.data.source.dto.execution.pageRecords.IPageHeartRateResponseDto

interface ICacheHeartRateRepositoryHelper {


    suspend fun cacheLatestHeartRateRecords(
        heartRateRecords: List<HeartRateDto>,
        userId: Long
    )


    suspend fun cachePageHeartRateRecords(
        records: IPageHeartRateResponseDto?,
        pageSize: Int,
        userId: Long
    ): Int


    suspend fun getLocalPageCount(
        pageSize: Int
    ): Int

}//end ICacheHeartRateRepositoryHelper