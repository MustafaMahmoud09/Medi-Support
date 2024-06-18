package com.example.heart.prediction.domain.repository.declarations

import com.example.heart.prediction.domain.dto.declarations.IHeartPredictionDto
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IHeartPredictionRepository {

    suspend fun predictHeartDisease(
        bmi: Float,
        physicalHealth: Float,
        mentalHealth: Float,
        sleepTime: Float,
        ageCategory: Int,
        race: Int,
        diabetic: Int,
        sex: Int,
        smoking: Int,
        alcoholDrinking: Int,
        stroke: Int,
        diffWalking: Int,
        physicalActivity: Int,
        asthma: Int,
        kidneyDisease: Int,
        skinCancer: Int,
        genHealth: Int,
    ): Flow<Status<EffectResponse<IHeartPredictionDto>>>

}//end IHeartPredictionRepository