package com.example.offline.booking.mapper.execution

import com.example.offline.booking.data.source.local.data.entity.execution.offlineBooking.OfflineBookingEntity
import com.example.offline.booking.domain.dto.declarations.bookingDetails.IOfflineBookingDto
import com.example.offline.booking.domain.entity.declarations.IOfflineBookingEntity
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineBookingDtoToOfflineBookingEntityMapper

class OfflineBookingDtoToOfflineBookingEntityMapper :
    IOfflineBookingDtoToOfflineBookingEntityMapper {

    override fun listConvertor(list: List<IOfflineBookingDto>): List<IOfflineBookingEntity> {

        return list.map { bloodSugarDto ->
            objectConvertor(
                obj = bloodSugarDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: IOfflineBookingDto): IOfflineBookingEntity {

        return OfflineBookingEntity(
            id = obj.id ?: 0,
            createdAt = obj.createdAt ?: "",
            userId = obj.userId ?: 0,
            doctorName = (obj.firstName ?: "") + " " + (obj.lastName ?: ""),
            specialization = obj.specialization ?: "",
            time = obj.time ?: "",
            date = obj.date ?: "",
            clinicLocation = obj.clinicLocation ?: ""
        )

    }//end objectConvertor

}//end OfflineBookingDtoToOfflineBookingEntityMapper