package com.example.online.booking.data.source.local.data.entity.execution.onlineBooking

import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

object OnlineBookingInfo {

    const val ONLINE_BOOKING_TABLE_NAME = "online_bookings"

    const val ID_COLUMN_NAME = "online_booking_id"

    const val USER_ID_COLUMN_NAME = UserInfo.ID_COLUMN_NAME

    const val DOCTOR_NAME_COLUMN_NAME = "doctor_name"

    const val BOOKING_STATUS_COLUMN_NAME = "booking_status"

    const val DOCTOR_STATUS_COLUMN_NAME = "doctor_active_status"

    const val SPECIALIZATION_COLUMN_NAME = "specialization"

    const val CREATED_AT_COLUMN_NAME = "created_at"

}//end OnlineBookingInfo