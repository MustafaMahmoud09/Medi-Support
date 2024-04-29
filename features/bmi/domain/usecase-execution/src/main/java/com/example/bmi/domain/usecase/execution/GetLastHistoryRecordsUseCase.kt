package com.example.bmi.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToSimpleBMIModelMapper
import com.example.bmi.domain.model.SimpleBMIModel
import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.bmi.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetLastHistoryRecordsUseCase(
    private val bmiRepository: IBMIRepository,
    private val bmiEntityToSimpleBMIModelMapper: IBMIEntityToSimpleBMIModelMapper
) : IGetLastHistoryRecordsUseCase {

    //function for make request on repository for provide latest history blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<SimpleBMIModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            bmiRepository.getLatestMeasurements(
                numberOfMeasurement = 3
            ).collect { heartRate ->

                //map data from entity to chart model here
                val bmiModels = bmiEntityToSimpleBMIModelMapper.listConvertor(
                    list = heartRate
                )

                //emit data after mapping here
                trySend(bmiModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLastHistoryRecordsUseCase