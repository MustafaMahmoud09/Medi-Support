package com.example.offline.booking.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IOfflineDoctorDtoToOfflineDoctorModelMapper
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.offline.booking.domain.model.OfflineDoctorModel
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.offline.booking.domain.usecase.declarations.IGetTotalOfflineDoctorsUseCase
import kotlinx.coroutines.flow.collect
import java.util.LinkedList

class GetTotalOfflineDoctorsUseCase(
    private val offlineBookingRepository: IOfflineBookingRepository,
    private val offlineDoctorDtoToOfflineDoctorModelMapper: IOfflineDoctorDtoToOfflineDoctorModelMapper
) : IGetTotalOfflineDoctorsUseCase {

    //function for make request on repository for get page contain on 10 doctors
    override suspend fun invoke(
        page: Int
    ): UnEffectResponse<List<OfflineDoctorModel>> {

        val offlineDoctorResult = LinkedList<OfflineDoctorModel>()
        var lastPage = 0

        //make request on repository for get data
        //collect result flow
        //in success status map data to model and kill collect
        offlineBookingRepository.getPageOfflineDoctor(
            page = page
        ).collect { status ->

            when (status) {

                is Status.Success -> {

                    if (status.toData()?.statusCode == 200) {

                        //get offline doctors
                        val offlineDoctorsDto = status.toData()?.body?.data?.data

                        //map offline doctors form dto to model
                        val offlineDoctorModels =
                            offlineDoctorDtoToOfflineDoctorModelMapper.listConvertor(
                                list = offlineDoctorsDto ?: emptyList()
                            )

                        //add offline doctors model list to result list
                        offlineDoctorResult.addAll(offlineDoctorModels)
                        lastPage = status.toData()?.body?.data?.lastPage ?: 0
                        return@collect
                    }//end if

                }//end success case

                is Status.Loading -> {

                }//end loading case

                is Status.Error -> {

                }//end error case

            }//end when

        }//end collect

        //return result here
        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = offlineDoctorResult
        )

    }//end invoke

}//end GetTotalOfflineDoctorsUseCase