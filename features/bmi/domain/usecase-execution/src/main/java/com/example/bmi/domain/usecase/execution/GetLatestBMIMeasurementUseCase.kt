package com.example.bmi.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToAdviceBMIModelMapper
import com.example.bmi.domain.model.AdviceBMIModel
import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.bmi.domain.usecase.declarations.IGetLatestBMIMeasurementUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetLatestHeartRateMeasurementUseCase(
    private val bmiRepository: IBMIRepository,
    private val bmiEntityToAdviceBMIModelMapper: IBMIEntityToAdviceBMIModelMapper
) : IGetLatestBMIMeasurementUseCase {

    //function for make request on repository for get last blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<AdviceBMIModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            bmiRepository.getLastMeasurement().collect { bloodSugarEntity ->

                //map data from entity to chart model here
                val bmiModels = bmiEntityToAdviceBMIModelMapper.listConvertor(
                    list = bloodSugarEntity
                )

                //emit data after mapping here
                trySend(bmiModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLatestBloodSugarMeasurementUseCase