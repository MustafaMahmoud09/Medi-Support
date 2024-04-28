package com.example.heartrate.domain.usecase

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToAdviceHeartRateModelMapper
import com.example.heart.rate.domain.domain.model.AdviceHeartRateModel
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.IGetLatestHeartRateMeasurementUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetLatestHeartRateMeasurementUseCase(
    private val heartRateRepository: IHeartRateRepository,
    private val heartRateEntityToAdviceHeartRateModelMapper: IHeartRateEntityToAdviceHeartRateModelMapper
) : IGetLatestHeartRateMeasurementUseCase {

    //function for make request on repository for get last blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<AdviceHeartRateModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            heartRateRepository.getLatestMeasurements(
                numberOfMeasurement = 1
            ).collect { bloodSugarEntity ->

                //map data from entity to chart model here
                val heartRateModels = heartRateEntityToAdviceHeartRateModelMapper.listConvertor(
                    list = bloodSugarEntity
                )

                //emit data after mapping here
                trySend(heartRateModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLatestBloodSugarMeasurementUseCase