package com.example.offline.booking.mapper.execution

import com.example.offline.booking.domain.entity.declarations.IOfflineBookingEntity
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineBookingEntityToOfflineBookingModelMapper
import com.example.offline.booking.domain.model.OfflineBookingModel

class OfflineBookingEntityToOfflineBookingModelMapper :
    IOfflineBookingEntityToOfflineBookingModelMapper {

    override fun listConvertor(
        list: List<IOfflineBookingEntity>
    ): List<OfflineBookingModel> {

        return list.map { offlineBookingEntity ->
            objectConvertor(
                obj = offlineBookingEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IOfflineBookingEntity
    ): OfflineBookingModel {

        return OfflineBookingModel(
            id = obj.id,
            doctorName = obj.doctorName,
            specialization = obj.specialization,
            clinicLocation = obj.clinicLocation,
            date = obj.date + " | " + obj.time
        )

    }//end objectConvertor

}//end OfflineBookingEntityToOfflineBookingModelMapper