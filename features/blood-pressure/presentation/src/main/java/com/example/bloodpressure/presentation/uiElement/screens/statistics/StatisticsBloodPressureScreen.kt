package com.example.bloodpressure.presentation.uiElement.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodpressure.presentation.uiElement.components.items.BloodPressureLineChartSection
import com.example.bloodpressure.presentation.uiElement.components.items.TypeStateSection
import com.example.bloodpressure.presentation.uiState.state.StatisticsBloodPressureUiState
import com.example.bloodpressure.presentation.uiState.viewModel.StatisticsBloodPressureViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.components.items.RecommendedSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.google.accompanist.placeholder.placeholder
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.component.shape.dashedShape
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions

@Composable
internal fun StatisticsBloodPressureScreen(
    viewModel: StatisticsBloodPressureViewModel = hiltViewModel(),
    popStatisticsBloodPressureDestination: () -> Unit,
    navigateToRecordBloodPressureDestination: () -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {

    //get screen state here
    val state = viewModel.state.collectAsState()

    StatisticsBloodPressureContent(
        onClickOnBackButton = popStatisticsBloodPressureDestination,
        onClickOnAddRecordButton = navigateToRecordBloodPressureDestination,
        uiState = state.value,
        daysColumnState = rememberLazyListState()
    )


    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.diastolicResult.unAuthorized,
        key2 = state.value.systolicResult.unAuthorized
    ) {

        if (
            state.value.diastolicResult.unAuthorized ||
            state.value.systolicResult.unAuthorized
        ) {

            //show email snack bar here
            navigateToLoginNavGraphWithPopBottomDestination()

        }//end if

    }//end LaunchedEffect

}//end StatisticsBloodPressureScreen

@Composable
private fun StatisticsBloodPressureContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnAddRecordButton: () -> Unit,
    uiState: StatisticsBloodPressureUiState,
    daysColumnState: LazyListState,
) {

    //create base screen for define status bar color and navigation bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        //create container here
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .background(
                    color = theme.background
                )
        ) {
            //create ids for screen components here
            val (headerId, recyclerContainerId, addRecordButton) = createRefs()

            //create header section here
            HeaderSection(
                dimen = dimen,
                theme = theme,
                onClickOnBackButton = onClickOnBackButton,
                title = stringResource(
                    id = R.string.blood_pressure
                ),
                modifier = Modifier
                    .constrainAs(headerId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            parent.top,
                            dimen.dimen_2_5.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            //create add record button here
            BasicButtonView(
                dimen = dimen,
                theme = theme,
                text = stringResource(
                    id = R.string.add_record
                ),
                onClick = onClickOnAddRecordButton,
                modifier = Modifier
                    .constrainAs(addRecordButton) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(
                            parent.bottom,
                            dimen.dimen_1_5.dp
                        )
                        width = Dimension.fillToConstraints
                    }//end constrainAs
            )

            //create column contain on all components here
            LazyColumn(
                modifier = Modifier
                    .constrainAs(recyclerContainerId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            headerId.bottom,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(addRecordButton.top)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    top = dimen.dimen_0_25.dp,
                    bottom = dimen.dimen_2.dp
                )
            ) {

                item(
                    key = 1
                ) {

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //create ids for screen components here
                        val (dateId, averageId, typeStateId, daysId, lineId,
                            bloodPressureChartId, recommendedId) = createRefs()
                        //create guides here
                        val guideFromEnd35P = createGuidelineFromEnd(0.35f)

                        //create date section here
                        IconTextSection(
                            theme = theme,
                            dimen = dimen,
                            text = if (uiState.adviceBloodPressureModel != null) {
                                uiState.adviceBloodPressureModel.createdAt
                            } else "15-03-2002",
                            fontFamily = robotoMedium,
                            fontSize = dimen.dimen_2,
                            fontColor = theme.hintIconBottom,
                            icon = painterResource(
                                id = R.drawable.reminder_icon_health_care
                            ),
                            iconSize = dimen.dimen_3,
                            iconTint = theme.black,
                            spaceBetweenComponents = dimen.dimen_1,
                            modifier = Modifier
                                .constrainAs(dateId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(parent.top)
                                }
                                .placeholder(
                                    visible = uiState.adviceBloodPressureModel == null,
                                    color = theme.background
                                )
                        )

                        //create average section here
                        MultiColorTextView(
                            dimen = dimen,
                            theme = theme,
                            parentText = "${stringResource(R.string.average)} ${
                                if (uiState.adviceBloodPressureModel != null) {
                                    uiState.adviceBloodPressureModel.systolic
                                } else ""
                            }/${
                                if (uiState.adviceBloodPressureModel != null) {
                                    uiState.adviceBloodPressureModel.diastolic
                                } else ""
                            } mmHG",
                            subTexts = arrayOf(
                                stringResource(
                                    R.string.average
                                )
                            ),
                            subTextsColors = arrayOf(theme.redDark),
                            parentTextColor = theme.black,
                            fontFamily = robotoMedium,
                            textSize = dimen.dimen_2_25,
                            textAlign = null,
                            modifier = Modifier
                                .constrainAs(averageId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        guideFromEnd35P,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(
                                        dateId.bottom,
                                        dimen.dimen_2_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                                .placeholder(
                                    visible = uiState.adviceBloodPressureModel == null,
                                    color = theme.background
                                )
                        )

                        //create type state user section here
                        TypeStateSection(
                            theme = theme,
                            dimen = dimen,
                            type = if (uiState.adviceBloodPressureModel != null) {
                                uiState.adviceBloodPressureModel.type
                            } else "",
                            placeHolderState = uiState.adviceBloodPressureModel == null,
                            modifier = Modifier
                                .constrainAs(typeStateId) {
                                    top.linkTo(
                                        dateId.bottom,
                                        dimen.dimen_2_5.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2_5.dp
                                    )
                                    start.linkTo(guideFromEnd35P)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create row contain on days here
                        LazyRow(
                            state = daysColumnState,
                            contentPadding = PaddingValues(
                                horizontal = dimen.dimen_1_75.dp
                            ),
                            horizontalArrangement = Arrangement.spacedBy(
                                space = dimen.dimen_1_75.dp,
                            ),
                            modifier = Modifier
                                .constrainAs(daysId) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(
                                        typeStateId.bottom,
                                        dimen.dimen_2_5.dp
                                    )
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

                        //create line separate here
                        LineView(
                            dimen = dimen,
                            theme = theme,
                            height = .77f,
                            color = theme.gray939393,
                            modifier = Modifier
                                .constrainAs(lineId) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(
                                        daysId.bottom,
                                        dimen.dimen_1_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create blood pressure chart here
                        BloodPressureLineChartSection(
                            theme = theme,
                            dimen = dimen,
                            xAxisData = if (uiState.systolicResult.xAxisData.size > uiState.diastolicResult.xAxisData.size) {
                                uiState.systolicResult.xAxisData
                            } else {
                                uiState.diastolicResult.xAxisData
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
                            bottomAxisVisibility = false,
                            endAxisVisibility = false,
                            guideLine = axisGuidelineComponent(
                                color = theme.blackLight353535,
                                strokeColor = theme.blackLight353535,
                                shape = Shapes.dashedShape(
                                    shape = Shapes.roundedCornerShape(90),
                                    dashLength = dimen.dimen_0_25.dp,
                                    gapLength = dimen.dimen_1_25.dp,
                                    fitStrategy = DashedShape.FitStrategy.Fixed
                                ),
                                margins = emptyDimensions(),
                            ),
                            maxYLinesForFirstChart = 4,
                            maxYLinesForSecondChart = 3,
                            modifier = Modifier
                                .constrainAs(bloodPressureChartId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_1_5.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_1_5.dp
                                    )
                                    top.linkTo(
                                        lineId.bottom,
                                        dimen.dimen_1_75.dp
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
                                        bloodPressureChartId.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
//                                    .placeholder(
//                                    visible = uiState.adviceBloodPressureModel == null,
//                                    color = theme.background
//                                )
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

    LaunchedEffect(key1 = true) {

        daysColumnState.scrollToItem(uiState.currentDayNumber - 1)

    }//end LaunchedEffect

}//end StatisticsBloodPressureContent