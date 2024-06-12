package com.example.notification.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IReadAllNotificationUseCase {

    suspend operator fun invoke(): Flow<Status<EffectResponse<Any>>>

}//end IReadAllNotificationUseCase