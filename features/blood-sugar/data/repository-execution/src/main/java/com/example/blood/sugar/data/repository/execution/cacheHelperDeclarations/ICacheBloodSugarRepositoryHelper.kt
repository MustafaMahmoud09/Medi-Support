package com.example.blood.sugar.data.repository.execution.cacheHelperDeclarations

import com.example.blood.sugar.data.source.remote.data.dto.execution.BloodSugarDto
import com.example.blood.sugar.domain.dto.declarations.pageRecords.IPageBloodSugarResponseDto

interface ICacheBloodSugarRepositoryHelper {

    suspend fun cacheLatestBloodSugarRecords(
        bloodSugarRecords: List<BloodSugarDto>,
        userId: Long
    )


    suspend fun cachePageBloodSugarRecords(
        records: IPageBloodSugarResponseDto?,
        pageSize: Int,
        userId: Long
    ): Int


    suspend fun getLocalPageCount(
        pageSize: Int,
        userId: Long
    ): Int

}//end ICacheBloodSugarRepositoryHelper