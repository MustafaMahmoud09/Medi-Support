package com.example.reminder.domain.mapper.declarations.child

import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import com.example.reminder.domain.entity.interfaces.complexQuery.INearestReminder
import com.example.reminder.domain.mapper.declarations.IListMapper
import com.example.reminder.domain.mapper.declarations.IObjectMapper

interface INearestReminderEntityToNearestReminderModelMapper
    : IListMapper<INearestReminder, NearestReminderPresentationModel>
