package com.example.notification.mapper.execution

import com.example.notification.domain.dto.declarations.notificationPage.INotificationDto
import com.example.notification.domain.mapper.declarations.child.INotificationDtoToNotificationModelMapper
import com.example.notification.domain.model.NotificationModel

class NotificationDtoToNotificationModelMapper : INotificationDtoToNotificationModelMapper {

    override fun listConvertor(
        list: List<INotificationDto>
    ): List<NotificationModel> {

        return list.map { notification ->
            objectConvertor(
                obj = notification
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: INotificationDto): NotificationModel {

        return NotificationModel(
            id = obj.id ?: "",
            notification = obj.message ?: "",
            read = obj.readAt.toString() != "null",
            type = obj.type ?: "",
            bookingId = obj.bookingId ?: 0
        )

    }//end objectConvertor

}//end NotificationDtoToNotificationModelMapper