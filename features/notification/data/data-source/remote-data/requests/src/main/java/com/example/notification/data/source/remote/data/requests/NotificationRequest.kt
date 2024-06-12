package com.example.notification.data.source.remote.data.requests

import com.example.notification.data.source.remote.data.dto.execution.notificationPage.PageNotificationResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationRequest {

    //function for make get notification request on server
    @GET("auth/user/notifications")
    suspend fun getNotifications(
        @Query("page") page: Int
    ): Response<PageNotificationResponseDto>


    //function for make request for read notification by id
    @PUT("auth/user/notifications/{id}")
    suspend fun readNotificationById(
        @Path("id") notificationId: String
    ): Response<Any>


    //function for make request for read all notifications
    @POST("auth/user/notifications/mark-all-read")
    suspend fun readAllNotification(): Response<Any>

}//end NotificationRequest