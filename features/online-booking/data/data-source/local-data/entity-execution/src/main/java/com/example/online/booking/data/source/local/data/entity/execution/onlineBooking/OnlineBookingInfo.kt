package com.example.bmi.data.source.local.data.entity.execution.bmi

import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

object BMIInfo {

    const val BMI_TABLE_NAME = "bmi"

    const val ID_COLUMN_NAME = "bmi_id"

    const val USER_ID_COLUMN_NAME = UserInfo.ID_COLUMN_NAME

    const val RESULT_COLUMN_NAME = "result"

    const val TYPE_COLUMN_NAME = "type"

    const val ADVICE_COLUMN_NAME = "advice"

    const val CREATED_AT_COLUMN_NAME = "created_at"

}//end BMIInfo