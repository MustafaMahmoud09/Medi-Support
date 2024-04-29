package com.example.bmi.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToSimpleBMIModelMapper
import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.bmi.domain.model.SimpleBMIModel
import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.bmi.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

class GetPageHistoryRecordsUseCase(
    private val bmiRepository: IBMIRepository,
    private val heartRateEntityToSimpleHeartRateModelMapper: IBMIEntityToSimpleBMIModelMapper
) : IGetPageHistoryRecordsUseCase {

    //function for provide page contain on blood sugar history records
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<SimpleBMIModel>> {

        //get blood sugar entities here
        val bmiEntities = bmiRepository.getPageMeasurements(
            page = page,
            pageSize = pageSize
        )

        //convert blood sugar entities to models here
        val bmiModels = heartRateEntityToSimpleHeartRateModelMapper.listConvertor(
            list = bmiEntities.body as List<IBMIEntity>
        )

        //return response contain on blood sugar models
        return UnEffectResponse(
            lastPageNumber = bmiEntities.lastPageNumber,
            body = bmiModels
        )

    }//end invoke

}//end GetPageHistoryRecordsUseCase