package com.example.blood.pressure.data.repository.execution

import com.example.blood.pressure.data.repository.execution.cacheHelperDeclarations.IServerBloodPressureRepositoryHelper
import com.example.blood.pressure.data.source.remote.data.requests.BloodPressureRequest
import com.example.blood.pressure.domain.dto.declarations.descMeasurement.IDescBloodPressureResponseDto
import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.flow.Flow

class BloodPressureRepositoryImpl(
    private val bloodPressureRequest: BloodPressureRequest,
    private val wrapper: ResponseWrapper,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject,
    private val serverBloodPressureRepositoryHelper: IServerBloodPressureRepositoryHelper
) : IBloodPressureRepository {

    //function for create new blood pressure record in global database
    override suspend fun createNewBloodPressureRecord(
        systolic: Int,
        diastolic: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            bloodPressureRequest.createNewBloodPressureRecord(
                systolic = systolic,
                diastolic = diastolic
            )
        }//end wrapper scope

    }//end createNewBloodPressureRecord


    //function for provide page blood pressure
    override suspend fun getPageBloodPressure(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IBloodPressureEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //get page contain on blood pressure records
        val lastPage = serverBloodPressureRepositoryHelper.getPageBloodPressureRecordsFromSever(
            userAuthId = userAuthId,
            page = page,
            pageSize = pageSize
        )

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.bloodPressureDao().selectPageBloodPressure(
                page = page,
                pageSize = pageSize,
                userId = userAuthId
            )
        )

    }//end getAllArticles


    //function for get latest blood pressure record from global database
    //cache data after that in local database
    //return result from local database
    override suspend fun getLatestBloodPressureRecord()
            : Flow<List<IBloodPressureEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //make request on server for get last blood pressure records
        serverBloodPressureRepositoryHelper.getLastBloodPressureRecordsFromServer(
            userAuthId = userAuthId
        )

        return localDatabase.bloodPressureDao().getLatestBloodPressureRecord(
            userId = userAuthId,
            limit = 1
        )

    }//end getLatestBloodPressureRecord


    //function for get latest history record and cache this data in local data base
    override suspend fun getLatestHistoryBloodPressureRecords(): Flow<List<IBloodPressureEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        return localDatabase.bloodPressureDao().getLatestBloodPressureRecord(
            userId = userAuthId,
            limit = 3
        )

    }//end getLatestHistoryBloodPressureRecords


    //function for make request on server for provide systolic measurements
    override suspend fun getSystolicMeasurements()
            : Flow<Status<EffectResponse<IDescBloodPressureResponseDto>>> {

        //make flow for emit result of request if internet exist
        //return flow with status function result contain on diastolic measurements
        return wrapper.infiniteWrapper {
            bloodPressureRequest.getLatestSystolicMeasurement()
        }//end infiniteWrapper

    }//end getLatestSystolicMeasurement


    //function for make request on server for provide diastolic measurements
    override suspend fun getDiastolicMeasurements()
            : Flow<Status<EffectResponse<IDescBloodPressureResponseDto>>> {

        //return flow with status function result contain on diastolic measurements
        return wrapper.infiniteWrapper {
            bloodPressureRequest.getLatestDiastolicMeasurement()
        }//end infiniteWrapper

    }//end getLatestDiastolicMeasurement

}//end BloodPressureRepositoryImpl