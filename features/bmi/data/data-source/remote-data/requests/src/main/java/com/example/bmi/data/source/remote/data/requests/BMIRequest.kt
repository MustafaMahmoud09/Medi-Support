package com.example.heart.rate.data.source.remote.data.requests

import com.example.heart.rate.data.source.dto.execution.lastRecords.LastHeartRateResponseDto
import com.example.heart.rate.data.source.dto.execution.pageRecords.PageHeartRateResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HeartRateRequest {

    /**
     * function for make request on server for store new heart rate record
     **/
    @FormUrlEncoded
    @POST("user/heart-rate/store")
    suspend fun createHeartRateRecord(
        @Field("heart_rate") rate: Int
    ): Response<Any>

    /**
     * function for make request on server for get latest seven record
     **/
    @GET("user/heart-rate/get-last-seven-records")
    suspend fun getLastSevenHeartRateRecords()
            : Response<LastHeartRateResponseDto>


    /**
     * function for make request on server for get page heart rate records
     **/
    @GET("user/heart-rate/get-all-records")
    suspend fun getPageHeartRateRecords(
        @Query("page") page: Int
    ): Response<PageHeartRateResponseDto>


}//end HeartRateRequest