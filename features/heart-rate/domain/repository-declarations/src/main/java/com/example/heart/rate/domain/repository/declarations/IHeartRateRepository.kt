package com.example.heart.rate.domain.repository.declarations

interface IHeartRateRepository {

    fun isLightSensorExist(): Boolean

    fun isFlashCameraExist(): Boolean

}//end IHeartRateRepository