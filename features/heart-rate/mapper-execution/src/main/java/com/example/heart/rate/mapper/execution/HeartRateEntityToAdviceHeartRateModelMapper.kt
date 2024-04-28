package com.example.heart.rate.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToAdviceHeartRateModelMapper
import com.example.heart.rate.domain.domain.model.AdviceHeartRateModel
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity


class HeartRateEntityToAdviceHeartRateModelMapper :
    IHeartRateEntityToAdviceHeartRateModelMapper {

    override fun listConvertor(
        list: List<IHeartRateEntity>
    ): List<AdviceHeartRateModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(bloodSugarEntity)
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IHeartRateEntity
    ): AdviceHeartRateModel {

        return AdviceHeartRateModel(
            id = obj.id,
            rate = obj.rate,
            type = obj.type,
            advice = obj.advice ?: "",
            createdAt = obj.createdAt
        )

    }//end objectConvertor

}//end HeartRateEntityToAdviceHeartRateModelMapper