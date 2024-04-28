package com.example.blood.sugar.domain.usecase.execution

import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.IAddNewBloodSugarRecordUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class AddNewBloodSugarRecordUseCase(
    private val bloodSugarRepository: IBloodSugarRepository
) : IAddNewBloodSugarRecordUseCase {

    //function for create new blood sugar record
    override suspend fun invoke(
        level: Float,
        statusId: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return bloodSugarRepository.createNewBloodSugarRecord(
            level = level,
            statusId = statusId
        )

    }//end invoke

}//end AddNewBloodSugarRecordUseCase