package com.example.heart.rate.data.source.entity.execution.heartRate

import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

object HeartRateInfo {

    const val HEART_RATE_TABLE_NAME = "heart_rate"

    const val ID_COLUMN_NAME = "heart_rate_id"

    const val USER_ID_COLUMN_NAME = UserInfo.ID_COLUMN_NAME

    const val RATE_COLUMN_NAME = "rate"

    const val TYPE_COLUMN_NAME = "type"

    const val ADVICE_COLUMN_NAME = "advice"

    const val CREATED_AT_COLUMN_NAME = "created_at"

}//end HeartRateInfo