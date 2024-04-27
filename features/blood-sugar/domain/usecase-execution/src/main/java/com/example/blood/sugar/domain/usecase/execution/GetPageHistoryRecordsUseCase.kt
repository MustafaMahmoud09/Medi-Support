package com.example.blood.sugar.domain.usecase.execution

import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToSimpleBloodSugarModelMapper
import com.example.blood.sugar.domain.model.SimpleBloodSugarModel
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

class GetPageHistoryRecordsUseCase(
    private val bloodSugarRepository: IBloodSugarRepository,
    private val bloodSugarEntityToSimpleBloodSugarModelMapper: IBloodSugarEntityToSimpleBloodSugarModelMapper
) : IGetPageHistoryRecordsUseCase {

    //function for provide page contain on blood sugar history records
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<SimpleBloodSugarModel>> {

        //get blood sugar entities here
        val bloodSugarEntities = bloodSugarRepository.getPageMeasurements(
            page = page,
            pageSize = pageSize
        )

        //convert blood sugar entities to models here
        val bloodSugarModels = bloodSugarEntityToSimpleBloodSugarModelMapper.listConvertor(
            list = bloodSugarEntities.body as List<IBloodSugarEntity>
        )

        //return response contain on blood sugar models
        return UnEffectResponse(
            lastPageNumber = bloodSugarEntities.lastPageNumber,
            body = bloodSugarModels
        )

    }//end invoke

}//end GetPageHistoryRecordsUseCase