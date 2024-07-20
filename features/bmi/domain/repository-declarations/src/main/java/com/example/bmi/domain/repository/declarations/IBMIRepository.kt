package com.example.bmi.domain.repository.declarations

import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.Flow

interface IBMIRepository {

    suspend fun createNewBMIRecord(
        gender: Int,
        age: Int,
        height: Float,
        weight: Float
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun getLastMeasurement()
            : Flow<List<IBMIEntity>>


    suspend fun getLatestMeasurements(
        numberOfMeasurement: Long
    ): Flow<List<IBMIEntity>>


    suspend fun getPageMeasurements(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IBMIEntity>>


    suspend fun logoutFromLocalDatabase()

}//end IBMIRepository