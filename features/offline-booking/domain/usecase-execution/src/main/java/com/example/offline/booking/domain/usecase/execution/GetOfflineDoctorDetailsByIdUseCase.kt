package com.example.offline.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.dto.declarations.doctorDetails.IDoctorDetailsDto
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
import com.example.offline.booking.domain.model.OfflineDoctorDetailsModel
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.offline.booking.domain.usecase.declarations.IGetOfflineDoctorDetailsByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetOfflineDoctorDetailsByIdUseCase(
    private val offlineBookingRepository: IOfflineBookingRepository,
    private val offlineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper: IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
) : IGetOfflineDoctorDetailsByIdUseCase {

    //function for make request on repository for get doctor details
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(
        doctorId: Long
    ): Flow<Status<EffectResponse<OfflineDoctorDetailsModel>>> {

        //return flow contain on response status
        return channelFlow<Status<EffectResponse<OfflineDoctorDetailsModel>>> {

            //make request on repository for get top doctors
            offlineBookingRepository.getDoctorDetails(
                doctorId = doctorId
            ).collect { status ->

                when (status) {

                    //if status is success
                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get top doctors dto
                            val doctorDetails = status.toData()?.body?.doctorDetailsDto

                            //map data from dto to model here
                            val topDoctorModels =
                                offlineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper.objectConvertor(
                                    obj = doctorDetails as IDoctorDetailsDto
                                )

                            //send top doctor models here
                            trySend(
                                element = Status.Success(
                                    data = EffectResponse(
                                        statusCode = status.toData()?.statusCode!!,
                                        body = topDoctorModels
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