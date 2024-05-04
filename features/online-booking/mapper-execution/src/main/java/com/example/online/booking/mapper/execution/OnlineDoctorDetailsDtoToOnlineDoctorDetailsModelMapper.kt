package com.example.online.booking.mapper.execution

import com.example.online.booking.domain.dto.declarations.doctorDetails.IOnlineDoctorDetailsDto
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
import com.example.online.booking.domain.model.OnlineDoctorDetailsModel

class OnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper(
    private val baseImageUrl: String
) : IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper {

    override fun objectConvertor(
        obj: IOnlineDoctorDetailsDto
    ): OnlineDoctorDetailsModel {

        return OnlineDoctorDetailsModel(
            id = obj.id ?: 0,
            name = (obj.firstName ?: "") + " " + (obj.lastName ?: ""),
            specialization = obj.specialization ?: "",
            userRating = obj.userRating ?: 0f,
            image = baseImageUrl + (obj.avatar ?: ""),
            rating = obj.averageRating ?: 0f,
            price = (obj.price ?: 0).toString(),
            bio = obj.bio ?: "",
            clinicLocation = obj.clinicLocation ?: "",
            phone = obj.phone ?: "",
            active = obj.activeStatus == 1
        )

    }//end objectConvertor

}//end OfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper