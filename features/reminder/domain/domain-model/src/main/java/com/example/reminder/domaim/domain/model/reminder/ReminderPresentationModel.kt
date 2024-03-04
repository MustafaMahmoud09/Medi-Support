package com.example.reminder.domaim.domain.model.reminder


data class ReminderPresentationModel(
    val id: Long,
    val time: String,
    val timeCode: String,
    val days: String,
    val status: Boolean
)
