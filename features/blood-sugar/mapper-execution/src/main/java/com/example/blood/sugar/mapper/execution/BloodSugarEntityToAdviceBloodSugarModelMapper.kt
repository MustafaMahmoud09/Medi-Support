package com.example.blood.sugar.mapper.execution

import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToAdviceBloodSugarModelMapper
import com.example.blood.sugar.domain.model.AdviceBloodSugarModel

class BloodSugarEntityToAdviceBloodSugarModelMapper :
    IBloodSugarEntityToAdviceBloodSugarModelMapper {

    override fun listConvertor(
        list: List<IBloodSugarEntity>
    ): List<AdviceBloodSugarModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(bloodSugarEntity)
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IBloodSugarEntity
    ): AdviceBloodSugarModel {

        return AdviceBloodSugarModel(
            id = obj.id,
            level = obj.level,
            type = obj.type,
            advice = obj.advice ?: "",
            createdAt = obj.createdAt
        )

    }//end objectConvertor

}//end BloodSugarEntityToAdviceBloodSugarModelMapper