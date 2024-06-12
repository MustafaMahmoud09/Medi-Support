package com.example.notification.domain.repository.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.notification.domain.dto.declarations.notificationPage.IPageNotificationResponseDto
import kotlinx.coroutines.flow.Flow

interface INotificationRepository {

    suspend fun getNotifications(
        page: Int
    ): Flow<Status<EffectResponse<IPageNotificationResponseDto>>>


    suspend fun readNotificationById(
        id: String
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun readAllNotifications(): Flow<Status<EffectResponse<Any>>>

}//end INotificationRepository