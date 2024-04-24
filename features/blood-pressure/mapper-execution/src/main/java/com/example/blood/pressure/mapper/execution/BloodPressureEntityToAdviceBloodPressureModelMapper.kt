package com.example.blood.pressure.mapper.execution

import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.blood.pressure.domain.mapper.declarations.child.IBloodPressureEntityToAdviceBloodPressureModelMapper
import com.example.blood.pressure.domain.model.AdviceBloodPressureModel

class BloodPressureEntityToAdviceBloodPressureModelMapper :
    IBloodPressureEntityToAdviceBloodPressureModelMapper {

    override fun listConvertor(
        list: List<IBloodPressureEntity>
    ): List<AdviceBloodPressureModel> {

        return list.map { bloodPressureEntity ->
            objectConvertor(
                obj = bloodPressureEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IBloodPressureEntity
    ): AdviceBloodPressureModel {

        return AdviceBloodPressureModel(
            id = obj.id,
            systolic = obj.systolic,
            diastolic = obj.diastolic,
            createdAt = obj.createdAt.substring(0, 10),
            advice = obj.advice ?: "",
            type = obj.type
        )

    }//end objectConvertor

}//end BloodPressureEntityToAdviceBloodPressureModelMapper