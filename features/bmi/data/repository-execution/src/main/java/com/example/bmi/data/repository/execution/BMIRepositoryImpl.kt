package com.example.bmi.data.repository.execution

import com.example.bmi.data.repository.execution.cacheHelperDeclarations.IServerBMIRepositoryHelper
import com.example.bmi.data.source.remote.data.requests.BMIRequest
import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.flow.Flow

class BMIRepositoryImpl(
    private val wrapper: ResponseWrapper,
    private val bmiRequest: BMIRequest,
    private val serverBMIHelper: IServerBMIRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject
) : IBMIRepository {

    //function for make request on server for create new bmi record
    override suspend fun createNewBMIRecord(
        gender: Int,
        age: Int,
        height: Float,
        weight: Float
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            bmiRequest.createBMIRecord(
                gender = gender,
                age = age,
                height = height,
                weight = weight
            )
        }//end wrapper

    }//end createNewBMIRecord


    //function for make request for get latest seven records from server
    //after that cache data in local database
    override suspend fun getLastMeasurement(): Flow<List<IBMIEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //make request on server for get last seven measurement
        //after that cache result in local data base
        serverBMIHelper.getLastWeekBMIRecordsFromServer(
            userAuthId = userAuthId
        )

        return localDatabase.bmiDao().getLatestBMIRecord(
            userId = userAuthId,
            limit = 1
        )

    }//end getLastWeekMeasurement


    //function for provide number of latest bmi record
    override suspend fun getLatestMeasurements(
        numberOfMeasurement: Long
    ): Flow<List<IBMIEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        return localDatabase.bmiDao().getLatestBMIRecord(
            userId = userAuthId,
            limit = numberOfMeasurement
        )

    }//end getLatestMeasurements


    //function for make request on server for get page contain on records
    override suspend fun getPageMeasurements(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IBMIEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //function for make request on server for get page contain on heart rate records
        //after that cache data in local database
        val lastPage = serverBMIHelper.getPageBMIRecordsFromSever(
            userAuthId = userAuthId,
            page = page,
            pageSize = pageSize
        )

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.bmiDao().selectPageBMI(
                page = page,
                pageSize = pageSize,
                userId = userAuthId
            )
        )

    }//end getPageMeasurements

}//end HeartRateRepositoryImpl