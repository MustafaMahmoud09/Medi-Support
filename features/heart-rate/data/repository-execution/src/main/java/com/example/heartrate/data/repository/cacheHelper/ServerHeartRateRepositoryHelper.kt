package com.example.heartrate.data.repository.cacheHelper

import android.util.Log
import com.example.heart.rate.data.source.dto.execution.HeartRateDto
import com.example.heart.rate.data.source.dto.execution.lastRecords.ILastHeartRateResponseDto
import com.example.heart.rate.data.source.dto.execution.lastRecords.LastHeartRateResponseDto
import com.example.heart.rate.data.source.dto.execution.pageRecords.IPageHeartRateResponseDto
import com.example.heart.rate.data.source.dto.execution.pageRecords.PageHeartRateResponseDto
import com.example.heart.rate.data.source.remote.data.requests.HeartRateRequest
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ServerHeartRateRepositoryHelper(
    private val heartRateRequest: HeartRateRequest,
    private val wrapper: ResponseWrapper,
    private val cacheHeartRateRepositoryHelper: CacheHeartRateRepositoryHelper,
) {

    //function for make request on server for get last heart rate records
    //after that cache data in local data base
    suspend fun getLastWeekHeartRateRecordsFromServer(
        userAuthId: Long
    ) {

        //create coroutine builder for apply parallel processing
        CoroutineScope(Dispatchers.IO).launch {

            while (true) {

                var breakCondition = false

                try {

                    //make request on api for get latest history data
                    //collect result for check status of request
                    //if status is success finish process and cache data
                    wrapper.wrapper<ILastHeartRateResponseDto, LastHeartRateResponseDto> {
                        heartRateRequest.getLastSevenHeartRateRecords()
                    }.collectLatest { status ->

                        when (status) {

                            //if status is success
                            //cache data in local database
                            is Status.Success -> {

                                //cache data if status code equal 200
                                if (status.toData()?.statusCode == 200) {

                                    cacheHeartRateRepositoryHelper.cacheLatestHeartRateRecords(
                                        heartRateRecords = status.toData()?.body?.data!! as List<HeartRateDto>,
                                        userId = userAuthId
                                    )
                                    breakCondition = true
                                    return@collectLatest

                                }//end if

                            }//end success case

                            is Status.Loading -> {

                            }//end loading case

                            is Status.Error -> {
                                return@collectLatest
                            }//end error case

                        }//end when

                    }//end collectLatest

                }//end try
                catch (ex: Exception) {
                    ex.message?.let { Log.d("ERROR", it) }
                }

                //if data is cached and request is success
                //make break
                if (breakCondition) {
                    break
                }//end if

                //delay 1/4 second between request and second request
                delay(250)

            }//end while

        }//end coroutine builder scope

    }//end getLastWeekHeartRateRecordsFromServer


    //function for get page contain on heart rate records from server
    //after that cache data in local database
    suspend fun getPageHeartRateRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int {

        var lastPage = 0

        try {
            //make request on server here
            //collect and handle response here
            wrapper.wrapper<IPageHeartRateResponseDto, PageHeartRateResponseDto> {
                heartRateRequest.getPageHeartRateRecords(
                    page = page
                )
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {
                        Log.d("TAG", status.toData()?.statusCode.toString())
                        //if status code equal 200
                        //process is success
                        if (status.toData()?.statusCode == 200) {
                            //make cache article in local database here
                            lastPage = cacheHeartRateRepositoryHelper.cachePageBloodSugarRecords(
                                records = status.toData()!!.body,
                                userId = userAuthId
                            )
                        }//end if
                        return@collectLatest
                    }//end Success

                    is Status.Loading -> {
                        Log.d("TAG", "LOAD")
                    }//end Load

                    is Status.Error -> {
                        Log.d("TAG", "ERROR")
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

            lastPage = cacheHeartRateRepositoryHelper.getLocalPageCount(
                pageSize = pageSize
            )

        }//end if

        return lastPage

    }//end getPageHeartRateRecordsFromSever

}//end ServerHeartRateRepositoryHelper