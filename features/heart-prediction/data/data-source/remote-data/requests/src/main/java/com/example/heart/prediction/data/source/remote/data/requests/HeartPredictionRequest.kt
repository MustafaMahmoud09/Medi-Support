package com.example.heart.prediction.data.source.remote.data.requests

import com.example.heart.prediction.data.source.remote.data.dto.execution.HeartPredictionDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HeartPredictionRequest {

    //function for make request on server for make heart disease prediction
    @FormUrlEncoded
    @POST("predict")
    suspend fun makePrediction(
       @Field("BMI") bmi: Float,
       @Field("PhysicalHealth") physicalHealth: Float,
       @Field("MentalHealth") mentalHealth: Float,
       @Field("SleepTime") sleepTime: Float,
       @Field("AgeCategory") ageCategory: Int,
       @Field("Race") race: Int,
       @Field("Diabetic") diabetic: Int,
       @Field("GenHealth") genHealth: Int,
       @Field("Sex") sex: Int,
       @Field("Smoking") smoking: Int,
       @Field("AlcoholDrinking") alcoholDrinking: Int,
       @Field("Stroke") stroke: Int,
       @Field("DiffWalking") diffWalking: Int,
       @Field("PhysicalActivity") physicalActivity: Int,
       @Field("Asthma") asthma: Int,
       @Field("KidneyDisease") kidneyDisease: Int,
       @Field("SkinCancer") skinCancer: Int,
    ): Response<HeartPredictionDto>

}//end HeartPredictionRequest