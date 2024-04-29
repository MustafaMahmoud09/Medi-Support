package com.example.bmi.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToChartBMIModelMapper
import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.bmi.domain.model.ChartBMIModel

class BMIEntityToChartBMIModelMapper
    : IBMIEntityToChartBMIModelMapper {

    override fun listConvertor(
        list: List<IBMIEntity>
    ): List<ChartBMIModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(
                obj = bloodSugarEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IBMIEntity
    ): ChartBMIModel {

        return ChartBMIModel(
            result = obj.result,
            dayName = convertDateToDayOfWeek(
                obj.createdAt
            )
        )

    }//end objectConvertor

}//end HeartRateEntityToChartHeartRateModelMapper