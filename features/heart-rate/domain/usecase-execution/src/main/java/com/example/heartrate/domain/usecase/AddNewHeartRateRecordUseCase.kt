package com.example.heartrate.domain.usecase

import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.IAddNewHeartRateRecordUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class AddNewHeartRateRecordUseCase(
    private val heartRateRepository: IHeartRateRepository
) : IAddNewHeartRateRecordUseCase {

    //function for create new blood sugar record
    override suspend fun invoke(
        rate: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return heartRateRepository.createNewHeartRateRecord(
            rate = rate
        )

    }//end invoke

}//end AddNewBloodSugarRecordUseCase