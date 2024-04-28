package com.example.heart.rate.mapper.execution

import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToSimpleBloodSugarModelMapper
import com.example.blood.sugar.domain.model.SimpleBloodSugarModel

class BloodSugarEntityToSimpleBloodSugarModelMapper :
    IBloodSugarEntityToSimpleBloodSugarModelMapper {

    override fun listConvertor(
        list: List<IBloodSugarEntity>
    ): List<SimpleBloodSugarModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(
                obj = bloodSugarEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IBloodSugarEntity
    ): SimpleBloodSugarModel {

        return SimpleBloodSugarModel(
            id = obj.id,
            level = obj.level,
            createdAt = obj.createdAt.substring(0, 10),
            dayName = convertDateToDayOfWeek(
                dateInput = obj.createdAt
            ),
            type = obj.type
        )

    }//end objectConvertor

}//end BloodSugarEntityToSimpleBloodSugarModelMapper