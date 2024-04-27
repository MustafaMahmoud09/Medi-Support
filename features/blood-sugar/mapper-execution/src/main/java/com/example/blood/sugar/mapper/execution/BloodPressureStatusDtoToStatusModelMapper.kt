package com.example.blood.sugar.mapper.execution

import com.example.blood.sugar.domain.dto.declarations.status.IBloodSugarStatusDto
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodPressureStatusDtoToStatusModelMapper
import com.example.blood.sugar.domain.model.StatusModel

class BloodPressureStatusDtoToStatusModelMapper : IBloodPressureStatusDtoToStatusModelMapper {

    override fun listConvertor(
        list: List<IBloodSugarStatusDto>
    ): List<StatusModel> {

        return list.map { statusDto ->
            objectConvertor(
                obj = statusDto
            )
        }

    }//end listConvertor

    override fun objectConvertor(
        obj: IBloodSugarStatusDto
    ): StatusModel {

        return StatusModel(
            id = obj.id ?: 0,
            status = obj.statusName ?: ""
        )

    }//end objectConvertor

}//end BloodPressureStatusDtoToStatusModelMapper