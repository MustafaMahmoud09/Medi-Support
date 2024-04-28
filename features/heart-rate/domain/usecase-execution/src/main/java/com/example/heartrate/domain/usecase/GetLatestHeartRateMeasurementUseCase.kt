package com.example.blood.sugar.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToAdviceBloodSugarModelMapper
import com.example.blood.sugar.domain.model.AdviceBloodSugarModel
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.IGetLatestBloodSugarMeasurementUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetLatestBloodSugarMeasurementUseCase(
    private val bloodSugarRepository: IBloodSugarRepository,
    private val bloodSugarEntityToAdviceBloodSugarModelMapper: IBloodSugarEntityToAdviceBloodSugarModelMapper
) : IGetLatestBloodSugarMeasurementUseCase {

    //function for make request on repository for get last blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<AdviceBloodSugarModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            bloodSugarRepository.getLatestMeasurements(
                numberOfMeasurement = 1
            ).collect { bloodSugarEntity ->

                //map data from entity to chart model here
                val bloodSugarModels = bloodSugarEntityToAdviceBloodSugarModelMapper.listConvertor(
                    list = bloodSugarEntity
                )

                //emit data after mapping here
                trySend(bloodSugarModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLatestBloodSugarMeasurementUseCase