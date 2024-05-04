package com.example.offline.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.offline.booking.domain.entity.declarations.IOfflineBookingEntity
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineBookingEntityToOfflineBookingModelMapper
import com.example.offline.booking.domain.model.OfflineBookingModel
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.offline.booking.domain.usecase.declarations.IGetPageOfflineBookingsUseCase

class GetPageOfflineBookingsUseCase(
    private val offlineBookingRepository: IOfflineBookingRepository,
    private val offlineBookingEntityToOfflineBookingModelMapper: IOfflineBookingEntityToOfflineBookingModelMapper
): IGetPageOfflineBookingsUseCase {

    //function for provide page contain on blood sugar history records
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<OfflineBookingModel>> {

        //get blood sugar entities here
        val offlineBookingEntities = offlineBookingRepository.getPageOfflineBookings(
            page = page,
            pageSize = pageSize
        )

        //convert blood sugar entities to models here
        val offlineBookingModels = offlineBookingEntityToOfflineBookingModelMapper.listConvertor(
            list = offlineBookingEntities.body as List<IOfflineBookingEntity>
        )

        //return response contain on blood sugar models
        return UnEffectResponse(
            lastPageNumber = offlineBookingEntities.lastPageNumber,
            body = offlineBookingModels
        )

    }//end invoke

}//end GetPageOnlineBookingsUseCase