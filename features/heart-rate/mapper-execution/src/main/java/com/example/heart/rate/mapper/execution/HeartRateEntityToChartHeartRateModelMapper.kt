package com.example.heart.rate.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToChartHeartRateModelMapper
import com.example.heart.rate.domain.domain.model.ChartHeartRateModel
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity

class HeartRateEntityToChartHeartRateModelMapper : IHeartRateEntityToChartHeartRateModelMapper {

    override fun listConvertor(
        list: List<IHeartRateEntity>
    ): List<ChartHeartRateModel> {

        return list.map { bloodSugarEntity ->
            objectConvertor(
                obj = bloodSugarEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IHeartRateEntity
    ): ChartHeartRateModel {

        return ChartHeartRateModel(
            rate = obj.rate,
            dayName = convertDateToDayOfWeek(
                obj.createdAt
            )
        )

    }//end objectConvertor

}//end HeartRateEntityToChartHeartRateModelMapper