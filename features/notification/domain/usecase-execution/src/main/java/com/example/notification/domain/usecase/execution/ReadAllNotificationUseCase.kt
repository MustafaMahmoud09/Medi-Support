package com.example.notification.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.notification.domain.repository.declarations.INotificationRepository
import com.example.notification.domain.usecase.declarations.IReadAllNotificationUseCase
import kotlinx.coroutines.flow.Flow

class ReadAllNotificationUseCase(
    private val notificationRepository: INotificationRepository,
): IReadAllNotificationUseCase {

    //function for make request on repository for read all notification
    override suspend fun invoke(): Flow<Status<EffectResponse<Any>>> {
        return notificationRepository.readAllNotifications()
    }//end invoke

}//end ReadAllNotificationUseCase