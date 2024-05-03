package com.example.offline.booking.mapper.execution

import com.example.offline.booking.domain.dto.declarations.doctorDetails.IDoctorDetailsDto
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
import com.example.offline.booking.domain.model.DateModel
import com.example.offline.booking.domain.model.OfflineDoctorDetailsModel

class OfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper(
    private val baseImageUrl: String
) : IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper {

    override fun objectConvertor(
        obj: IDoctorDetailsDto
    ): OfflineDoctorDetailsModel {

        return OfflineDoctorDetailsModel(
            id = obj.id ?: 0,
            name = (obj.firstName ?: "") + " " + (obj.lastName ?: ""),
            specialization = obj.specialization ?: "",
            userRating = obj.userRating ?: 0f,
            image = baseImageUrl + (obj.avatar ?: ""),
            rating = obj.avgRating ?: 0f,
            price = (obj.price ?: 0).toString(),
            bio = obj.bio ?: "",
            clinicLocation = obj.clinicLocation ?: "",
            dates = (obj.dates ?: emptyList()).map { date ->
                DateModel(
                    id = date?.id ?: 0,
                    date = date?.date ?: ""
                )
            },
        )

    }//end objectConvertor

}//end OfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper