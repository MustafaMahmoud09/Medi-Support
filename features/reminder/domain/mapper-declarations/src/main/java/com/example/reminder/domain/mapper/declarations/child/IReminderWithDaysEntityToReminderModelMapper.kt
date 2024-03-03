package com.example.reminder.domain.mapper.declarations.child

import com.example.reminder.domaim.domain.model.Reminder
import com.example.reminder.domain.entity.interfaces.IReminderWithDays
import com.example.reminder.domain.mapper.declarations.IListMapper

interface IReminderWithDaysEntityToReminderModelMapper : IListMapper<IReminderWithDays, Reminder>