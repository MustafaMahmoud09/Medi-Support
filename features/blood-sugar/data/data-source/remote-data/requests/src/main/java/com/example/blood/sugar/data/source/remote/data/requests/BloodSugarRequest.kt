package com.example.blood.sugar.data.source.remote.data.requests

import com.example.blood.sugar.data.source.remote.data.dto.execution.lastRecords.LatestBloodSugarResponseDto
import com.example.blood.sugar.data.source.remote.data.dto.execution.pageRecords.PageBloodSugarResponseDto
import com.example.blood.sugar.data.source.remote.data.dto.execution.status.BloodSugarStatusResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BloodSugarRequest {

    /**
     * function for make request on server for store new blood sugar record
     **/
    @FormUrlEncoded
    @POST("user/blood-sugar/store")
    suspend fun createBloodSugarRecord(
        @Field("level") level: Float,
        @Field("blood_sugar_statuses_id") statusId: Int
    ): Response<Any>


    /**
     * function for make request on server for get all blood sugar status
     ***/
    @GET("user/blood-sugar/get-all-status")
    suspend fun getBloodSugarStatus()
            : Response<BloodSugarStatusResponseDto>


    /**
     * function for make request on server for get latest seven record
     **/
    @GET("user/blood-sugar/get-last-seven-records")
    suspend fun getLastSevenBloodSugarRecords()
            : Response<LatestBloodSugarResponseDto>


    /**
     * function for make request on server for get page blood sugar records
     **/
    @GET("user/blood-sugar/get-all-records")
    suspend fun getPageBloodSugarRecords(
        @Query("page") page: Int
    ): Response<PageBloodSugarResponseDto>


}//end BloodSugarRequest