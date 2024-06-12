package com.example.notification.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.notification.domain.mapper.declarations.child.INotificationDtoToNotificationModelMapper
import com.example.notification.domain.model.NotificationModel
import com.example.notification.domain.repository.declarations.INotificationRepository
import com.example.notification.domain.usecase.declarations.IGetPageNotificationsUseCase
import kotlinx.coroutines.flow.collect
import java.util.LinkedList

class GetPageNotificationsUseCase(
    private val notificationRepository: INotificationRepository,
    private val notificationDtoToNotificationModelMapper: INotificationDtoToNotificationModelMapper
): IGetPageNotificationsUseCase{

    override suspend fun invoke(
        page: Int
    ): UnEffectResponse<List<NotificationModel>> {

        val notificationResult = LinkedList<NotificationModel>()
        var lastPage = 0

        while (true) {

            var breakCondition = false

            //make request on repository for get data
            //collect result flow
            //in success status map data to model and kill collect
            notificationRepository.getNotifications(
                page = page
            ).collect { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get offline doctors
                            val notificationDto = status.toData()?.body?.data?.data

                            //map offline doctors form dto to model
                            val notificationModels =
                                notificationDtoToNotificationModelMapper.listConvertor(
                                    list = notificationDto ?: emptyList()
                                )

                            //add offline doctors model list to result list
                            notificationResult.addAll(notificationModels)
                            lastPage = status.toData()?.body?.data?.lastPage ?: 0
                            breakCondition = true
                        }//end if

                        return@collect

                    }//end success case

                    is Status.Loading -> {

                    }//end loading case

                    is Status.Error -> {

                    }//end error case

                }//end when

            }//end collect

            if (breakCondition) {
                break
            }//end if

        }//end while

        //return result here
        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = notificationResult
        )

    }//end GetPageNotificationsUseCase

}//end GetPageNotificationsUseCase