@file:OptIn(ExperimentalFoundationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.screens.home.child

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharedui.uiElement.navigation.data.TabData
import com.example.sharedui.R
import com.damanhour.Graduation.medisupport.ui.uiElement.components.items.HealthCareSection
import com.damanhour.Graduation.medisupport.ui.uiElement.components.items.HeartPredictionSection
import com.damanhour.Graduation.medisupport.ui.uiElement.components.items.TabsSection
import com.example.offlinebooking.presentation.uiElement.screens.TopOfflineDoctorsScreen
import com.example.onlinebooking.presentation.uiElement.screens.TopOnlineDoctorsScreen
import com.damanhour.Graduation.medisupport.ui.uiState.state.TopDoctorsUiState
import com.damanhour.Graduation.medisupport.ui.uiState.viewModel.TopDoctorsViewModel
import com.example.sharedui.uiElement.components.composable.LinkView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.navigation.transitions.animateScrollToPage
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoBold
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlin.reflect.KFunction1

//function for collect state and execute action from view model
@Composable
internal fun TopDoctorsScreen(
    viewModel: TopDoctorsViewModel = hiltViewModel(),
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickSeeAll: () -> Unit,
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    navigateToOnlineBookingNavGraph: (Int) -> Unit,
    navigateToOfflineBookingDestination: (Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    val state = viewModel.state.collectAsState()

    //call home content function
    TopDoctorsContent(
        dimen = dimen,
        theme = theme,
        onClickSeeAll = onClickSeeAll,
        onClickHeartPrediction = navigateToHeartPredictionNavGraph,
        onClickOnBmiSection = navigateToBmiNavGraph,
        onClickOnBloodPressureSection = navigateToBloodPressureNavGraph,
        onClickOnBloodSugarSection = navigateToBloodSugarNavGraph,
        onClickOnHeartRateSection = navigateToHeartRateNavGraph,
        navigateToOnlineBookingNavGraph = navigateToOnlineBookingNavGraph,
        pagerState = pagerState,
        uiState = state.value,
        onCurrentDoctorsChanged = viewModel::onCurrentDoctorsPageChanged,
        navigateToOfflineBookingDestination = navigateToOfflineBookingDestination,
        doctorsOnlineTabData = TabData(
            title = stringResource(
                id = R.string.doctors_online
            ),
            onClick = {

                //make scroll here
                pagerState.animateScrollToPage(
                    coroutineScope = coroutineScope,
                    page = 0
                )

                //change current doctors page
                viewModel.onCurrentDoctorsPageChanged(
                    newPage = 0
                )

            }//end onClick
        ),
        doctorsOfflineTabData = TabData(
            title = stringResource(
                id = R.string.doctors_offline
            ),
            onClick = {

                //make scroll here
                pagerState.animateScrollToPage(
                    coroutineScope = coroutineScope,
                    page = 1
                )

                //change current doctors page
                viewModel.onCurrentDoctorsPageChanged(
                    newPage = 1
                )

            }//end onClick
        )
    )
}//end HomeScreen

//function for observe state and draw components
@Composable
private fun TopDoctorsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickSeeAll: () -> Unit,
    onClickHeartPrediction: () -> Unit,
    onClickOnBmiSection: () -> Unit,
    onClickOnBloodPressureSection: () -> Unit,
    onClickOnBloodSugarSection: () -> Unit,
    onClickOnHeartRateSection: () -> Unit,
    pagerState: PagerState,
    screenHeight: Int = LocalConfiguration.current.screenHeightDp,
    navigateToOnlineBookingNavGraph: (Int) -> Unit,
    doctorsOnlineTabData: TabData,
    doctorsOfflineTabData: TabData,
    uiState: TopDoctorsUiState,
    onCurrentDoctorsChanged: KFunction1<Int, Unit>,
    navigateToOfflineBookingDestination: (Int) -> Unit
) {

    //create lazy column here
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
            .padding(
                top = dimen.dimen_1.dp
            ),
        contentPadding = PaddingValues(
            top = dimen.dimen_1.dp
        )
    ) {

        //item contain on all buttons to transition to particular parts
        item(
            key = 0
        ) {

            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimen.dimen_2.dp
                    )
            ) {
                //create ids for components here
                val (
                    smartHealthTitleId, heartPredictionId, heartRateId,
                    bloodPressureId, bloodSugarId, bmiId, titleId, seeAllId
                ) = createRefs()

                //create guides here
                val guideFromStart50P = createGuidelineFromStart(.50f)

                //create smart health title here
                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        id = R.string.smart_health_metrics
                    ),
                    size = dimen.dimen_2_25,
                    color = theme.black,
                    modifier = Modifier
                        .constrainAs(smartHealthTitleId) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                )

                //create predict heart section here
                HeartPredictionSection(
                    dimen = dimen,
                    theme = theme,
                    image = painterResource(
                        id = R.drawable.heart_predict
                    ),
                    title = stringResource(
                        id = R.string.predicting_heart_disease_using_artificial_intelligence
                    ),
                    firstText = stringResource(
                        id = R.string.artificial
                    ),
                    secondText = stringResource(
                        id = R.string.intell
                    ),
                    thirdText = stringResource(
                        id = R.string.igence
                    ),
                    buttonContent = stringResource(
                        id = R.string.record_now
                    ),
                    onClick = onClickHeartPrediction,
                    modifier = Modifier
                        .constrainAs(heartPredictionId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                smartHealthTitleId.bottom,
                                dimen.dimen_2_5.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                //create heart rate item here
                HealthCareSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = R.string.heart_rate
                    ),
                    value = "65",
                    unit = "PBM",
                    image = painterResource(
                        id = R.drawable.heart_rate
                    ),
                    onClick = onClickOnHeartRateSection,
                    modifier = Modifier
                        .constrainAs(heartRateId) {
                            start.linkTo(parent.start)
                            end.linkTo(
                                guideFromStart50P,
                                dimen.dimen_0_75.dp
                            )
                            top.linkTo(
                                heartPredictionId.bottom,
                                dimen.dimen_1_5.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                //create blood pressure item here
                HealthCareSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = R.string.blood_pressure
                    ),
                    value = "120",
                    unit = "mmHG",
                    image = painterResource(
                        id = R.drawable.blood_pressure
                    ),
                    onClick = onClickOnBloodPressureSection,
                    modifier = Modifier
                        .constrainAs(bloodPressureId) {
                            start.linkTo(
                                guideFromStart50P,
                                dimen.dimen_0_75.dp
                            )
                            end.linkTo(parent.end)
                            top.linkTo(heartRateId.top)
                            width = Dimension.fillToConstraints
                        }
                )

                //create blood sugar item here
                HealthCareSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = R.string.blood_suger
                    ),
                    value = "120",
                    unit = "Mg/Ld",
                    image = painterResource(
                        id = R.drawable.blood_sugar
                    ),
                    onClick = onClickOnBloodSugarSection,
                    modifier = Modifier
                        .constrainAs(bloodSugarId) {
                            start.linkTo(parent.start)
                            end.linkTo(
                                guideFromStart50P,
                                dimen.dimen_0_75.dp
                            )
                            top.linkTo(
                                heartRateId.bottom,
                                dimen.dimen_1_5.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                //create bmi item here
                HealthCareSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = R.string.weight_and_tall
                    ),
                    value = "80",
                    unit = "Kg",
                    image = painterResource(
                        id = R.drawable.bmi
                    ),
                    onClick = onClickOnBmiSection,
                    modifier = Modifier
                        .constrainAs(bmiId) {
                            start.linkTo(
                                guideFromStart50P,
                                dimen.dimen_0_75.dp
                            )
                            end.linkTo(parent.end)
                            top.linkTo(bloodSugarId.top)
                            width = Dimension.fillToConstraints
                        }
                )

                //create top doctor title here
                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.top_doctors
                    ),
                    size = dimen.dimen_2_25,
                    color = theme.black,
                    modifier = Modifier
                        .constrainAs(titleId) {
                            start.linkTo(parent.start)
                            top.linkTo(
                                bloodSugarId.bottom,
                                dimen.dimen_2.dp
                            )
                        }
                )

                //create see all link here
                LinkView(
                    text = stringResource(
                        id = R.string.see_all
                    ),
                    color = theme.redDark,
                    size = dimen.dimen_1_5,
                    fontFamily = robotoBold,
                    onClick = onClickSeeAll,
                    modifier = Modifier
                        .constrainAs(seeAllId) {
                            end.linkTo(parent.end)
                            top.linkTo(titleId.top)
                            bottom.linkTo(titleId.bottom)
                        }
                )

            }//end ConstraintLayout

        }//end item

        //item for create tabs item
        stickyHeader(
            key = 6
        ) {

            //create tabs items here
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = theme.background
                    )
                    .padding(
                        start = dimen.dimen_2.dp,
                        end = dimen.dimen_2.dp
                    )
            ) {

                TabsSection(
                    theme = theme,
                    dimen = dimen,
                    currentItem = uiState.currentDoctorsPage,
                    tabs = arrayOf(doctorsOnlineTabData, doctorsOfflineTabData),
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end Box

        }//end item

        //create doctors pager item here
        item(
            key = 7
        ) {

            //create doctors pager here
            HorizontalPager(
                pageCount = 2,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight - dimen.dimen_23).dp)
            ) { page ->

                //if page is 0 create doctors online page else create doctor offline page
                when (page) {

                    //create doctors online page here
                    0 -> {

                        TopOnlineDoctorsScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToBookingNavGraph = navigateToOnlineBookingNavGraph
                        )

                    }//end top online case here

                    //create doctors offline page here
                    1 -> {

                        TopOfflineDoctorsScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToBookingNavGraph = navigateToOfflineBookingDestination
                        )

                    }//end top offline case here

                }//end when

            }//end HorizontalPager

        }//end item

    }//end LazyColumn

    //on pager state changed
    LaunchedEffect(
        key1 = pagerState.currentPage
    ) {

        //change current doctors page here
        onCurrentDoctorsChanged(pagerState.currentPage)

    }//end LaunchedEffect

}//end HomeContent





































//        //item contain on all buttons to transition to particular parts
//        item(
//            key = 0
//        ) {
//
//            //create container here
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        horizontal = dimen.dimen_2.dp
//                    )
//            ) {
//                //create ids for components here
//                val (smartHealthTitleId, heartPredictionId) = createRefs()
//
//                //create smart health title here
//                TextBoldView(
//                    theme = theme,
//                    dimen = dimen,
//                    text = stringResource(
//                        id = R.string.smart_health_metrics
//                    ),
//                    size = dimen.dimen_2_25,
//                    color = theme.black,
//                    modifier = Modifier
//                        .constrainAs(smartHealthTitleId) {
//                            start.linkTo(parent.start)
//                            top.linkTo(parent.top)
//                        }
//                )
//
//                //create predict heart section here
//                HeartPredictionSection(
//                    dimen = dimen,
//                    theme = theme,
//                    image = painterResource(
//                        id = R.drawable.heart_predict
//                    ),
//                    title = stringResource(
//                        id = R.string.predicting_heart_disease_using_artificial_intelligence
//                    ),
//                    firstText = stringResource(
//                        id = R.string.artificial
//                    ),
//                    secondText = stringResource(
//                        id = R.string.intell
//                    ),
//                    thirdText = stringResource(
//                        id = R.string.igence
//                    ),
//                    buttonContent = stringResource(
//                        id = R.string.record_now
//                    ),
//                    onClick = onClickHeartPrediction,
//                    modifier = Modifier
//                        .constrainAs(heartPredictionId) {
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                            top.linkTo(
//                                smartHealthTitleId.bottom,
//                                dimen.dimen_2_5.dp
//                            )
//                            width = Dimension.fillToConstraints
//                        }
//                )
//
//            }//end ConstraintLayout
//
//        }//end item
//
//        //item contain on heart rate section and blood pressure
//        item(
//            key = 1
//        ){
//
//            //create container here
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        horizontal = dimen.dimen_2.dp
//                    )
//            ){
//                //create ids for components here
//                val (heartRateId, bloodPressureId) = createRefs()
//
//                //create guides here
//                val guideFromStart50P = createGuidelineFromStart(.50f)
//
//                //create heart rate item here
//                HealthCareSection(
//                    dimen = dimen,
//                    theme = theme,
//                    title = stringResource(
//                        id = R.string.heart_rate
//                    ),
//                    value = "65",
//                    unit = "PBM",
//                    image = painterResource(
//                        id = R.drawable.heart_rate
//                    ),
//                    onClick = onClickOnHeartRateSection,
//                    modifier = Modifier
//                        .constrainAs(heartRateId) {
//                            start.linkTo(parent.start)
//                            end.linkTo(
//                                guideFromStart50P,
//                                dimen.dimen_0_75.dp
//                            )
//                            top.linkTo(
//                                parent.top,
//                                dimen.dimen_1_5.dp
//                            )
//                            width = Dimension.fillToConstraints
//                        }
//                )
//
//                //create blood pressure item here
//                HealthCareSection(
//                    dimen = dimen,
//                    theme = theme,
//                    title = stringResource(
//                        id = R.string.blood_pressure
//                    ),
//                    value = "120",
//                    unit = "mmHG",
//                    image = painterResource(
//                        id = R.drawable.blood_pressure
//                    ),
//                    onClick = onClickOnBloodPressureSection,
//                    modifier = Modifier
//                        .constrainAs(bloodPressureId) {
//                            start.linkTo(
//                                guideFromStart50P,
//                                dimen.dimen_0_75.dp
//                            )
//                            end.linkTo(parent.end)
//                            top.linkTo(heartRateId.top)
//                            width = Dimension.fillToConstraints
//                        }
//                )
//
//            }//end ConstraintLayout
//
//        }//end item
//
//        //item contain on blood sugar and bmi section and see all link
//        item(
//            key = 2
//        ) {
//
//            //create container here
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        horizontal = dimen.dimen_2.dp
//                    )
//            ){
//                //create ids for components here
//                val (bloodSugarId, bmiId, titleId, seeAllId) = createRefs()
//
//                //create guides here
//                val guideFromStart50P = createGuidelineFromStart(.50f)
//
//                //create blood sugar item here
//                HealthCareSection(
//                    dimen = dimen,
//                    theme = theme,
//                    title = stringResource(
//                        id = R.string.blood_suger
//                    ),
//                    value = "120",
//                    unit = "Mg/Ld",
//                    image = painterResource(
//                        id = R.drawable.blood_sugar
//                    ),
//                    onClick = onClickOnBloodSugarSection,
//                    modifier = Modifier
//                        .constrainAs(bloodSugarId) {
//                            start.linkTo(parent.start)
//                            end.linkTo(
//                                guideFromStart50P,
//                                dimen.dimen_0_75.dp
//                            )
//                            top.linkTo(
//                                parent.top,
//                                dimen.dimen_1_5.dp
//                            )
//                            width = Dimension.fillToConstraints
//                        }
//                )
//
//                //create bmi item here
//                HealthCareSection(
//                    dimen = dimen,
//                    theme = theme,
//                    title = stringResource(
//                        id = R.string.weight_and_tall
//                    ),
//                    value = "80",
//                    unit = "Kg",
//                    image = painterResource(
//                        id = R.drawable.bmi
//                    ),
//                    onClick = onClickOnBmiSection,
//                    modifier = Modifier
//                        .constrainAs(bmiId) {
//                            start.linkTo(
//                                guideFromStart50P,
//                                dimen.dimen_0_75.dp
//                            )
//                            end.linkTo(parent.end)
//                            top.linkTo(bloodSugarId.top)
//                            width = Dimension.fillToConstraints
//                        }
//                )
//
//                //create top doctor title here
//                TextBoldView(
//                    theme = theme,
//                    dimen = dimen,
//                    text = stringResource(
//                        R.string.top_doctors
//                    ),
//                    size = dimen.dimen_2_25,
//                    color = theme.black,
//                    modifier = Modifier
//                        .constrainAs(titleId) {
//                            start.linkTo(parent.start)
//                            top.linkTo(
//                                bloodSugarId.bottom,
//                                dimen.dimen_2.dp
//                            )
//                        }
//                )
//
//                //create see all link here
//                LinkView(
//                    text = stringResource(
//                        id = R.string.see_all
//                    ),
//                    color = theme.redDark,
//                    size = dimen.dimen_1_5,
//                    fontFamily = robotoBold,
//                    onClick = onClickSeeAll,
//                    modifier = Modifier
//                        .constrainAs(seeAllId) {
//                            end.linkTo(parent.end)
//                            top.linkTo(titleId.top)
//                            bottom.linkTo(titleId.bottom)
//                        }
//                )
//
//            }//end ConstraintLayout
//
//        }//end item
