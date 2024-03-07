package com.example.reminder.mapper

import com.example.reminder.domaim.domain.model.Day
import com.example.reminder.domain.entity.interfaces.entity.IDayEntity
import com.example.reminder.domain.mapper.declarations.child.IDayEntityToDayModelMapper

class DayEntityToDayModelMapper : IDayEntityToDayModelMapper {

    override fun listConvertor(list: List<IDayEntity>): List<Day> {

        return list.map { dayEntity ->

            objectConvertor(
                obj = dayEntity
            )

        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: IDayEntity): Day {

        return Day(
            id = obj.id,
            name = obj.day.substring(
                startIndex = 0,
                endIndex = if (obj.day.length < 3) obj.day.length  else 3
            )
        )

    }//end objectConvertor

}//end DayEntityToDayModelMapper