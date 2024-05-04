package com.example.online.booking.mapper.execution

import com.example.online.booking.domain.dto.declarations.IOnlineDoctorDto
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDtoToOnlineDoctorModelMapper
import com.example.online.booking.domain.model.OnlineDoctorModel


class OnlineDoctorDtoToOnlineDoctorModelMapper(
    private val baseImageUrl: String
) : IOnlineDoctorDtoToOnlineDoctorModelMapper {

    override fun listConvertor(
        list: List<IOnlineDoctorDto>
    ): List<OnlineDoctorModel> {

        return list.map { offlineDoctorDto ->
            objectConvertor(
                obj = offlineDoctorDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IOnlineDoctorDto
    ): OnlineDoctorModel {

        return OnlineDoctorModel(
            id = obj.id ?: 0,
            name = (obj.firstName ?: "") + " " + (obj.lastName ?: ""),
            clinicLocation = obj.clinicLocation ?: "",
            workingHours = obj.workingHours ?: 0,
            rate = obj.averageRating ?: 0f,
            image = baseImageUrl + (obj.avatar ?: ""),
            active = obj.activeStatus == 1
        )

    }//end objectConvertor

}//end OfflineDoctorDtoToOfflineDoctorModelMapper