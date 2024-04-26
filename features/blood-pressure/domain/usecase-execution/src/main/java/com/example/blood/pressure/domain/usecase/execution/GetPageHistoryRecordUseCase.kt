package com.example.blood.pressure.domain.usecase.execution

import com.example.blood.pressure.domain.mapper.declarations.child.IBloodPressureEntityToSimpleBloodPressureModelMapper
import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
import com.example.blood.pressure.domain.usecase.declarations.IGetPageHistoryRecordUseCase
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

class GetPageHistoryRecordUseCase(
    private val bloodPressureRepository: IBloodPressureRepository,
    private val bloodPressureEntityToSimpleBloodPressureModelMapper: IBloodPressureEntityToSimpleBloodPressureModelMapper
) : IGetPageHistoryRecordUseCase {

    //function for get page history blood pressure records
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<SimpleBloodPressureModel>> {

        val bloodPressureEntities = bloodPressureRepository.getPageBloodPressure(
            page = page,
            pageSize = pageSize
        )

        val simpleBloodPressureModel =
            bloodPressureEntityToSimpleBloodPressureModelMapper.listConvertor(
                list = bloodPressureEntities.body ?: emptyList()
            )

        return UnEffectResponse(
            lastPageNumber = bloodPressureEntities.lastPageNumber,
            body = simpleBloodPressureModel
        )

    }//end invoke

}//end GetPageHistoryRecordUseCase