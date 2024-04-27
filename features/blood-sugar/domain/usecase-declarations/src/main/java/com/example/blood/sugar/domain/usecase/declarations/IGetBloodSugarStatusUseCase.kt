package com.example.blood.sugar.domain.usecase.declarations

import com.example.blood.sugar.domain.model.StatusModel
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IGetBloodSugarStatusUseCase {

    suspend operator fun invoke(): Flow<Status<EffectResponse<List<StatusModel>>>>

}//end IGetBloodSugarStatusUseCase