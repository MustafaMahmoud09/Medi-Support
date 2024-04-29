package com.example.bmi.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IAddNewBMIRecordUseCase {

    suspend operator fun invoke(
        gender: Int,
        age: Int,
        height: Float,
        weight: Float
    ): Flow<Status<EffectResponse<Any>>>

}//end IAddNewBMIRecordUseCase