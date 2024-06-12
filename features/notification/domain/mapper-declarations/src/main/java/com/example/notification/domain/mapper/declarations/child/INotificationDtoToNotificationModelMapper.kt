package com.example.notification.domain.mapper.declarations.child

import com.example.notification.domain.dto.declarations.notificationPage.INotificationDto
import com.example.notification.domain.mapper.declarations.IListMapper
import com.example.notification.domain.model.NotificationModel


interface INotificationDtoToNotificationModelMapper
    : IListMapper<INotificationDto, NotificationModel>