package com.example.heartprediction.presentation.uiState.state

data class HeartPredictionUiState(
    val bmi: String = "",
    val physicalHealth: String = "",
    val mentalHealth: String = "",
    val sleepTime: String = "",
    val menuExpanded: Boolean = false,
    val startRunning: Boolean = true,
    val numberOfFieldIsFocus: Int = 0,
    val sexData: FieldDataSelected = FieldDataSelected(),
    val raceData: FieldDataSelected = FieldDataSelected(),
    val diabeticData: FieldDataSelected = FieldDataSelected(),
    val ageCategoryData: FieldDataSelected = FieldDataSelected(),
    val genHealthData: FieldDataSelected = FieldDataSelected(),
    val smokingData: FieldDataSelected = FieldDataSelected(),
    val alcoholDrinkingData: FieldDataSelected = FieldDataSelected(),
    val strokeData: FieldDataSelected = FieldDataSelected(),
    val diffWalkingData: FieldDataSelected = FieldDataSelected(),
    val physicalActivityData: FieldDataSelected = FieldDataSelected(),
    val asthmaData: FieldDataSelected = FieldDataSelected(),
    val kidneyDiseaseData: FieldDataSelected = FieldDataSelected(),
    val skinCancerData: FieldDataSelected = FieldDataSelected(),
    val predictHeartDiseaseStatus: PredictHeartDiseaseStatus = PredictHeartDiseaseStatus(),
    val resultClass: Int = -1,
)

data class FieldDataSelected(
    val id: Int = -1,
    val name: String = ""
)

data class PredictHeartDiseaseStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val dataNotComplete: Boolean = false,
    val unAuthorized: Boolean = false
)