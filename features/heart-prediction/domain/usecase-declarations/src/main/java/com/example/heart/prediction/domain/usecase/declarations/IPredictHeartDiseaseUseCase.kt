package com.example.heart.prediction.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IPredictHeartDiseaseUseCase {

    suspend operator fun invoke(
        bmi: Float,
        physicalHealth: Float,
        mentalHealth: Float,
        sleepTime: Float,
        ageCategory: Int,
        race: Int,
        diabetic: Int,
        genHealth: Int,
        sex: Int,
        smoking: Int,
        alcoholDrinking: Int,
        stroke: Int,
        diffWalking: Int,
        physicalActivity: Int,
        asthma: Int,
        kidneyDisease: Int,
        skinCancer: Int
    ): Flow<Status<EffectResponse<Int>>>

}//end IPredictHeartDiseaseUseCase