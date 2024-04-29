package com.example.bmi.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToChartBMIModelMapper
import com.example.bmi.domain.model.ChartBMIModel
import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.bmi.domain.usecase.declarations.IGetLastWeekBMIRecordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import java.util.LinkedList

class GetLastWeekBMIRecordsUseCase(
    private val bmiRepository: IBMIRepository,
    private val bmiEntityToChartBMIModelMapper: IBMIEntityToChartBMIModelMapper
) : IGetLastWeekBMIRecordsUseCase {


    //function for provide last week blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<ChartBMIModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            bmiRepository.getLatestMeasurements(
                numberOfMeasurement = 7
            ).collect { bloodSugarEntity ->

                //map data from entity to chart model here
                var bmiModels = bmiEntityToChartBMIModelMapper.listConvertor(
                    list = bloodSugarEntity.reversed()
                )

                if (bmiModels.size < 7 && bmiModels.isNotEmpty()) {

                    //create temp
                    val bmiModelsTemp = LinkedList(bmiModels)

                    //for loop on empty position for full with temp data
                    for (count in 0 until 7 - bmiModels.size) {

                        bmiModelsTemp.add(
                            ChartBMIModel(
                                result = (0).toDouble(),
                                dayName = ""
                            )
                        )

                    }//end for

                    bmiModels = bmiModelsTemp

                }//end if

                //emit data after mapping here
                trySend(bmiModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLastWeekBloodSugarRecordsUseCase