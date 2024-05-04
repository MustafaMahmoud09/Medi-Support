package com.example.online.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDtoToOnlineDoctorModelMapper
import com.example.online.booking.domain.model.OnlineDoctorModel
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.online.booking.domain.usecase.declarations.IGetTotalOnlineDoctorsUseCase
import kotlinx.coroutines.flow.collect
import java.util.LinkedList

class GetTotalOnlineDoctorsUseCase(
    private val onlineBookingRepository: IOnlineBookingRepository,
    private val onlineDoctorDtoToOnlineDoctorModelMapper: IOnlineDoctorDtoToOnlineDoctorModelMapper
) : IGetTotalOnlineDoctorsUseCase {

    //function for make request on repository for get page contain on 10 doctors
    override suspend fun invoke(
        page: Int
    ): UnEffectResponse<List<OnlineDoctorModel>> {

        val onlineDoctorResult = LinkedList<OnlineDoctorModel>()
        var lastPage = 0

        while (true) {

            var breakCondition = false

            //make request on repository for get data
            //collect result flow
            //in success status map data to model and kill collect
            onlineBookingRepository.getPageOnlineDoctor(
                page = page
            ).collect { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get offline doctors
                            val onlineDoctorsDto = status.toData()?.body?.data?.data

                            //map offline doctors form dto to model
                            val onlineDoctorModels =
                                onlineDoctorDtoToOnlineDoctorModelMapper.listConvertor(
                                    list = onlineDoctorsDto ?: emptyList()
                                )

                            //add offline doctors model list to result list
                            onlineDoctorResult.addAll(onlineDoctorModels)
                            lastPage = status.toData()?.body?.data?.pagination?.lastPage ?: 0
                            breakCondition = true
                        }//end if

                        return@collect

                    }//end success case

                    is Status.Loading -> {

                    }//end loading case

                    is Status.Error -> {

                    }//end error case

                }//end when

            }//end collect

            if (breakCondition) {
                break
            }//end if

        }//end while

        //return result here
        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = onlineDoctorResult
        )

    }//end invoke

}//end GetTotalOfflineDoctorsUseCase