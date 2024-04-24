package com.example.blood.pressure.mapper.execution

import com.example.blood.pressure.domain.mapper.declarations.child.IDescBloodPressureDtoToChartBloodPressureModelMapper
import com.example.blood.pressure.domain.model.ChartBloodPressureModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DescBloodPressureDtoToChartBloodPressureModelMapper :
    IDescBloodPressureDtoToChartBloodPressureModelMapper {

    override fun listConvertor(
        list: Map<String, Long>
    ): List<ChartBloodPressureModel> {

        return list.map { entity ->
            objectConvertor(
                obj = entity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: Map.Entry<String, Long>
    ): ChartBloodPressureModel {

        return ChartBloodPressureModel(
            dayName = convertDateToDayOfWeek(
                dateInput = obj.key
            ),
            measurementValue = obj.value,
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

}//end DescBloodPressureDtoToChartBloodPressureModelMapper