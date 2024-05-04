package com.example.offline.booking.data.source.local.data.entity.execution.offlineBooking

import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

object OfflineBookingInfo {

    const val OFFLINE_BOOKING_TABLE_NAME = "offline_bookings"

    const val ID_COLUMN_NAME = "offline_booking_id"

    const val USER_ID_COLUMN_NAME = UserInfo.ID_COLUMN_NAME

    const val DOCTOR_NAME_COLUMN_NAME = "doctor_name"

    const val SPECIALIZATION_COLUMN_NAME = "specialization"

    const val TIME_COLUMN_NAME = "time"

    const val DATE_COLUMN_NAME = "date"

    const val CLINIC_LOCATION_COLUMN_NAME = "clinic_location"

    const val CREATED_AT_COLUMN_NAME = "created_at"

}//end OnlineBookingInfo