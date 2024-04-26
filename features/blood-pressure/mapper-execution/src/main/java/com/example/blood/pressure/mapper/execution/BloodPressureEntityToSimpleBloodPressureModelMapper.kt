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


    //function for convert date to day of week
    private fun convertDateToDayOfWeek(
        dateInput: String
    ): String {

        //format date to d-M-yyyy
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        //convert string to date
        val date = LocalDate.parse(dateInput.subSequence(0, 10), formatter)

        //get day of week
        val dayOfWeek = date.dayOfWeek.name

        //format day to number of character
        val day = if (dayOfWeek.length > 3) {
            dayOfWeek.substring(0, 3)
        } else {
            dayOfWeek
        }

        return if (day.length > 1) {
            day[0].toString().uppercase() + day.substring(1).lowercase()
        } else {
            day.uppercase()
        }

    }//end convertDateToDayOfWeek


}//end BloodPressureEntityToSimpleBloodPressureModelMapper