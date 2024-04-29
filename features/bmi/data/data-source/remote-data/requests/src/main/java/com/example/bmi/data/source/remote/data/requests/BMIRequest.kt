package com.example.bmi.data.source.remote.data.requests

import com.example.bmi.data.source.remote.data.dto.execution.lastRecords.LastBMIResponseDto
import com.example.bmi.data.source.remote.data.dto.execution.pageRecords.PageBMIResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BMIRequest {

    /**
     * function for make request on server for store new bmi record
     **/
    @FormUrlEncoded
    @POST("user/bmi/store")
    suspend fun createBMIRecord(
        @Field("gender") gender: Int,
        @Field("age") age: Int,
        @Field("height") height: Float,
        @Field("weight") weight: Float
    ): Response<Any>


    /**
     * function for make request on server for get latest seven record
     **/
    @GET("user/bmi/get-last-seven-records")
    suspend fun getLastSevenBMIRecords()
            : Response<LastBMIResponseDto>


    /**
     * function for make request on server for get page BMI records
     **/
    @GET("user/bmi/get-all-records")
    suspend fun getPageBMIRecords(
        @Query("page") page: Int
    ): Response<PageBMIResponseDto>


}//end BMIRequest