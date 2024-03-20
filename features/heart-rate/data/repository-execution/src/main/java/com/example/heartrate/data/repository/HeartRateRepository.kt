package com.example.heartrate.data.repository

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository

class HeartRateRepository(
   private val context: Context
): IHeartRateRepository {//end HeartRateRepository

    //function for check device contain on light sensor or no
    override fun isLightSensorExist(): Boolean {

        //get sensor manager here
        val sensorManager =
            context.getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        //get list contain on heart rate sensors here
        val lightSensors =
            sensorManager?.getSensorList(Sensor.TYPE_LIGHT)

        //check heart rate sensors list is empty or no here
        return !lightSensors.isNullOrEmpty()

    }//end isLightSensorExist

    //function for check device contain on flash camera or no
    override fun isFlashCameraExist(): Boolean {

        //get flash camera state in device
        val isFlashExist =
            context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        return isFlashExist

    }//end isFlashCameraExist

}