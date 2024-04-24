package com.example.blood.pressure.mapper.execution

import com.example.blood.pressure.data.source.local.data.entity.execution.bloodPressure.BloodPressureEntity
import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.ILatestBloodPressureDto
import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.blood.pressure.domain.mapper.declarations.child.ILatestBloodPressureDtoToBloodPressureEntityMapper

class LatestBloodPressureDtoToBloodPressureEntityMapper
    : ILatestBloodPressureDtoToBloodPressureEntityMapper {

    override fun listConvertor(
        list: List<ILatestBloodPressureDto>
    ): List<IBloodPressureEntity> {

        return list.map { bloodPressureDto ->
            objectConvertor(
                obj = bloodPressureDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: ILatestBloodPressureDto): IBloodPressureEntity {

        return BloodPressureEntity(
            id = obj.id ?: 0,
            userId = obj.attributes?.userId ?: 0,
            systolic = obj.attributes?.systolic?.toLong() ?: 0,
            diastolic = obj.attributes?.diastolic?.toLong() ?: 0,
            advice = obj.attributes?.pressureAdviceAdvice ?: "",
            type = obj.attributes?.pressureAdviceKey ?: "",
            createdAt = obj.attributes?.createdAt ?: ""
        )

    }//end objectConvertor

}//end LatestBloodPressureDtoToBloodPressureEntityMapper
