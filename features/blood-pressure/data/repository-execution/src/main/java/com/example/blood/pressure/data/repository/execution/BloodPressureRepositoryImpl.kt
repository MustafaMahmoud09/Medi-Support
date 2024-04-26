package com.example.blood.pressure.data.repository.execution

import android.util.Log
import com.example.blood.pressure.data.source.remote.data.dto.execution.descMeasurement.DescBloodPressureResponseDto
import com.example.blood.pressure.data.source.remote.data.dto.execution.BloodPressureDto
import com.example.blood.pressure.data.source.remote.data.dto.execution.adviceMeasurement.AdviceBloodPressureResponseDto
import com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement.LatestBloodPressureResponseDto
import com.example.blood.pressure.data.source.remote.data.dto.execution.pageMeasurement.PageBloodPressureResponseDto
import com.example.blood.pressure.data.source.remote.data.requests.BloodPressureRequest
import com.example.blood.pressure.domain.dto.declarations.descMeasurement.IDescBloodPressureResponseDto
import com.example.blood.pressure.domain.dto.declarations.adviceMeasurement.IAdviceBloodPressureResponseDto
import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.ILatestBloodPressureResponseDto
import com.example.blood.pressure.domain.dto.declarations.pageMeasurement.IPageBloodPressureResponseDto
import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException

class BloodPressureRepositoryImpl(
    private val bloodPressureRequest: BloodPressureRequest,
    private val wrapper: ResponseWrapper,
    private val bloodPressureRepositoryHelper: BloodPressureRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject
) : IBloodPressureRepository {

    //function for create new blood pressure record in global database
    override suspend fun createNewBloodPressureRecord(
        systolic: Int,
        diastolic: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            bloodPressureRequest.createNewBloodPressureRecord(
                systolic = systolic,
                diastolic = diastolic
            )
        }//end wrapper scope

    }//end createNewBloodPressureRecord


    //function for provide page blood pressure
    override suspend fun getPageBloodPressure(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IBloodPressureEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

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
                            lastPage = bloodPressureRepositoryHelper.cacheBloodPressureRecords(
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
            val articleSize = localDatabase.bloodPressureDao().selectBloodPressureCount()

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
            body = localDatabase.bloodPressureDao().selectPageBloodPressure(
                page = page,
                pageSize = pageSize,
                userId = userAuthId
            )
        )

    }//end getAllArticles


    //function for get latest blood pressure record from global database
    //cache data after that in local database
    //return result from local database
    override suspend fun getLatestBloodPressureRecord()
            : Flow<List<IBloodPressureEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //create coroutine builder scope here
        CoroutineScope(Dispatchers.IO).launch {

            while (true) {

                var breakCondition = false

                try {

                    //make request on server for get latest blood pressure record here
                    wrapper.wrapper<IAdviceBloodPressureResponseDto, AdviceBloodPressureResponseDto> {
                        bloodPressureRequest.getLatestBloodPressureRecord()
                    }.collectLatest { status ->

                        when (status) {

                            is Status.Success -> {

                                if (status.toData()?.statusCode == 200) {

                                    //execute cache data in local data base here
                                    bloodPressureRepositoryHelper.cacheLatestBloodPressureRecords(
                                        bloodPressureRecords = listOf(status.toData()?.body?.data as BloodPressureDto),
                                        userId = userAuthId
                                    )

                                }//end if

                                //if this condition is true
                                //delete all caching data
                                else if (
                                    status.toData()?.statusCode == 500 &&
                                    status.toData()?.body?.message.toString() == "Attempt to read property \\\"id\\\" on null"
                                ) {

                                    //execute delete all caching data here
                                    localDatabase.bloodPressureDao()
                                        .deleteBloodPressureRecordsFromId(
                                            userId = userAuthId,
                                            startId = 0
                                        )

                                }//end else if
                                breakCondition = true
                                return@collectLatest
                            }//end Success case

                            is Status.Loading -> {

                            }//end Loading case

                            is Status.Error -> {
                                return@collectLatest
                            }//end Error case

                        }//end when

                    }//end collectLatest

                }//end try
                catch (ex: Exception) {
                    ex.message?.let { Log.d("ERROR", it) }
                }//end catch

                if (breakCondition) {
                    break
                }//end if

                //delay 1/4 second between request and second request
                delay(250)
            }//end while

        }//end coroutine builder scope

        return localDatabase.bloodPressureDao().getLatestBloodPressureRecord(
            userId = userAuthId,
            limit = 1
        )

    }//end getLatestBloodPressureRecord


    //function for get latest history record and cache this data in local data base
    override suspend fun getLatestHistoryBloodPressureRecords(): Flow<List<IBloodPressureEntity>> {


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
                    wrapper.wrapper<ILatestBloodPressureResponseDto, LatestBloodPressureResponseDto> {
                        bloodPressureRequest.getLatestHistoryMeasurements()
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
                                        bloodPressureRepositoryHelper.cacheLatestBloodPressureRecords(
                                            bloodPressureRecords = status.toData()?.body?.data!! as List<BloodPressureDto>,
                                            userId = userAuthId
                                        )
                                    }//end if
                                    else {

                                        //execute delete all caching data here
                                        localDatabase.bloodPressureDao()
                                            .deleteBloodPressureRecordsFromId(
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

        return localDatabase.bloodPressureDao().getLatestBloodPressureRecord(
            userId = userAuthId,
            limit = 3
        )

    }//end getLatestHistoryBloodPressureRecords


    //function for make request on server for provide systolic measurements
    override suspend fun getSystolicMeasurements()
            : Flow<Status<EffectResponse<IDescBloodPressureResponseDto>>> {

        //make flow for emit result of request if internet exist
        val latestSystolicMeasurementFlow =
            flow {

                var dataEmitting: Status<EffectResponse<IDescBloodPressureResponseDto>> =
                    Status.Loading

                emit(dataEmitting)

                val serverRequestsJop = CoroutineScope(Dispatchers.IO).launch {

                    while (true) {

                        var breakCondition = false

                        try {

                            //make request on server here for get diastolic measurements
                            wrapper.wrapper<IDescBloodPressureResponseDto, DescBloodPressureResponseDto> {
                                bloodPressureRequest.getLatestSystolicMeasurement()
                            }.collectLatest { status ->

                                when (status) {

                                    is Status.Success -> {
                                        breakCondition = true
                                        dataEmitting = status
                                        return@collectLatest
                                    }//end Success case

                                    is Status.Loading -> {
                                        dataEmitting = status
                                    }//end Loading case

                                    is Status.Error -> {
                                        dataEmitting = status
                                        return@collectLatest
                                    }//end Error case

                                }//end when

                                //emit current status to flow
                                dataEmitting = status
                            }//end collectLatest

                        }//end try
                        catch (ex: IOException) {
                            dataEmitting = Status.Error(status = 400)
                        }//end catch

                        if (breakCondition) {
                            return@launch
                        }//end if

                        //delay 1/4 second between request and second request
                        delay(250)
                    }//end while

                }//end coroutine builder scope

                serverRequestsJop.join()
                emit(dataEmitting)

            }//end flow

        //return flow with status function result contain on diastolic measurements
        return latestSystolicMeasurementFlow

    }//end getLatestSystolicMeasurement


    //function for make request on server for provide diastolic measurements
    override suspend fun getDiastolicMeasurements()
            : Flow<Status<EffectResponse<IDescBloodPressureResponseDto>>> {

        //make flow for emit result of request if internet exist
        val latestDiastolicMeasurementFlow =
            flow {

                var dataEmitting: Status<EffectResponse<IDescBloodPressureResponseDto>> =
                    Status.Loading

                emit(dataEmitting)

                val serverRequestsJop = CoroutineScope(Dispatchers.IO).launch {

                    while (true) {

                        var breakCondition = false

                        try {

                            //make request on server here for get diastolic measurements
                            wrapper.wrapper<IDescBloodPressureResponseDto, DescBloodPressureResponseDto> {
                                bloodPressureRequest.getLatestDiastolicMeasurement()
                            }.collectLatest { status ->

                                when (status) {

                                    is Status.Success -> {
                                        breakCondition = true
                                        dataEmitting = status
                                        return@collectLatest
                                    }//end Success case

                                    is Status.Loading -> {
                                        //emit current status to flow
                                        dataEmitting = status
                                    }//end Loading case

                                    is Status.Error -> {
                                        dataEmitting = status
                                        return@collectLatest
                                    }//end Error case

                                }//end when

                            }//end collectLatest

                        }//end try
                        catch (ex: IOException) {
                            dataEmitting = Status.Error(status = 400)
                        }//end catch

                        if (breakCondition) {
                            return@launch
                        }//end if

                        //delay 1/4 second between request and second request
                        delay(250)
                    }//end while

                }//end coroutine builder scope

                serverRequestsJop.join()
                emit(dataEmitting)

            }//end flow

        //return flow with status function result contain on diastolic measurements
        return latestDiastolicMeasurementFlow

    }//end getLatestDiastolicMeasurement

}//end BloodPressureRepositoryImpl