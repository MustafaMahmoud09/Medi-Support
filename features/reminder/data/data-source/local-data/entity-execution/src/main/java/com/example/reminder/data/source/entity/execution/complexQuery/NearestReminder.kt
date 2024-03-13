package com.example.reminder.data.source.entity.execution.complexQuery

import com.example.reminder.domain.entity.interfaces.complexQuery.INearestReminder
import java.time.LocalTime

data class NearestReminder(
    override val id: Long,
    override val name: String,
    override val time: LocalTime,
    override val day: String,
    override val endResultDaysDifferent: Int,
    override val dayId: Long
) : INearestReminder
