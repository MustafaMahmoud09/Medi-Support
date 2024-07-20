package com.example.heartrate.data.repository

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import com.example.database_creator.MediSupportDatabase
import com.example.heart.rate.data.source.remote.data.requests.HeartRateRequest
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heartrate.data.repository.cacheHelperDeclarations.IServerHeartRateRepositoryHelper
import com.example.heartrate.data.repository.cacheHelperExecution.ServerHeartRateRepositoryHelper
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.flow.Flow

class HeartRateRepositoryImpl(
    private val context: Context,
    private val wrapper: ResponseWrapper,
    private val heartRateRequest: HeartRateRequest,
    private val serverHeartRateHelper: IServerHeartRateRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject
) : IHeartRateRepository {

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


    //function for make request on server for create new heart rate record
    override suspend fun createNewHeartRateRecord(
        rate: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            heartRateRequest.createHeartRateRecord(
                rate = rate
            )
        }//end wrapper

    }//end createNewHeartRateRecord


    //function for make request for get latest seven records from server
    //after that cache data in local database
    override suspend fun getLastWeekMeasurement(): Flow<List<IHeartRateEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //make request on server for get last seven measurement
        //after that cache result in local data base
        serverHeartRateHelper.getLastWeekHeartRateRecordsFromServer(
            userAuthId = userAuthId
        )

        return localDatabase.heartRateDao().getLatestHeartRateRecord(
            userId = userAuthId,
            limit = 7
        )

    }//end getLastWeekMeasurement


    //function for provide number of latest blood sugar record
    override suspend fun getLatestMeasurements(
        numberOfMeasurement: Long
    ): Flow<List<IHeartRateEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        return localDatabase.heartRateDao().getLatestHeartRateRecord(
            userId = userAuthId,
            limit = numberOfMeasurement
        )

    }//end getLatestMeasurements


    //function for make request on server for get page contain on records
    override suspend fun getPageMeasurements(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IHeartRateEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //function for make request on server for get page contain on heart rate records
        //after that cache data in local database
        val lastPage = serverHeartRateHelper.getPageHeartRateRecordsFromSever(
            userAuthId = userAuthId,
            page = page,
            pageSize = pageSize
        )

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.heartRateDao().selectPageHeartRate(
                page = page,
                pageSize = pageSize,
                userId = userAuthId
            )
        )

    }//end getPageMeasurements


    override suspend fun logoutFromLocalDatabase(){

        //get user auth token
        val accessToken = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()

        //delete user account from local database
        localDatabase.userDao().deleteUserAccountByToken(
            token = accessToken
        )

        //remove access token from shared preferences
        sharedPreferencesAccessObject.accessTokenManager().setAccessToken(
            value = ""
        )

    }//end logoutFromLocalDatabase

}//end HeartRateRepositoryImpl