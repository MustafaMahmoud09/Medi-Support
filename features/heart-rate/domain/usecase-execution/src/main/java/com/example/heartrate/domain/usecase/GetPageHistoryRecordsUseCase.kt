package com.example.heartrate.domain.usecase

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToSimpleHeartRateModelMapper
import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

class GetPageHistoryRecordsUseCase(
    private val heartRateRepository: IHeartRateRepository,
    private val heartRateEntityToSimpleHeartRateModelMapper: IHeartRateEntityToSimpleHeartRateModelMapper
) : IGetPageHistoryRecordsUseCase {

    //function for provide page contain on blood sugar history records
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<SimpleHeartRateModel>> {

        //get blood sugar entities here
        val heartRateEntities = heartRateRepository.getPageMeasurements(
            page = page,
            pageSize = pageSize
        )

        //convert blood sugar entities to models here
        val heartRateModels = heartRateEntityToSimpleHeartRateModelMapper.listConvertor(
            list = heartRateEntities.body as List<IHeartRateEntity>
        )

        //return response contain on blood sugar models
        return UnEffectResponse(
            lastPageNumber = heartRateEntities.lastPageNumber,
            body = heartRateModels
        )

    }//end invoke

}//end GetPageHistoryRecordsUseCase