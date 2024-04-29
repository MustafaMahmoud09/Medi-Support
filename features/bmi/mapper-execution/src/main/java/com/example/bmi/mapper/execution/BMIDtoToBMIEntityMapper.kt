package com.example.heart.rate.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateDtoToHeartRateEntityMapper
import com.example.heart.rate.data.source.entity.execution.heartRate.HeartRateEntity
import com.example.heart.rate.domain.dto.declarations.IHeartRateDto
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity

class HeartRateDtoToHeartRateEntityMapper : IHeartRateDtoToHeartRateEntityMapper {

    override fun listConvertor(list: List<IHeartRateDto>): List<IHeartRateEntity> {

        return list.map { bloodSugarDto ->
            objectConvertor(
                obj = bloodSugarDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: IHeartRateDto): IHeartRateEntity {

        return HeartRateEntity(
            id = obj.id ?: 0,
            rate = obj.heartRate ?: 0,
            createdAt = obj.createdAt ?: "",
            type = obj.advice?.key ?: "",
            advice = obj.advice?.advice ?: "",
            userId = obj.userId ?: 0
        )

    }//end objectConvertor

}//end HeartRateDtoToHeartRateEntityMapper