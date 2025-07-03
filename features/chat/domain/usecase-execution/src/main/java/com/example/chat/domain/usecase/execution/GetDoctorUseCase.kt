package com.example.chat.domain.usecase.execution

import com.example.chat.domain.dto.declarations.doctorInfo.IFetch
import com.example.chat.domain.mapper.declarations.child.IDoctorDtoToDoctorModelMapper
import com.example.chat.domain.model.DoctorModel
import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.IGetDoctorUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalCoroutinesApi::class)
class GetDoctorUseCase(
    private val chatRepository: IChatRepository,
    private val doctorDtoToDoctorModelMapper: IDoctorDtoToDoctorModelMapper
): IGetDoctorUseCase {

    override suspend operator fun invoke(
        doctorId: Int
    ): Flow<Status<EffectResponse<DoctorModel>>>{

        //return flow contain on response status
        return channelFlow<Status<EffectResponse<DoctorModel>>> {

            //make request on repository for get top doctors
            chatRepository.getDoctorById(
                doctorId = doctorId
            ).collect { status ->

                when (status) {

                    //if status is success
                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get top doctors dto
                            val doctorDetails = status.toData()?.body?.fetch

                            //map data from dto to model here
                            val doctorModel =
                                doctorDtoToDoctorModelMapper.objectConvertor(
                                    obj = doctorDetails as IFetch
                                )

                            //send top doctor models here
                            trySend(
                                element = Status.Success(
                                    data = EffectResponse(
                                        statusCode = status.toData()?.statusCode!!,
                                        body = doctorModel
                                    )
                                )
                            )

                        }//end if
                        else {

                            //return empty list with error status
                            trySend(
                                element = Status.Success(
                                    data = EffectResponse(
                                        statusCode = status.toData()?.statusCode!!,
                                        body = null
                                    )
                                )
                            )

                        }//end else

                    }//end success case

                    //if status is loading
                    is Status.Loading -> {
                        trySend(status)
                    }//end loading case

                    //if status is error
                    is Status.Error -> {
                        trySend(status)
                    }//end error case

                }//end when

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end IGetChatsUseCase