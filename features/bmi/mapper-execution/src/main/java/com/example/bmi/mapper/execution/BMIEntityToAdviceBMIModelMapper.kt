package com.example.bmi.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToAdviceBMIModelMapper
import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.bmi.domain.model.AdviceBMIModel


class BMIEntityToAdviceBMIModelMapper :
    IBMIEntityToAdviceBMIModelMapper {

    override fun listConvertor(
        list: List<IBMIEntity>
    ): List<AdviceBMIModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(bloodSugarEntity)
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IBMIEntity
    ): AdviceBMIModel {

        return AdviceBMIModel(
            id = obj.id,
            result = String.format("%.2f",obj.result),
            type = obj.type,
            advice = obj.advice ?: "",
            createdAt = obj.createdAt
        )

    }//end objectConvertor

}//end HeartRateEntityToAdviceHeartRateModelMapper