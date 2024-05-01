package com.example.blood.sugar.data.repository.execution.cacheHelperExecution

import android.util.Log
import com.example.blood.sugar.data.repository.execution.cacheHelperDeclarations.ICacheBloodSugarRepositoryHelper
import com.example.blood.sugar.data.repository.execution.cacheHelperDeclarations.IServerBloodSugarRepositoryHelper
import com.example.blood.sugar.data.source.remote.data.dto.execution.BloodSugarDto
import com.example.blood.sugar.data.source.remote.data.dto.execution.lastRecords.LatestBloodSugarResponseDto
import com.example.blood.sugar.data.source.remote.data.dto.execution.pageRecords.PageBloodSugarResponseDto
import com.example.blood.sugar.data.source.remote.data.requests.BloodSugarRequest
import com.example.blood.sugar.domain.dto.declarations.latestRecords.ILatestBloodSugarResponseDto
import com.example.blood.sugar.domain.dto.declarations.pageRecords.IPageBloodSugarResponseDto
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ServerBloodSugarRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val wrapper: ResponseWrapper,
    private val bloodSugarRequest: BloodSugarRequest,
    private val cacheBloodSugarRepositoryHelper: ICacheBloodSugarRepositoryHelper,
): IServerBloodSugarRepositoryHelper {

    override suspend fun getLastWeekBloodSugarRecordsFromServer(
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
                    wrapper.wrapper<ILatestBloodSugarResponseDto, LatestBloodSugarResponseDto> {
                        bloodSugarRequest.getLastSevenBloodSugarRecords()
                    }.collectLatest { status ->

                        when (status) {

                            //if status is success
                            //cache data in local database
                            is Status.Success -> {

                                //cache data if status code equal 200
                                if (status.toData()?.statusCode == 200) {

                                    if (
                                        status.toData()?.body?.data != null &&
                                        status.toData()?.body?.data!!.isNotEmpty()
                                    ) {
                                        cacheBloodSugarRepositoryHelper.cacheLatestBloodSugarRecords(
                                            bloodSugarRecords = status.toData()?.body?.data!! as List<BloodSugarDto>,
                                            userId = userAuthId
                                        )
                                    }//end if
                                    else {

                                        //execute delete all caching data here
                                        localDatabase.bloodSugarDao()
                                            .deleteBloodSugarRecordsFromId(
                                                userId = userAuthId,
                                                startId = 0
                                            )
                                    }//end else
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

    }//end getLastWeekBloodSugarRecordsFromServer


    override suspend fun getPageBloodSugarRecordsFromSever(
        userAuthId: Long,
        page: Int,
        pageSize: Int
    ): Int{

        var lastPage = 0

        try {
            //make request on server here
            //collect and handle response here
            wrapper.wrapper<IPageBloodSugarResponseDto, PageBloodSugarResponseDto> {
                bloodSugarRequest.getPageBloodSugarRecords(
                    page = page
                )
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {

                        //if status code equal 200
                        //process is success
                        if (status.toData()?.statusCode == 200) {
                            //make cache article in local database here
                            lastPage = cacheBloodSugarRepositoryHelper.cachePageBloodSugarRecords(
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

            //get last page number in local
            lastPage = cacheBloodSugarRepositoryHelper.getLocalPageCount(
                pageSize = pageSize
            )

        }//end if

        return lastPage

    }//end getPageHeartRateRecordsFromSever

}//end ServerBloodSugarRepositoryHelper