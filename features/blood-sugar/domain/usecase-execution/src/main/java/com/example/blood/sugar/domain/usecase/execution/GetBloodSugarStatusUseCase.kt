package com.example.blood.sugar.domain.usecase.execution

import com.example.blood.sugar.domain.dto.declarations.status.IBloodSugarStatusDto
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodPressureStatusDtoToStatusModelMapper
import com.example.blood.sugar.domain.model.StatusModel
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.IGetBloodSugarStatusUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

class GetBloodSugarStatusUseCase(
    private val bloodSugarRepository: IBloodSugarRepository,
    private val bloodPressureStatusDtoToStatusModelMapper: IBloodPressureStatusDtoToStatusModelMapper
) : IGetBloodSugarStatusUseCase {

    //function for provide blood sugar status
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke()
            : Flow<Status<EffectResponse<List<StatusModel>>>> {

        //return stream of data contain on status models
        return channelFlow<Status<EffectResponse<List<StatusModel>>>> {

            //make request for get status
            //collect result for collect success data
            //map success data to status model
            //after that emit status models in chanel flow
            bloodSugarRepository.getBloodSugarStatus().collectLatest { status ->

                when (status) {

                    is Status.Success -> {

                        //if status code equal 200
                        //map data to status model
                        //emit data after mapping
                        if (status.toData()?.statusCode == 200) {

                            val statusModels =
                                bloodPressureStatusDtoToStatusModelMapper.listConvertor(
                                    list = status.toData()?.body?.data as List<IBloodSugarStatusDto>
                                )

                            //emit success with status models
                            trySend(
                                element = Status.Success(
                                    EffectResponse(
                                        statusCode = status.toData()!!.statusCode,
                                        body = statusModels
                                    )
                                )
                            )

                        }//end if
                        else {

                            //emit empty list with status code not equal 200
                            send(
                                Status.Success(
                                    EffectResponse(
                                        statusCode = status.toData()!!.statusCode,
                                        body = emptyList()
                                    )
                                )
                            )
                        }//end else
                    }//end success case

                    is Status.Error -> {

                        //emit error status
                        trySend(status)
                    }//end error case

                    is Status.Loading -> {

                        //emit loading status
                        trySend(status)
                    }//end loading case

                }//end when

            }//end collectLatest

        }.flowOn(Dispatchers.IO)

    }

}//end GetBloodSugarStatusUseCase