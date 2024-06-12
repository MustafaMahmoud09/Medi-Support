package com.example.notification.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.notification.domain.repository.declarations.INotificationRepository
import com.example.notification.domain.usecase.declarations.IReadNotificationUseCase
import kotlinx.coroutines.flow.Flow

class ReadNotificationUseCase(
    private val notificationRepository: INotificationRepository,
) : IReadNotificationUseCase {

    //function for make request on repository for read notification by id
    override suspend fun invoke(
        notificationId: String
    ): Flow<Status<EffectResponse<Any>>> {
        return notificationRepository.readNotificationById(
            id = notificationId
        )
    }//end invoke

}//end ReadNotificationUseCase