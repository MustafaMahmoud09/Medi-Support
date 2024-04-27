package com.example.blood.sugar.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToChartBloodSugarModelMapper
import com.example.blood.sugar.domain.model.ChartBloodSugarModel
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.IGetLastWeekBloodSugarRecordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import java.util.LinkedList

class GetLastWeekBloodSugarRecordsUseCase(
    private val bloodSugarRepository: IBloodSugarRepository,
    private val bloodSugarEntityToChartBloodSugarModelMapper: IBloodSugarEntityToChartBloodSugarModelMapper
) : IGetLastWeekBloodSugarRecordsUseCase {


    //function for provide last week blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<ChartBloodSugarModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            bloodSugarRepository.getLastWeekMeasurement().collect { bloodSugarEntity ->

                //map data from entity to chart model here
                var bloodSugarModels = bloodSugarEntityToChartBloodSugarModelMapper.listConvertor(
                    list = bloodSugarEntity.reversed()
                )

                if (bloodSugarModels.size < 7 && bloodSugarModels.isNotEmpty()) {

                    //create temp
                    val bloodSugarModelsTemp = LinkedList(bloodSugarModels)

                    //for loop on empty position for full with temp data
                    for (count in 0 until 7 - bloodSugarModels.size) {

                        bloodSugarModelsTemp.add(
                            ChartBloodSugarModel(
                                level = (0).toDouble(),
                                dayName = ""
                            )
                        )

                    }//end for

                    bloodSugarModels = bloodSugarModelsTemp

                }//end if

                //emit data after mapping here
                trySend(bloodSugarModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLastWeekBloodSugarRecordsUseCase