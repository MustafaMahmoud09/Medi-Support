package com.example.offline.booking.mapper.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IOfflineDoctorDtoToOfflineDoctorModelMapper
import com.example.offline.booking.domain.dto.declarations.IOfflineDoctorDto
import com.example.offline.booking.domain.model.OfflineDoctorModel

class OfflineDoctorDtoToOfflineDoctorModelMapper(
    private val baseImageUrl: String
) : IOfflineDoctorDtoToOfflineDoctorModelMapper {

    override fun listConvertor(
        list: List<IOfflineDoctorDto>
    ): List<OfflineDoctorModel> {

        return list.map { offlineDoctorDto ->
            objectConvertor(
                obj = offlineDoctorDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IOfflineDoctorDto
    ): OfflineDoctorModel {

        return OfflineDoctorModel(
            id = obj.id ?: 0,
            name = (obj.firstName ?: "") + " " + (obj.lastName ?: ""),
            clinicLocation = obj.clinicLocation ?: "",
            workingHours = obj.workingHours ?: 0,
            rate = obj.rate ?: 0f,
            image = baseImageUrl + (obj.avatar ?: "")
        )

    }//end objectConvertor

}//end OfflineDoctorDtoToOfflineDoctorModelMapper