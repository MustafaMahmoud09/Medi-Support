package com.example.blood.sugar.data.repository.execution

import com.example.blood.sugar.data.repository.execution.cacheHelperDeclarations.IServerBloodSugarRepositoryHelper
import com.example.blood.sugar.data.source.remote.data.dto.execution.status.BloodSugarStatusResponseDto
import com.example.blood.sugar.data.source.remote.data.requests.BloodSugarRequest
import com.example.blood.sugar.domain.dto.declarations.status.IBloodSugarStatusResponseDto
import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.flow.Flow

class BloodSugarRepositoryImpl(
    private val bloodSugarRequest: BloodSugarRequest,
    private val wrapper: ResponseWrapper,
    private val serverBloodSugarRepositoryHelper: IServerBloodSugarRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject
) : IBloodSugarRepository {

    //function for make request on server for create new blood sugar record
    override suspend fun createNewBloodSugarRecord(
        level: Float,
        statusId: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            bloodSugarRequest.createBloodSugarRecord(
                level = level,
                statusId = statusId
            )
        }//end wrapper

    }//end createNewBloodSugarRecord


    //function for make request on server for get blood sugar status
    override suspend fun getBloodSugarStatus()
            : Flow<Status<EffectResponse<IBloodSugarStatusResponseDto>>> {

        //return flow with status function result contain on diastolic measurements
        return wrapper.infiniteWrapper<IBloodSugarStatusResponseDto, BloodSugarStatusResponseDto> {
            bloodSugarRequest.getBloodSugarStatus()
        }//end wrapper

    }//end getBloodSugarStatus


    //function for make request for get latest seven records from server
    //after that cache data in local database
    override suspend fun getLastWeekMeasurement(): Flow<List<IBloodSugarEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //get latest blood sugar records from server and store this records in local
        serverBloodSugarRepositoryHelper.getLastWeekBloodSugarRecordsFromServer(
            userAuthId = userAuthId
        )

        return localDatabase.bloodSugarDao().getLatestBloodSugarRecord(
            userId = userAuthId,
            limit = 7
        )

    }//end getLastWeekMeasurement


    //function for provide number of latest blood sugar record
    override suspend fun getLatestMeasurements(
        numberOfMeasurement: Long
    ): Flow<List<IBloodSugarEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id


        return localDatabase.bloodSugarDao().getLatestBloodSugarRecord(
            userId = userAuthId,
            limit = numberOfMeasurement
        )

    }//end getLatestMeasurements


    //function for make request on server for get page contain on records
    override suspend fun getPageMeasurements(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IBloodSugarEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //get records from server and store this records in local
        val lastPage = serverBloodSugarRepositoryHelper.getPageBloodSugarRecordsFromSever(
            userAuthId = userAuthId,
            page = page,
            pageSize = pageSize
        )

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.bloodSugarDao().selectPageBloodSugar(
                page = page,
                pageSize = pageSize,
                userId = userAuthId
            )
        )

    }//end getPageMeasurements

}//end BloodSugarRepositoryImpl