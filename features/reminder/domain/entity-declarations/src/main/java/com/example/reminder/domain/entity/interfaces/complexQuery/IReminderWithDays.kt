package com.example.reminder.domain.entity.interfaces.complexQuery

import com.example.reminder.domain.entity.interfaces.entity.IDayEntity
import com.example.reminder.domain.entity.interfaces.entity.IReminderEntity

interface IReminderWithDays {

    val reminder: IReminderEntity

    val days: List<IDayEntity>

}//end ReminderWithDay