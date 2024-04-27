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



}//end DescBloodPressureDtoToChartBloodPressureModelMapper