package com.example.blood.pressure.domain.usecase.execution

import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
import com.example.blood.pressure.domain.usecase.declarations.IAddBloodPressureRecordUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class AddBloodPressureRecordUseCase(
    private val bloodPressureRepository: IBloodPressureRepository,
) : IAddBloodPressureRecordUseCase {

    //function for execute add blood pressure record case
    override suspend fun invoke(
        systolic: Int,
        diastolic: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return bloodPressureRepository.createNewBloodPressureRecord(
            systolic = systolic,
            diastolic = diastolic
        )

    }//end invoke

}//end AddBloodPressureRecordUseCase