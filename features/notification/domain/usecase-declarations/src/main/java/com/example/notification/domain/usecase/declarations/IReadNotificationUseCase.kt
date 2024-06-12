package com.example.notification.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IReadNotificationUseCase {

    suspend operator fun invoke(
        notificationId: String
    ): Flow<Status<EffectResponse<Any>>>

}//end IReadNotificationUseCase