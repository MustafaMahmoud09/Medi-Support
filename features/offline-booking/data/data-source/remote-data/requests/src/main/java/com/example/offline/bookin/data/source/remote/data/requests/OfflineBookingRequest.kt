package com.example.offline.bookin.data.source.remote.data.requests

import com.example.offline.booking.data.source.remote.data.dto.execution.doctorDetails.DoctorDetailsResponseDto
import com.example.offline.booking.data.source.remote.data.dto.execution.doctorTime.DoctorTimeResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OfflineBookingRequest {

    /**
     * function for make request for get doctor details with booking dates
     ***/
    @GET("user/booking/get-doctor-details")
    suspend fun getDoctorDetails(
        @Query("id") id: Long
    ): Response<DoctorDetailsResponseDto>


    /**
     * function for make request for get booking date times
     ***/
    @GET("user/booking/get-times")
    suspend fun getDateTimes(
        @Query("id") id: Long
    ): Response<DoctorTimeResponseDto>


    /**
     * function for make request on server for book offline appointment
     **/
    @FormUrlEncoded
    @POST("user/booking/appointment")
    suspend fun bookOfflineAppointment(
        @Field("date_id") dateId: Long,
        @Field("doctor_id") doctorId: Long,
        @Field("time_id") timeId: Long
    ): Response<Any>

}//end OfflineBookingRequest