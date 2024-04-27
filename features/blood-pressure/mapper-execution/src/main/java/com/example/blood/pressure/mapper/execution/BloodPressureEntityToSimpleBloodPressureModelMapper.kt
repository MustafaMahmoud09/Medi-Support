package com.example.blood.pressure.mapper.execution

import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.blood.pressure.domain.mapper.declarations.child.IBloodPressureEntityToSimpleBloodPressureModelMapper
import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BloodPressureEntityToSimpleBloodPressureModelMapper :
    IBloodPressureEntityToSimpleBloodPressureModelMapper {

    override fun listConvertor(
        list: List<IBloodPressureEntity>
    ): List<SimpleBloodPressureModel> {

        return list.map { bloodPressureEntity ->
            objectConvertor(
                obj = bloodPressureEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IBloodPressureEntity
    ): SimpleBloodPressureModel {

        return SimpleBloodPressureModel(
            id = obj.id,
            systolic = obj.systolic,
            diastolic = obj.diastolic,
            type = obj.type,
            createdAt = obj.createdAt,
            dayName = convertDateToDayOfWeek(
                dateInput = obj.createdAt
            )
        )

    }//end objectConvertor

}//end BloodPressureEntityToSimpleBloodPressureModelMapper