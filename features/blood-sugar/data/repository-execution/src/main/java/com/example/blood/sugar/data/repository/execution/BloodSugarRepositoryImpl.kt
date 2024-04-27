package com.example.blood.sugar.data.repository.execution

import android.util.Log
import com.example.blood.sugar.data.source.remote.data.dto.execution.BloodSugarDto
import com.example.blood.sugar.data.source.remote.data.dto.execution.lastRecords.LatestBloodSugarResponseDto
import com.example.blood.sugar.data.source.remote.data.dto.execution.pageRecords.PageBloodSugarResponseDto
import com.example.blood.sugar.data.source.remote.data.dto.execution.status.BloodSugarStatusResponseDto
import com.example.blood.sugar.data.source.remote.data.requests.BloodSugarRequest
import com.example.blood.sugar.domain.dto.declarations.latestRecords.ILatestBloodSugarResponseDto
import com.example.blood.sugar.domain.dto.declarations.pageRecords.IPageBloodSugarResponseDto
import com.example.blood.sugar.domain.dto.declarations.status.IBloodSugarStatusResponseDto
import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BloodSugarRepositoryImpl(
    private val bloodSugarRequest: BloodSugarRequest,
    private val wrapper: ResponseWrapper,
    private val bloodSugarRepositoryHelper: BloodSugarRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject
) : IBloodSugarRepository {

    //function for make request on server for create new blood sugar record
    override suspend fun createNewBloodSugarRecord(
        level: Float,
        statusId: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            bloodSugarRequest.createBloodPressureRecord(
                level = level,
                statusId = statusId
            )
        }//end wrapper

    }//end createNewBloodSugarRecord


    //function for make request on server for get blood sugar status
    override suspend fun getBloodSugarStatus()
            : Flow<Status<EffectResponse<IBloodSugarStatusResponseDto>>> {

        //return flow with status function result contain on diastolic measurements
        return wrapper.infiniteWrapper<IBloodSugarStatusResponseDto, BloodSugarStatusResponseDto> {
            bloodSugarRequest.getBloodSugarStatus()
        }//end wrapper

    }//end getBloodSugarStatus


    //function for make request for get latest seven records from server
    //after that cache data in local database
    override suspend fun getLastWeekMeasurement(): Flow<List<IBloodSugarEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

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
                                        bloodSugarRepositoryHelper.cacheLatestBloodSugarRecords(
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

        return localDatabase.bloodSugarDao().getLatestBloodSugarRecord(
            userId = userAuthId,
            limit = 7
        )

    }//end getLastWeekMeasurement


    //function for provide number of latest blood sugar record
    override suspend fun getLatestMeasurements(
        numberOfMeasurement: Long
    ): Flow<List<IBloodSugarEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id


        return localDatabase.bloodSugarDao().getLatestBloodSugarRecord(
            userId = userAuthId,
            limit = numberOfMeasurement
        )

    }//end getLatestMeasurements


    //function for make request on server for get page contain on records
    override suspend fun getPageMeasurements(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IBloodSugarEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

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
                            lastPage = bloodSugarRepositoryHelper.cachePageBloodSugarRecords(
                                records = status.toData()!!.body,
                                userId = userAuthId
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
            //get article size
            val articleSize = localDatabase.bloodSugarDao().selectBloodSugarCount()

            lastPage =
                if ((articleSize.toFloat() / pageSize.toFloat()) - (articleSize / pageSize) != 0f) {
                    (articleSize / pageSize) + 1
                }//end if
                else {
                    (articleSize / pageSize)
                }.toInt()//end else
        }//end if

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.bloodSugarDao().selectPageBloodSugar(
                page = page,
                pageSize = pageSize,
                userId = userAuthId
            )
        )

    }//end getPageMeasurements

}//end BloodSugarRepositoryImpl