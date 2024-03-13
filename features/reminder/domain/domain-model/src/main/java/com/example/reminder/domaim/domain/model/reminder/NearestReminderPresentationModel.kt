package com.example.reminder.domaim.domain.model.reminder

import java.time.LocalTime

data class NearestReminderPresentationModel(
    val id: Long,
    val name: String,
    val time: LocalTime,
    val day: String,
    val differentDays: Int
)
