package com.example.offline.bookin.data.source.remote.data.requests

import com.example.offline.booking.data.source.remote.data.dto.execution.pageOfflineDoctors.PageOfflineDoctorsResponseDto
import com.example.offline.booking.data.source.remote.data.dto.execution.topOfflineDoctors.TopOfflineDoctorsResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OfflineDoctorsRequest {

    /**
     * function for make get top offline doctors request
     **/
    @GET("auth/user/get-top-doctors")
    suspend fun getTopOfflineDoctors()
            : Response<TopOfflineDoctorsResponseDto>


    /**
     * function for make request for get page from all offline doctors
     **/
    @GET("auth/user/get-all-doctors")
    suspend fun getAllOfflineDoctors(
        @Query("page") page: Int
    ): Response<PageOfflineDoctorsResponseDto>


    /**
     * function for make request for get page from search offline doctors
     **/
    @GET("auth/user/search-doctors")
    suspend fun searchOnOfflineDoctors(
        @Query("page") page: Int,
        @Query("search") searchKey: String
    ): Response<PageOfflineDoctorsResponseDto>


    /**
     * function for make request on server for rate doctor
     ***/
    @FormUrlEncoded
    @POST("auth/user/ratings")
    suspend fun rateDoctor(
        @Field("doctor_id") doctorId: Long,
        @Field("rate") rate: Int
    ): Response<Any>

}//end OfflineDoctorsRequest