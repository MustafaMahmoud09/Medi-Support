package com.example.reminder.mapper

import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import com.example.reminder.domain.entity.interfaces.complexQuery.INearestReminder
import com.example.reminder.domain.mapper.declarations.child.INearestReminderEntityToNearestReminderModelMapper

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
            dayNumber = obj.dayId
        )

    }//end objectConvertor

}//end NearestReminderEntityToNearestReminderModelMapper