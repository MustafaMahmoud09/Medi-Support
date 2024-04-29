package com.example.bmi.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIDtoToBMIEntityMapper
import com.example.bmi.data.source.local.data.entity.execution.bmi.BMIEntity
import com.example.bmi.domain.dto.declarations.IBMIDto
import com.example.bmi.domain.entity.declarations.IBMIEntity

class BMIDtoToBMIEntityMapper : IBMIDtoToBMIEntityMapper {

    override fun listConvertor(list: List<IBMIDto>): List<IBMIEntity> {

        return list.map { bloodSugarDto ->
            objectConvertor(
                obj = bloodSugarDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: IBMIDto): IBMIEntity {

        return BMIEntity(
            id = obj.id ?: 0,
            result = (obj.result ?: 0).toDouble(),
            createdAt = obj.createdAt ?: "",
            type = obj.key ?: "",
            advice = obj.advice ?: "",
            userId = obj.userId ?: 0
        )

    }//end objectConvertor

}//end BMIDtoToBMIEntityMapper