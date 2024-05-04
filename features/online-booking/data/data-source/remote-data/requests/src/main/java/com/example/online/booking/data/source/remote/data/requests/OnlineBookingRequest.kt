package com.example.online.booking.data.source.remote.data.requests

import com.example.online.booking.data.source.remote.data.dto.execution.doctorDetails.OnlineDoctorDetailsResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OnlineBookingRequest {

    /**
     * function for make request for get online doctor details
     ***/
    @GET("auth/user/online-doctor/{id}")
    suspend fun getDoctorDetails(
        @Path("id") id: Long
    ): Response<OnlineDoctorDetailsResponseDto>


    /**
     * function for make request on server for book online appointment
     **/
    @FormUrlEncoded
    @POST("auth/user/online-bookings")
    suspend fun bookOnlineAppointment(
        @Field("doctor_id") doctorId: Long
    ): Response<Any>

}//end OnlineBookingRequest