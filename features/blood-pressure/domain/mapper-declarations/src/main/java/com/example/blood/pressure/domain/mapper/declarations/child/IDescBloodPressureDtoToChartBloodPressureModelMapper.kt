package com.example.blood.pressure.domain.mapper.declarations.child

import com.example.blood.pressure.domain.model.ChartBloodPressureModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface IDescBloodPressureDtoToChartBloodPressureModelMapper {


    /**
     * abstract function tack list have input type and convert it to list have output type
     *
     * @return List<OT>
     **/
    fun listConvertor(list: Map<String, Long>): List<ChartBloodPressureModel>


    /**
     * abstract function tack object have input type and convert it to output type
     *
     * @return ST
     **/
    fun objectConvertor(obj: Map.Entry<String, Long>): ChartBloodPressureModel


    //function for convert date to day of week
    fun convertDateToDayOfWeek(
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

}//end IDescBloodPressureDtoToChartBloodPressureModelMapper