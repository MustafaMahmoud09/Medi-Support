package com.example.booking.uiElement.screens.search.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.booking.uiElement.components.items.DoctorSearchSection
import com.example.booking.uiElement.components.items.HealthCareSection
import com.example.booking.uiElement.components.items.PredictHeartSection
import com.example.sharedui.uiElement.components.composable.LinkView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoBold
import com.example.sharedui.uiElement.style.theme.CustomTheme

//function for collect state and execute action from view model
@Composable
internal fun HomeScreen(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickSeeAll: () -> Unit,
    navigateToHeartPredictionNavGraph: () -> Unit
) {

    //call home content function
    HomeContent(
        dimen = dimen,
        theme = theme,
        onClickSeeAll = onClickSeeAll,
        onClickHeartPrediction = navigateToHeartPredictionNavGraph
    )
}//end HomeScreen

//function for observe state and draw components
@Composable
private fun HomeContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickSeeAll: () -> Unit,
    onClickHeartPrediction: () -> Unit
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
            start = dimen.dimen_2.dp,
            end = dimen.dimen_2.dp,
            bottom = dimen.dimen_2.dp,
            top = dimen.dimen_1.dp
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = dimen.dimen_1_5.dp
        )
    ) {

        //create smart health title item here
        item(
            key = 1
        ) {
            //create smart health title here
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimen.dimen_1.dp
                    )
            ) {

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.smart_health_metrics
                    ),
                    size = dimen.dimen_2_25,
                    color = theme.black
                )

            }//end Box

        }//end item

        //create heart predict item here
        item(
            key = 2
        ) {

            //create content item here
            PredictHeartSection(
                dimen = dimen,
                theme = theme,
                image = painterResource(
                    id = com.example.sharedui.R.drawable.heart_predict
                ),
                title = stringResource(
                    com.example.sharedui.R.string.predicting_heart_disease_using_artificial_intelligence
                ),
                firstText = stringResource(
                    com.example.sharedui.R.string.artificial
                ),
                secondText = stringResource(
                    com.example.sharedui.R.string.intell
                ),
                thirdText = stringResource(
                    com.example.sharedui.R.string.igence
                ),
                buttonContent = stringResource(
                    com.example.sharedui.R.string.record_now
                ),
                onClick = onClickHeartPrediction,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end item

        //create health care item here
        item(
            key = 3
        ) {
            //create content item here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //create ids to components here
                val (heartRateId, bloodPressureId) = createRefs()
                val guideFromStart50P = createGuidelineFromStart(.50f)

                //create heart rate item here
                HealthCareSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = com.example.sharedui.R.string.heart_rate
                    ),
                    value = "65",
                    unit = "PBM",
                    image = painterResource(
                        id = com.example.sharedui.R.drawable.heart_rate
                    ),
                    modifier = Modifier
                        .constrainAs(heartRateId) {
                            start.linkTo(parent.start)
                            end.linkTo(
                                guideFromStart50P,
                                dimen.dimen_0_75.dp
                            )
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }
                        .fillMaxWidth()
                )

                //create blood pressure item here
                HealthCareSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = com.example.sharedui.R.string.blood_pressure
                    ),
                    value = "120",
                    unit = "mmHG",
                    image = painterResource(
                        id = com.example.sharedui.R.drawable.blood_pressure
                    ),
                    modifier = Modifier
                        .constrainAs(bloodPressureId) {
                            start.linkTo(
                                guideFromStart50P,
                                dimen.dimen_0_75.dp
                            )
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }
                        .fillMaxWidth()
                )

            }//end ConstraintLayout

        }//end item

        //create health care item here
        item(
            key = 4
        ) {

            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //create ids to components here
                val (bloodSugarId, bmiId) = createRefs()
                val guideFromStart50P = createGuidelineFromStart(.50f)

                //create blood sugar item here
                HealthCareSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = com.example.sharedui.R.string.blood_suger
                    ),
                    value = "120",
                    unit = "Mg/Ld",
                    image = painterResource(
                        id = com.example.sharedui.R.drawable.blood_sugar
                    ),
                    modifier = Modifier
                        .constrainAs(bloodSugarId) {
                            start.linkTo(parent.start)
                            end.linkTo(
                                guideFromStart50P,
                                dimen.dimen_0_75.dp
                            )
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }
                        .fillMaxWidth()
                )

                //create bmi item here
                HealthCareSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = com.example.sharedui.R.string.weight_and_tall
                    ),
                    value = "80",
                    unit = "Kg",
                    image = painterResource(
                        id = com.example.sharedui.R.drawable.bmi
                    ),
                    modifier = Modifier
                        .constrainAs(bmiId) {
                            start.linkTo(
                                guideFromStart50P,
                                dimen.dimen_0_75.dp
                            )
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }
                        .fillMaxWidth()
                )

            }//end ConstraintLayout

        }//end item

        //create top doctor title item
        item(
            key = 5
        ) {

            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimen.dimen_0_5.dp
                    )
            ) {
                //create ids to components here
                val (titleId, seeAllId) = createRefs()

                //create top doctor title here
                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.top_doctors
                    ),
                    size = dimen.dimen_2_25,
                    color = theme.black,
                    modifier = Modifier
                        .constrainAs(titleId) {
                            start.linkTo(parent.start)
                            top.linkTo(
                                parent.top,
                                dimen.dimen_0_5.dp
                            )
                        }
                )

                //create see all link here
                LinkView(
                    text = stringResource(
                        com.example.sharedui.R.string.see_all
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

        //create doctor items
        items(
            count = 10
        ) {

            //create single doctor here
            DoctorSearchSection(
                dimen = dimen,
                theme = theme,
                name = "DR: Alaa Ahmed",
                location = "Cairo",
                time = "12.00 AM -3:00 PM",
                image = painterResource(
                    id = com.example.sharedui.R.drawable.doctor_test
                ),
                textButton = stringResource(
                    com.example.sharedui.R.string.book_now
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end items

    }//end LazyColumn

}//end HomeContent