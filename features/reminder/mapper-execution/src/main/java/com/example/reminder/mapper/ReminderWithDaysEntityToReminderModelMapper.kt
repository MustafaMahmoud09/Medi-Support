package com.example.reminder.mapper

import com.example.reminder.domaim.domain.model.Reminder
import com.example.reminder.domain.entity.interfaces.IReminderWithDays
import com.example.reminder.domain.mapper.declarations.child.IReminderWithDaysEntityToReminderModelMapper

class ReminderWithDaysEntityToReminderModelMapper(
    private val dayEntityToDayModelMapper: DayEntityToDayModelMapper
) : IReminderWithDaysEntityToReminderModelMapper {

    override fun listConvertor(list: List<IReminderWithDays>): List<Reminder> {

        return list.map { reminderWithDays ->

            objectConvertor(
                obj = reminderWithDays
            )

        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: IReminderWithDays): Reminder {

        return Reminder(
            id = obj.reminder.id,
            time = obj.reminder.time.toString(),
            timeCode = "",
            status = obj.reminder.status,
            days = dayEntityToDayModelMapper.listConvertor(
                list = obj.days
            )
        )

    }//end objectConvertor


}//end ReminderWithDaysEntityToReminderModelMapper