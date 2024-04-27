package com.example.blood.pressure.data.source.local.data.entity.execution.bloodPressure

import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

object BloodPressureInfo {

    const val BLOOD_PRESSURE_TABLE_NAME = "blood_pressure"

    const val ID_COLUMN_NAME = "blood_pressure_id"

    const val USER_ID_COLUMN_NAME = UserInfo.ID_COLUMN_NAME

    const val SYSTOLIC_COLUMN_NAME = "systolic"

    const val DIASTOLIC_COLUMN_NAME = "diastolic"

    const val TYPE_COLUMN_NAME = "type"

    const val ADVICE_COLUMN_NAME = "advice"

    const val CREATED_AT_COLUMN_NAME = "created_at"

}//end BloodPressureInfo