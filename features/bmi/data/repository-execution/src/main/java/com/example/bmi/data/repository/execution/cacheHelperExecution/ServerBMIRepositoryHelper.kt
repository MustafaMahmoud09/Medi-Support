package com.example.bmi.data.repository.execution.cacheHelperExecution

import android.util.Log
import com.example.bmi.data.repository.execution.cacheHelperDeclarations.ICacheBMIRepositoryHelper
import com.example.bmi.data.source.remote.data.dto.execution.BMIDto
import com.example.bmi.data.source.remote.data.dto.execution.lastRecords.LastBMIResponseDto
import com.example.bmi.data.source.remote.data.dto.execution.pageRecords.PageBMIResponseDto
import com.example.bmi.data.source.remote.data.requests.BMIRequest
import com.example.bmi.domain.dto.declarations.lastReocrds.ILastBMIResponseDto
import com.example.bmi.domain.dto.declarations.pageRecords.IPageBMIResponseDto
import com.example.bmi.data.repository.execution.cacheHelperDeclarations.IServerBMIRepositoryHelper
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ServerBMIRepositoryHelper(
    private val bmiRequest: BMIRequest,
    private val wrapper: ResponseWrapper,
    private val cacheBMIRepositoryHelper: ICacheBMIRepositoryHelper,
): IServerBMIRepositoryHelper {

    //function for make request on server for get last heart rate records
    //after that cache data in local data base
    override suspend fun getLastWeekBMIRecordsFromServer(
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
                    wrapper.wrapper<ILastBMIResponseDto, LastBMIResponseDto> {
                        bmiRequest.getLastSevenBMIRecords()
                    }.collectLatest { status ->

                        when (status) {

                            //if status is success
                            //cache data in local database
                            is Status.Success -> {

                                //cache data if status code equal 200
                                if (status.toData()?.statusCode == 200) {

                                    cacheBMIRepositoryHelper.cacheLatestBMIRecords(
                                        bmiRecords = status.toData()?.body?.data!! as List<BMIDto>,
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
    override suspend fun getPageBMIRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int {

        var lastPage = 0

        try {
            //make request on server here
            //collect and handle response here
            wrapper.wrapper<IPageBMIResponseDto, PageBMIResponseDto> {
                bmiRequest.getPageBMIRecords(
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
                            lastPage = cacheBMIRepositoryHelper.cachePageBMIRecords(
                                records = status.toData()!!.body,
                                userId = userAuthId,
                                pageSize = pageSize
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

            lastPage = cacheBMIRepositoryHelper.getLocalPageCount(
                pageSize = pageSize
            )

        }//end if

        return lastPage

    }//end getPageHeartRateRecordsFromSever

}//end ServerHeartRateRepositoryHelper