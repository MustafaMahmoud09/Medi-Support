package com.example.bloodpressure.presentation.uiElement.screens.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodpressure.presentation.uiElement.components.items.BloodPressureLineChartSection
import com.example.bloodpressure.presentation.uiState.state.BloodPressureActivityUiState
import com.example.bloodpressure.presentation.uiState.viewModel.BloodPressureActivityViewModel
import com.example.sharedui.uiElement.components.items.SomeHistorySection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.components.items.RecommendedSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme


@Composable
fun BloodPressureActivityScreen(
    viewModel: BloodPressureActivityViewModel = hiltViewModel(),
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToHistoryDestination: () -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    BloodPressureActivityContent(
        theme = theme,
        dimen = dimen,
        onClickSeeAll = navigateToHistoryDestination,
        uiState = state.value,
        daysColumnState = rememberLazyListState()
    )
}//end BloodPressureScreen

@Composable
private fun BloodPressureActivityContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickSeeAll: () -> Unit,
    uiState: BloodPressureActivityUiState,
    daysColumnState: LazyListState
) {

    //create screen container here
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            ),
        contentPadding = PaddingValues(
            vertical = dimen.dimen_2.dp
        )
    ) {

        //create container item contain on all screen components
        item(
            key = 1
        ) {

            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //create ids for screen components here
                val (daysId, chartId, someHistoryId, recommendedId) = createRefs()

                //create row contain on days here
                LazyRow(
                    state = daysColumnState,
                    contentPadding = PaddingValues(
                        horizontal = dimen.dimen_1_5.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = dimen.dimen_1_5.dp,
                    ),
                    modifier = Modifier
                        .constrainAs(daysId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }
                ) {

                    //create days here
                    items(
                        count = uiState.monthDays.size
                    ) { count ->

                        //create single day here
                        DaySection(
                            dimen = dimen,
                            theme = theme,
                            dayName = uiState.monthDays[count].name,
                            dayNumber = "${uiState.monthDays[count].number}",
                            toDay = uiState.monthDays[count].today
                        )

                    }//end items

                }//end LazyRow

                //create blood pressure chart here
                BloodPressureLineChartSection(
                    theme = theme,
                    dimen = dimen,
                    xAxisData = if (uiState.systolicResult.xAxisData.isNotEmpty()) {
                        uiState.systolicResult.xAxisData
                    } else if (uiState.diastolicResult.xAxisData.isNotEmpty()) {
                        uiState.diastolicResult.xAxisData
                    } else {
                        listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                    },
                    firstData = uiState.systolicResult.dataResult,
                    maxValueForFirstData = uiState.systolicResult.maxValue.toFloat(),
                    secondData = uiState.diastolicResult.dataResult,
                    maxValueForSecondData = uiState.diastolicResult.maxValue.toFloat(),
                    titleForFirstChart = stringResource(
                        R.string.upper_bound
                    ),
                    titleForSecondChart = stringResource(
                        R.string.lower_bound
                    ),
                    unit = stringResource(
                        R.string.mmhg
                    ),
                    modifier = Modifier
                        .constrainAs(chartId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_1_5.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_1_5.dp
                            )
                            top.linkTo(
                                daysId.bottom,
                                dimen.dimen_1_5.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                //create some history section here
                SomeHistorySection(
                    dimen = dimen,
                    theme = theme,
                    onClickSeeAll = onClickSeeAll,
                    modifier = Modifier
                        .constrainAs(someHistoryId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                chartId.bottom,
                                dimen.dimen_1.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                //create recommended section here
                RecommendedSection(
                    dimen = dimen,
                    theme = theme,
                    equationText = stringResource(R.string.how_to_control_blood_pressure),
                    responseText = if (uiState.adviceBloodPressureModel != null) {
                        uiState.adviceBloodPressureModel.advice
                    } else {
                        ""
                    },
                    modifier = Modifier
                        .constrainAs(recommendedId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                someHistoryId.bottom,
                                dimen.dimen_1_75.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

            }//end ConstraintLayout

        }//end item

    }//end LazyColumn

    LaunchedEffect(key1 = true) {

        daysColumnState.scrollToItem(uiState.currentDayNumber - 1)

    }//end LaunchedEffect

}//end HeartRateScreen