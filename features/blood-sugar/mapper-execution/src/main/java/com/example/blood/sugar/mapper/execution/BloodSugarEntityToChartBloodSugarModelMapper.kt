package com.example.blood.sugar.mapper.execution

import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToChartBloodSugarModelMapper
import com.example.blood.sugar.domain.model.ChartBloodSugarModel

class BloodSugarEntityToChartBloodSugarModelMapper : IBloodSugarEntityToChartBloodSugarModelMapper {

    override fun listConvertor(
        list: List<IBloodSugarEntity>
    ): List<ChartBloodSugarModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(
                obj = bloodSugarEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IBloodSugarEntity
    ): ChartBloodSugarModel {

        return ChartBloodSugarModel(
            level = obj.level,
            dayName = convertDateToDayOfWeek(
                obj.createdAt
            )
        )

    }//end objectConvertor

}//end BloodSugarEntityToChartBloodSugarModelMapper