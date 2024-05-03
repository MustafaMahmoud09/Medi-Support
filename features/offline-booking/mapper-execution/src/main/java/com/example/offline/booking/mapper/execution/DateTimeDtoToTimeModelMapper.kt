package com.example.offline.booking.mapper.execution

import com.example.offline.booking.domain.dto.declarations.doctorTime.ITime
import com.example.offline.booking.domain.mapper.declarations.child.IDateTimeDtoToTimeModelMapper
import com.example.offline.booking.domain.model.TimeModel

class DateTimeDtoToTimeModelMapper : IDateTimeDtoToTimeModelMapper {

    override fun listConvertor(
        list: List<ITime>
    ): List<TimeModel> {

        return list.map { offlineDoctorDto ->
            objectConvertor(
                obj = offlineDoctorDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: ITime
    ): TimeModel {

        return TimeModel(
            id = obj.id ?: 0,
            time = obj.time ?: ""
        )

    }//end objectConvertor

}//end OfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper