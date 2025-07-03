package com.example.heart.rate.domain.repository.declarations

import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.Flow

interface IHeartRateRepository {

    fun isLightSensorExist(): Boolean


    fun isFlashCameraExist(): Boolean


    suspend fun createNewHeartRateRecord(
        rate: Int
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun getLastWeekMeasurement()
            : Flow<List<IHeartRateEntity>>


    suspend fun getLatestMeasurements(
        numberOfMeasurement: Long
    ): Flow<List<IHeartRateEntity>>


    suspend fun getPageMeasurements(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IHeartRateEntity>>


    suspend fun logoutFromLocalDatabase()

}//end IHeartRateRepository