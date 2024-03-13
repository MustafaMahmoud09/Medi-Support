package com.example.reminder.mapper

import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import com.example.reminder.domain.entity.interfaces.complexQuery.INearestReminder
import com.example.reminder.domain.mapper.declarations.child.INearestReminderEntityToNearestReminderModelMapper
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class NearestReminderEntityToNearestReminderModelMapper
    : INearestReminderEntityToNearestReminderModelMapper {

    override fun listConvertor(list: List<INearestReminder>): List<NearestReminderPresentationModel> {

        return list.map { nearestReminder ->
            objectConvertor(nearestReminder)
        }//end list

    }//end NearestReminderEntityNearestReminderModelMapper

    //function for convert nearest reminder to nearest reminder model object
    override fun objectConvertor(
        obj: INearestReminder
    ): NearestReminderPresentationModel {

        return NearestReminderPresentationModel(
            id = obj.id,
            name = obj.name,
            time = obj.time,
            day = obj.day,
            differentDays = obj.dayId.calculateDifferentDays(obj.time)
        )

    }//end objectConvertor


    private fun Long.calculateDifferentDays(time: LocalTime): Int {

        //define now day
        val today = LocalDate.now().dayOfWeek

        val ordinal = today.value

        return if (
            this.toInt() == ordinal &&
            LocalTime.now().isBefore(time) &&
            formatLocalTime(
                localTime = LocalTime.now(),
                pattern = "hh:mm:ss a"
            ) == formatLocalTime(
                localTime = time,
                pattern = "hh:mm:ss a"
            )
        ) {
            0
        }//end if

        else {
            (6 - ordinal + this - 1).toInt()
        }//end else if

    }//end calculateDifferentDays

    fun formatLocalTime(localTime: LocalTime, pattern: String): String {

        val timeFormatter = DateTimeFormatter.ofPattern(pattern)
        val timeSelectedValue = localTime
            .format(timeFormatter)
            .uppercase(Locale.getDefault())

        return timeSelectedValue

    }//end formatLocalTime

}