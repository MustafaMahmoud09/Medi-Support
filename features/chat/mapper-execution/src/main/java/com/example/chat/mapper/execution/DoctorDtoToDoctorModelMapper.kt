package com.example.chat.mapper.execution

import com.example.chat.domain.dto.declarations.doctorInfo.IFetch
import com.example.chat.domain.mapper.declarations.child.IDoctorDtoToDoctorModelMapper
import com.example.chat.domain.model.DoctorModel

class DoctorDtoToDoctorModelMapper(
    private val baseUrl: String
): IDoctorDtoToDoctorModelMapper {

    override fun listConvertor(
        list: List<IFetch>
    ): List<DoctorModel> {

        return list.map {
            objectConvertor(
                obj = it
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IFetch
    ): DoctorModel {

        return DoctorModel(
            doctorName = (obj.firstName ?: "") + " " + (obj.lastName ?: ""),
            activeStatus = obj.activeStatus == 1,
            imageUrl = baseUrl + obj.avatar
        )

    }//end objectConvertor

}//end DoctorDtoToDoctorModelMapper