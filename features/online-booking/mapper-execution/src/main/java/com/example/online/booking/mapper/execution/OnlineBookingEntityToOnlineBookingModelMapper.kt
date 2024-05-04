package com.example.online.booking.mapper.execution

import com.example.online.booking.domain.entity.declarations.IOnlineBookingEntity
import com.example.online.booking.domain.mapper.declarations.child.IOnlineBookingEntityToOnlineBookingModelMapper
import com.example.online.booking.domain.model.OnlineBookingModel

class OnlineBookingEntityToOnlineBookingModelMapper:
    IOnlineBookingEntityToOnlineBookingModelMapper {

    override fun listConvertor(
        list: List<IOnlineBookingEntity>
    ): List<OnlineBookingModel> {

        return list.map { onlineBookingEntity ->
            objectConvertor(
                obj = onlineBookingEntity
            )
        }

    }//end listConvertor

    override fun objectConvertor(
        obj: IOnlineBookingEntity
    ): OnlineBookingModel {

        return OnlineBookingModel(
            id = obj.id,
            doctorName = obj.doctorName,
            bookingStatus = obj.bookingStatus,
            specialization = obj.specialization,
            activeStatus = obj.doctorActiveStatus == 1
        )

    }//end objectConvertor

}//end OnlineBookingEntityToOnlineBookingModelMapper