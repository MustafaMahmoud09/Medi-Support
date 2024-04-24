package com.example.blood.pressure.data.source.remote.data.requests

import com.example.blood.pressure.data.source.remote.data.dto.execution.descMeasurement.DescBloodPressureResponseDto
import com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement.LatestBloodPressureResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BloodPressureRequest {

    /**
     * function for make create blood pressure record request
     **/
    @FormUrlEncoded
    @POST("user/blood-pressure/store")
    suspend fun createNewBloodPressureRecord(
        @Field("systolic") systolic: Int,
        @Field("diastolic") diastolic: Int
    ): Response<Any>


    /**
     * function for make get latest blood pressure record request
     **/
    @GET("user/blood-pressure/get-latest-measurement")
    suspend fun getLatestBloodPressureRecord()
            : Response<LatestBloodPressureResponseDto>


    /**
     * function for make get Systolic measurement order by desc
     ***/
    @GET("user/blood-pressure/get-all-systolic")
    suspend fun getLatestSystolicMeasurement()
            : Response<DescBloodPressureResponseDto>


    /**
     * function for make get diastolic measurement order by desc
     ***/
    @GET("user/blood-pressure/get-all-diastolic")
    suspend fun getLatestDiastolicMeasurement()
            : Response<DescBloodPressureResponseDto>

}//end BloodPressureRequest