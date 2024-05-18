package com.example.online.booking.data.source.remote.data.requests

import com.example.online.booking.data.source.remote.data.dto.execution.bookingDetails.BookingDetailsResponseDto
import com.example.online.booking.data.source.remote.data.dto.execution.doctorDetails.OnlineDoctorDetailsResponseDto
import com.example.online.booking.data.source.remote.data.dto.execution.payment.PaymentResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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


    /**
     * function for make request on server for get online bookings
     **/
    @GET("auth/user/all-bookings")
    suspend fun getOnlineBookings(
        @Query("page") page: Int
    ): Response<BookingDetailsResponseDto>


    /**
     * function for make request on server for provide intent secret for payment
     **/
    @POST("user/online-booking/payment/{id}")
    suspend fun getPaymentIntentSecret(
        @Path("id") id: Long
    ): Response<PaymentResponseDto>

}//end OnlineBookingRequest