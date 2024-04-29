package com.example.heart.rate.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToSimpleHeartRateModelMapper
import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity

class HeartRateEntityToSimpleHeartRateModelMapper :
    IHeartRateEntityToSimpleHeartRateModelMapper {

    override fun listConvertor(
        list: List<IHeartRateEntity>
    ): List<SimpleHeartRateModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(
                obj = bloodSugarEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IHeartRateEntity
    ): SimpleHeartRateModel {

        return SimpleHeartRateModel(
            id = obj.id,
            rate = obj.rate,
            createdAt = obj.createdAt.substring(0, 10),
            dayName = convertDateToDayOfWeek(
                dateInput = obj.createdAt
            ),
            type = obj.type
        )

    }//end objectConvertor

}//end HeartRateEntityToSimpleHeartRateModelMapper