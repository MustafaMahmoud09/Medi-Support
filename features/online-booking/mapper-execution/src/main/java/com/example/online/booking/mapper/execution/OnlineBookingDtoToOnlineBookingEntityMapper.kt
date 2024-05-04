package com.example.online.booking.mapper.execution

import com.example.online.booking.data.source.local.data.entity.execution.onlineBooking.OnlineBookingEntity
import com.example.online.booking.domain.dto.declarations.bookingDetails.IBookingDetailDto
import com.example.online.booking.domain.entity.declarations.IOnlineBookingEntity
import com.example.online.booking.domain.mapper.declarations.child.IOnlineBookingDtoToOnlineBookingEntityMapper

class OnlineBookingDtoToOnlineBookingEntityMapper : IOnlineBookingDtoToOnlineBookingEntityMapper {

    override fun listConvertor(list: List<IBookingDetailDto>): List<IOnlineBookingEntity> {

        return list.map { bloodSugarDto ->
            objectConvertor(
                obj = bloodSugarDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: IBookingDetailDto): IOnlineBookingEntity {

        return OnlineBookingEntity(
            id = obj.id ?: 0,
            createdAt = obj.createdAt ?: "",
            userId = obj.userId ?: 0,
            doctorName = obj.doctorName ?: "",
            specialization = obj.specialization ?: "",
            bookingStatus = obj.status ?: 0,
            doctorActiveStatus = obj.activeStatus ?: 0,
        )

    }//end objectConvertor

}//end OnlineBookingDtoToOnlineBookingEntityMapper