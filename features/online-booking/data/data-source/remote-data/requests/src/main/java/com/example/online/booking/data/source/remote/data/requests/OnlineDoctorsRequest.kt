package com.example.online.booking.data.source.remote.data.requests

import com.example.online.booking.data.source.remote.data.dto.execution.pageOnlineDoctor.PageOnlineDoctorResponseDto
import com.example.online.booking.data.source.remote.data.dto.execution.topOnlineDoctors.TopOnlineDoctorResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OnlineDoctorsRequest {

    /**
     * function for make get top online doctors request
     **/
    @GET("auth/user/ten-online-doctors")
    suspend fun getTopOnlineDoctors()
            : Response<TopOnlineDoctorResponseDto>


    /**
     * function for make request for get page from all online doctors
     **/
    @GET("auth/user/online-doctors")
    suspend fun getAllOnlineDoctors(
        @Query("page") page: Int
    ): Response<PageOnlineDoctorResponseDto>


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