package com.example.notification.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.notification.domain.model.NotificationModel

interface IGetPageNotificationsUseCase {

    suspend operator fun invoke(
        page: Int
    ): UnEffectResponse<List<NotificationModel>>

}//end IGetPageNotificationsUseCase