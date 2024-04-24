package com.example.blood.pressure.domain.mapper.declarations.child

import com.example.blood.pressure.domain.model.ChartBloodPressureModel

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

}//end IDescBloodPressureDtoToChartBloodPressureModelMapper