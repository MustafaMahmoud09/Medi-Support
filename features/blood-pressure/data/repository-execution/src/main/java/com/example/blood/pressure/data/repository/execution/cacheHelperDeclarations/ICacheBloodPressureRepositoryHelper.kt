package com.example.blood.pressure.data.repository.execution.cacheHelperDeclarations

import com.example.blood.pressure.data.source.remote.data.dto.execution.BloodPressureDto
import com.example.blood.pressure.domain.dto.declarations.pageMeasurement.IPageBloodPressureResponseDto

interface ICacheBloodPressureRepositoryHelper {

    suspend fun cacheLatestBloodPressureRecords(
        bloodPressureRecords: List<BloodPressureDto>,
        userId: Long
    )


    suspend fun cacheBloodPressureRecords(
        records: IPageBloodPressureResponseDto?,
        userId: Long,
        pageSize: Int
    ): Int


    suspend fun getLocalPageCount(
        pageSize: Int,
        userId: Long
    ): Int

}//end ICacheBloodPressureRepositoryHelper