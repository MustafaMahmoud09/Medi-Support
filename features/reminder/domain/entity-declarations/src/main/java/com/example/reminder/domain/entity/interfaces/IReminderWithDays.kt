package com.example.reminder.domain.entity.interfaces

interface IReminderWithDays {

    val reminder: IReminderEntity

    val days: List<IDayEntity>

}//end ReminderWithDay