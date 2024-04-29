package com.example.bmi.domain.usecase.execution

import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.bmi.domain.usecase.declarations.IAddNewBMIRecordUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class AddNewBMIRecordUseCase(
    private val bmiRepository: IBMIRepository
) : IAddNewBMIRecordUseCase {

    //function for create new bmi record
    override suspend fun invoke(
        gender: Int,
        age: Int,
        height: Float,
        weight: Float
    ): Flow<Status<EffectResponse<Any>>> {

        return bmiRepository.createNewBMIRecord(
            gender = gender,
            age = age,
            height = height,
            weight = weight
        )

    }//end invoke

}//end AddNewBMIRecordUseCase