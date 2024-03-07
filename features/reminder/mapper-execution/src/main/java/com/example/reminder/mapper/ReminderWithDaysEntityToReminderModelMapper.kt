package com.example.reminder.mapper


import com.example.reminder.domaim.domain.model.Day
import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel
import com.example.reminder.domain.entity.interfaces.complexQuery.IReminderWithDays
import com.example.reminder.domain.mapper.declarations.child.IDayEntityToDayModelMapper
import com.example.reminder.domain.mapper.declarations.child.IReminderWithDaysEntityToReminderModelMapper
import java.time.format.DateTimeFormatter

class ReminderWithDaysEntityToReminderModelMapper(
    private val dayEntityToDayModelMapper: IDayEntityToDayModelMapper
) : IReminderWithDaysEntityToReminderModelMapper {

    override fun listConvertor(list: List<IReminderWithDays>): List<ReminderPresentationModel> {

        return list.map { reminderWithDays ->

            objectConvertor(
                obj = reminderWithDays
            )

        }//end map

    }//end listConvertor


    override fun objectConvertor(obj: IReminderWithDays): ReminderPresentationModel {

        return ReminderPresentationModel(
            id = obj.reminder.id,
            time = obj.reminder.time.format(DateTimeFormatter.ofPattern("hh:mm")),
            timeCode = obj.reminder.time.format(DateTimeFormatter.ofPattern("a")).uppercase(),
            status = obj.reminder.status,
            days = daysFormatter(
                days = dayEntityToDayModelMapper.listConvertor(
                    list = obj.days
                )
            )
        )

    }//end objectConvertor

    //function for formatting days
    private fun daysFormatter(days: List<Day>): String {

        return if (days.size == 7) {
            "Everyday"
        }//end if
        else {
            var output = ""

            for (index in days.indices) {

                if (days.size == 1) {
                    output += days[index].name
                    continue
                }

                if (index == 0) {
                    output += "${days[index].name},"
                    continue
                }//end if

                if (index == days.size - 1) {
                    output += " ${days[index].name}"
                    continue
                }//end else

                output += " ${days[index].name},"
            }//nd for

            output
        }//end else

    }//end daysFormatter

}//end ReminderWithDaysEntityToReminderModelMapper