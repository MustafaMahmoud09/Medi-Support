package com.example.online.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.dto.declarations.doctorDetails.IOnlineDoctorDetailsDto
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
import com.example.online.booking.domain.model.OnlineDoctorDetailsModel
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.online.booking.domain.usecase.declarations.IGetOnlineDoctorDetailsByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetOnlineDoctorDetailsByIdUseCase(
    private val onlineBookingRepository: IOnlineBookingRepository,
    private val onlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper: IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
) : IGetOnlineDoctorDetailsByIdUseCase {

    //function for make request on repository for get doctor details
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(
        doctorId: Long
    ): Flow<Status<EffectResponse<OnlineDoctorDetailsModel>>> {

        //return flow contain on response status
        return channelFlow<Status<EffectResponse<OnlineDoctorDetailsModel>>> {

            //make request on repository for get top doctors
            onlineBookingRepository.getDoctorDetails(
                doctorId = doctorId
            ).collect { status ->

                when (status) {

                    //if status is success
                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get top doctors dto
                            val doctorDetails = status.toData()?.body?.data

                            //map data from dto to model here
                            val doctorModel =
                                onlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper.objectConvertor(
                                    obj = doctorDetails as IOnlineDoctorDetailsDto
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

}//end GetOfflineDoctorDetailsByIdUseCase