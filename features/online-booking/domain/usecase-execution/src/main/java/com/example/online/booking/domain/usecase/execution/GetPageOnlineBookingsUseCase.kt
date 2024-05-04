package com.example.online.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.online.booking.domain.entity.declarations.IOnlineBookingEntity
import com.example.online.booking.domain.mapper.declarations.child.IOnlineBookingEntityToOnlineBookingModelMapper
import com.example.online.booking.domain.model.OnlineBookingModel
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.online.booking.domain.usecase.declarations.IGetPageOnlineBookingsUseCase

class GetPageOnlineBookingsUseCase(
    private val onlineBookingRepository: IOnlineBookingRepository,
    private val onlineBookingEntityToOnlineBookingModelMapper: IOnlineBookingEntityToOnlineBookingModelMapper
): IGetPageOnlineBookingsUseCase {

    //function for provide page contain on blood sugar history records
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<OnlineBookingModel>> {

        //get blood sugar entities here
        val onlineBookingEntities = onlineBookingRepository.getPageOnlineBookings(
            page = page,
            pageSize = pageSize
        )

        //convert blood sugar entities to models here
        val onlineBookingModels = onlineBookingEntityToOnlineBookingModelMapper.listConvertor(
            list = onlineBookingEntities.body as List<IOnlineBookingEntity>
        )

        //return response contain on blood sugar models
        return UnEffectResponse(
            lastPageNumber = onlineBookingEntities.lastPageNumber,
            body = onlineBookingModels
        )

    }//end invoke

}//end GetPageOnlineBookingsUseCase