@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.heart.prediction.domain.usecase.execution

import com.example.heart.prediction.domain.repository.declarations.IHeartPredictionRepository
import com.example.heart.prediction.domain.usecase.declarations.IPredictHeartDiseaseUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class PredictHeartDiseaseUseCase(
    private val heartPredictionRepository: IHeartPredictionRepository
) : IPredictHeartDiseaseUseCase {

    //function for make request on repository for heart disease prediction
    override suspend fun invoke(
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
    ): Flow<Status<EffectResponse<Int>>> {

        return channelFlow<Status<EffectResponse<Int>>> {

            heartPredictionRepository.predictHeartDisease(
                bmi = bmi,
                physicalHealth = physicalHealth,
                mentalHealth = mentalHealth,
                sleepTime = sleepTime,
                ageCategory = ageCategory,
                race = race,
                diabetic = diabetic,
                genHealth = genHealth,
                sex = sex,
                smoking = smoking,
                alcoholDrinking = alcoholDrinking,
                stroke = stroke,
                diffWalking = diffWalking,
                physicalActivity = physicalActivity,
                asthma = asthma,
                kidneyDisease = kidneyDisease,
                skinCancer = skinCancer
            ).collect { status ->

                when (status) {

                    is Status.Success -> {
                        if (status.toData()?.statusCode == 200) {
                            trySend(
                                Status.Success(
                                    data = EffectResponse(
                                        statusCode = status.toData()?.statusCode ?: 0,
                                        body = status.toData()?.body?.prediction
                                    )
                                )
                            )
                        }//end if
                        else {
                            trySend(
                                Status.Success(
                                    data = EffectResponse(
                                        statusCode = status.toData()?.statusCode ?: 0,
                                        body = null
                                    )
                                )
                            )
                        }//end else
                    }//end success case

                    is Status.Error -> {
                        trySend(status)
                    }//end error case

                    is Status.Loading -> {
                        trySend(status)
                    }//end loading case

                }//end when

            }//end collect

        }.flowOn(Dispatchers.IO)//end channelFlow

    }//end invoke

}//end IPredictHeartDiseaseUseCase