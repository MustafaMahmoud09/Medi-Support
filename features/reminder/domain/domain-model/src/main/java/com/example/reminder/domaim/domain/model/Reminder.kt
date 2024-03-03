package com.example.reminder.domaim.domain.model

data class Reminder(
    val id: Long,
    val time: String,
    val timeCode: String,
    val days: List<Day>,
    val status: Boolean
)
