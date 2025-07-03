package com.example.heart.prediction.data.repository.execution

import com.example.database_creator.MediSupportDatabase
import com.example.heart.prediction.data.source.remote.data.dto.execution.HeartPredictionDto
import com.example.heart.prediction.data.source.remote.data.requests.HeartPredictionRequest
import com.example.heart.prediction.domain.dto.declarations.IHeartPredictionDto
import com.example.heart.prediction.domain.repository.declarations.IHeartPredictionRepository
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.flow.Flow

class HeartPredictionRepositoryImpl(
    private val wrapper: ResponseWrapper,
    private val heartPredictionRequest: HeartPredictionRequest,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject
) : IHeartPredictionRepository {

    //function for make request on server for heart disease prediction
    override suspend fun predictHeartDisease(
        bmi: Float,
        physicalHealth: Float,
        mentalHealth: Float,
        sleepTime: Float,
        ageCategory: Int,
        race: Int,
        diabetic: Int,
        sex: Int,
        smoking: Int,
        alcoholDrinking: Int,
        stroke: Int,
        diffWalking: Int,
        physicalActivity: Int,
        asthma: Int,
        kidneyDisease: Int,
        skinCancer: Int,
        genHealth: Int
    ): Flow<Status<EffectResponse<IHeartPredictionDto>>> {

        return wrapper.wrapper<IHeartPredictionDto, HeartPredictionDto> {
            heartPredictionRequest.makePrediction(
                bmi = bmi,
                physicalHealth = physicalHealth,
                mentalHealth = mentalHealth,
                sleepTime = sleepTime,
                ageCategory = ageCategory,
                race = race,
                diabetic = diabetic,
                genHealth = genHealth,
                sex = sex,
                smoking = smoking,
                alcoholDrinking = alcoholDrinking,
                stroke = stroke,
                diffWalking = diffWalking,
                physicalActivity = physicalActivity,
                asthma = asthma,
                kidneyDisease = kidneyDisease,
                skinCancer = skinCancer
            )
        }//end wrapper

    }//end predictHeartDisease


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


}//end HeartPredictionRepositoryImpl