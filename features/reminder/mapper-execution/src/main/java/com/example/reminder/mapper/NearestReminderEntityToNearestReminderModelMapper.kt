package com.example.reminder.mapper

import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import com.example.reminder.domain.entity.interfaces.complexQuery.INearestReminder
import com.example.reminder.domain.mapper.declarations.child.INearestReminderEntityToNearestReminderModelMapper
import java.time.LocalDate
import java.time.LocalTime

class NearestReminderEntityToNearestReminderModelMapper
    : INearestReminderEntityToNearestReminderModelMapper {

    override fun listConvertor(
        list: List<INearestReminder>
    ): List<NearestReminderPresentationModel> {

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

    //function for calculate different days between today and reminder days
    private fun Long.calculateDifferentDays(time: LocalTime): Int {

        //define now day
        val today = LocalDate.now().dayOfWeek

        val ordinal = today.value

        //0 - reminder day number - 6
        //to day have number id
        //calculate different between today and reminder number
        return if (this.toInt() - 1 > ordinal || ((this.toInt() - ordinal - 1) == 0 && !LocalTime.now().isAfter(time))) {

            if (LocalTime.now().isBefore(time)) {

                this.toInt() - ordinal - 1

            } else {

                this.toInt() - ordinal - 2

            }//end else

        }//end if
        else {

            if (LocalTime.now().isBefore(time)) {

                6 - ordinal + this.toInt()

            } else {

                6 - ordinal + this.toInt() - 1

            }//end else

        }//end else

    }//end calculateDifferentDays

}//end NearestReminderEntityToNearestReminderModelMapper