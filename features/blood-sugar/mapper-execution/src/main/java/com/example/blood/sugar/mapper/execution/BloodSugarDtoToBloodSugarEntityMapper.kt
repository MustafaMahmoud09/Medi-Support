package com.example.blood.sugar.mapper.execution

import com.example.blood.sugar.data.source.local.data.entity.execution.bloodSugar.BloodSugarEntity
import com.example.blood.sugar.domain.dto.declarations.IBloodSugarDto
import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarDtoToBloodSugarEntityMapper

class BloodSugarDtoToBloodSugarEntityMapper : IBloodSugarDtoToBloodSugarEntityMapper {

    override fun listConvertor(list: List<IBloodSugarDto>): List<IBloodSugarEntity> {

        return list.map { bloodSugarDto ->
            objectConvertor(
                obj = bloodSugarDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: IBloodSugarDto): IBloodSugarEntity {

        return BloodSugarEntity(
            id = obj.id ?: 0,
            level = (obj.level ?: 0).toDouble(),
            createdAt = obj.createdAt ?: "",
            type = obj.advice?.key ?: "",
            advice = obj.advice?.advice ?: "",
            userId = obj.userId ?: 0
        )

    }//end objectConvertor

}//end BloodSugarDtoToBloodSugarEntityMapper