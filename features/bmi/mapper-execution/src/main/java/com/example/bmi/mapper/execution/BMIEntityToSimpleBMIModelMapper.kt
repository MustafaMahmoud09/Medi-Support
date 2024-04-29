package com.example.bmi.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToSimpleBMIModelMapper
import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.bmi.domain.model.SimpleBMIModel

class BMIEntityToSimpleBMIModelMapper :
    IBMIEntityToSimpleBMIModelMapper {

    override fun listConvertor(
        list: List<IBMIEntity>
    ): List<SimpleBMIModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(
                obj = bloodSugarEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IBMIEntity
    ): SimpleBMIModel {

        return SimpleBMIModel(
            id = obj.id,
            result = String.format("%.2f",obj.result),
            createdAt = obj.createdAt.substring(0, 10),
            dayName = convertDateToDayOfWeek(
                dateInput = obj.createdAt
            ),
            type = obj.type
        )

    }//end objectConvertor

}//end HeartRateEntityToSimpleHeartRateModelMapper