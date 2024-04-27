package com.example.blood.sugar.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IAddNewBloodSugarRecordUseCase {

    suspend operator fun invoke(
        level: Float,
        statusId: Int
    ): Flow<Status<EffectResponse<Any>>>

}//end IAddNewBloodSugarRecordUseCase