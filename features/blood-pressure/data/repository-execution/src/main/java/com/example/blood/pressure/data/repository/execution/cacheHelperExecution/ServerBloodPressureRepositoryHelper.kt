package com.example.blood.pressure.data.repository.execution.cacheHelperExecution

import android.util.Log
import com.example.blood.pressure.data.repository.execution.cacheHelperDeclarations.ICacheBloodPressureRepositoryHelper
import com.example.blood.pressure.data.repository.execution.cacheHelperDeclarations.IServerBloodPressureRepositoryHelper
import com.example.blood.pressure.data.source.remote.data.dto.execution.BloodPressureDto
import com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement.LatestBloodPressureResponseDto
import com.example.blood.pressure.data.source.remote.data.dto.execution.pageMeasurement.PageBloodPressureResponseDto
import com.example.blood.pressure.data.source.remote.data.requests.BloodPressureRequest
import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.ILatestBloodPressureResponseDto
import com.example.blood.pressure.domain.dto.declarations.pageMeasurement.IPageBloodPressureResponseDto
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ServerBloodPressureRepositoryHelper(
    private val bloodPressureRequest: BloodPressureRequest,
    private val wrapper: ResponseWrapper,
    private val cacheBloodPressureRepositoryHelper: ICacheBloodPressureRepositoryHelper,
): IServerBloodPressureRepositoryHelper{

    //make function for get last blood pressure records from server
    override suspend fun getLastBloodPressureRecordsFromServer(
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
                    wrapper.wrapper<ILatestBloodPressureResponseDto, LatestBloodPressureResponseDto> {
                        bloodPressureRequest.getLatestHistoryMeasurements()
                    }.collectLatest { status ->

                        when (status) {

                            //if status is success
                            //cache data in local database
                            is Status.Success -> {

                                //cache data if status code equal 200
                                if (status.toData()?.statusCode == 200) {

                                    //make cache to data here
                                    cacheBloodPressureRepositoryHelper.cacheLatestBloodPressureRecords(
                                        bloodPressureRecords = status.toData()?.body?.data!! as List<BloodPressureDto>,
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

    }//end getLastBloodPressureRecordsFromServer


    //function for get page contain on heart rate records from server
    //after that cache data in local database
    override suspend fun getPageBloodPressureRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int {

        var lastPage = 0

        try {
            //make request on server here
            //collect and handle response here
            wrapper.wrapper<IPageBloodPressureResponseDto, PageBloodPressureResponseDto> {
                bloodPressureRequest.getPageHistoryMeasurements(
                    page = page
                )
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {

                        //if status code equal 200
                        //process is success
                        if (status.toData()?.statusCode == 200) {
                            //make cache article in local database here
                            lastPage = cacheBloodPressureRepositoryHelper.cacheBloodPressureRecords(
                                records = status.toData()!!.body,
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

            //get last page number in local here
            lastPage = cacheBloodPressureRepositoryHelper.getLocalPageCount(
                pageSize = pageSize
            )

        }//end if

        return lastPage

    }//end getPageBloodPressureRecordsFromSever


}//end ServerBloodPressureRepositoryHelper