package com.example.bmi.data.repository.execution.cacheHelperDeclarations

import com.example.bmi.data.source.remote.data.dto.execution.BMIDto
import com.example.bmi.domain.dto.declarations.pageRecords.IPageBMIResponseDto

interface ICacheBMIRepositoryHelper {

    suspend fun cacheLatestBMIRecords(
        bmiRecords: List<BMIDto>,
        userId: Long
    )


    suspend fun cachePageBMIRecords(
        records: IPageBMIResponseDto?,
        pageSize: Int,
        userId: Long
    ): Int


    suspend fun getLocalPageCount(
        pageSize: Int
    ): Int

}//end ICacheBMIRepositoryHelper