package com.example.heartrate.domain.usecase

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToSimpleHeartRateModelMapper
import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetLastHistoryRecordsUseCase(
    private val heartRateRepository: IHeartRateRepository,
    private val heartRateEntityToSimpleHeartRateModelMapper: IHeartRateEntityToSimpleHeartRateModelMapper
) : IGetLastHistoryRecordsUseCase {

    //function for make request on repository for provide latest history blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<SimpleHeartRateModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            heartRateRepository.getLatestMeasurements(
                numberOfMeasurement = 3
            ).collect { heartRate ->

                //map data from entity to chart model here
                val heartRateModels = heartRateEntityToSimpleHeartRateModelMapper.listConvertor(
                    list = heartRate
                )

                //emit data after mapping here
                trySend(heartRateModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLastHistoryRecordsUseCase