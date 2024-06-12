package com.example.notification.data.repository.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.notification.data.source.remote.data.dto.execution.notificationPage.PageNotificationResponseDto
import com.example.notification.data.source.remote.data.requests.NotificationRequest
import com.example.notification.domain.dto.declarations.notificationPage.IPageNotificationResponseDto
import com.example.notification.domain.repository.declarations.INotificationRepository
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(
    private val notificationRequest: NotificationRequest,
    private val wrapper: ResponseWrapper,
) : INotificationRepository {

    override suspend fun getNotifications(
        page: Int
    ): Flow<Status<EffectResponse<IPageNotificationResponseDto>>> {

        return wrapper.infiniteWrapper<IPageNotificationResponseDto, PageNotificationResponseDto> {
            notificationRequest.getNotifications(
                page = page
            )
        }//end infiniteWrapper

    }//end getNotifications


    //function for make request on server for read notification by id
    override suspend fun readNotificationById(
        id: String
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            notificationRequest.readNotificationById(
                notificationId = id
            )
        }//end wrapper

    }//end readNotificationById


    //function for make request on server for read all notifications
    override suspend fun readAllNotifications(): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper {
            notificationRequest.readAllNotification()
        }//end wrapper

    }//end readAllNotifications

}//end NotificationRepositoryImpl