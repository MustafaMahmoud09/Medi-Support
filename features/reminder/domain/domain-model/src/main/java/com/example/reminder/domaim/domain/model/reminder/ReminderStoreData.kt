package com.example.reminder.domaim.domain.model.reminder

import java.time.LocalTime

data class ReminderStoreData(
    val reminderName: String,
    val time: LocalTime,
    val days: List<Long>
)