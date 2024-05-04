package com.example.online.booking.data.repository.execution.cacheHelperExecution

import android.util.Log
import com.example.offline.booking.data.repository.execution.cacheHelperDeclarations.ICacheOfflineBookingRepositoryHelper
import com.example.offline.booking.data.repository.execution.cacheHelperDeclarations.IServerOfflineBookingRepositoryHelper
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.offline.bookin.data.source.remote.data.requests.OfflineBookingRequest
import com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails.BookingDetailResponseDto
import com.example.offline.booking.domain.dto.declarations.bookingDetails.IBookingDetailResponseDto
import kotlinx.coroutines.flow.collectLatest

class ServerOfflineBookingRepositoryHelper(
    private val wrapper: ResponseWrapper,
    private val offlineBookingRequest: OfflineBookingRequest,
    private val cacheOnlineBookingRepositoryHelper: ICacheOfflineBookingRepositoryHelper,
) : IServerOfflineBookingRepositoryHelper {


    override suspend fun getPageOfflineBookingRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int {

        var lastPage = 0

        try {
            //make request on server here
            //collect and handle response here
            wrapper.wrapper<IBookingDetailResponseDto, BookingDetailResponseDto> {
                offlineBookingRequest.getOfflineBookings(
                    page = page
                )
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {

                        //if status code equal 200
                        //process is success
                        if (status.toData()?.statusCode == 200) {
                            //make cache article in local database here
                            lastPage =
                                cacheOnlineBookingRepositoryHelper.cachePageOfflineBookingRecords(
                                    records = status.toData()?.body!!,
                                    userId = userAuthId,
                                    pageSize = pageSize
                                )
                        }//end if
                        return@collectLatest
                    }//end Success

                    is Status.Loading -> {

                    }//end Load

                    is Status.Error -> {
                        return@collectLatest
                    }//end Error
                }//end when

            }//end collectLatest

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("ERROR", it) }
        }


        //if last page equal 0 get last page number in local database
        if (lastPage == 0) {

            //get last page number in local
            lastPage = cacheOnlineBookingRepositoryHelper.getLocalPageCount(
                pageSize = pageSize,
                userId = userAuthId
            )

        }//end if

        return lastPage

    }//end getPageHeartRateRecordsFromSever

}//end ServerBloodSugarRepositoryHelper