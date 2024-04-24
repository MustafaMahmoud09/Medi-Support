package com.example.bloodpressure.presentation.uiElement.screens.record

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodpressure.presentation.uiElement.components.items.PressureFieldSection
import com.example.bloodpressure.presentation.uiElement.components.items.TypeStateSection
import com.example.bloodpressure.presentation.uiState.state.RecordBloodPressureUiState
import com.example.bloodpressure.presentation.uiState.viewModel.RecordBloodPressureViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.google.accompanist.placeholder.placeholder


@Composable
internal fun RecordBloodPressureScreen(
    viewModel: RecordBloodPressureViewModel = hiltViewModel(),
    popRecordBloodPressureDestination: () -> Unit,
    navigateToStatisticsBloodPressureDestination: () -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    RecordBloodPressureContent(
        uiState = state.value,
        onDiastolicBloodPressureChanged = viewModel::onDiastolicBloodPressureChanged,
        onSystolicBloodPressureChanged = viewModel::onSystolicBloodPressureChanged,
        onClickOnBackButton = popRecordBloodPressureDestination,
        onClickOnAddRecordButton = viewModel::onBloodPressureRecordAdded,
        snackbarHostState = snackbarHostState,
        daysColumnState = rememberLazyListState()
    )

    LaunchedEffect(
        key1 = state.value.addBloodPressureRecordStatus.success
    ) {

        if (state.value.addBloodPressureRecordStatus.success) {
            navigateToStatisticsBloodPressureDestination()
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.addBloodPressureRecordStatus.internetError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = internetError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.addBloodPressureRecordStatus.serverError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = serverError
            )
        }//end if

    }//end LaunchedEffect

}//end RecordBloodPressureScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun RecordBloodPressureContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnAddRecordButton: () -> Unit,
    uiState: RecordBloodPressureUiState,
    onDiastolicBloodPressureChanged: (Boolean) -> Unit,
    onSystolicBloodPressureChanged: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState,
    daysColumnState: LazyListState
) {

    //create base screen for define status bar color and navigation bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }//end snack bar Host
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
                val (headerId, recyclerContainerId) = createRefs()

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
                            bottom.linkTo(parent.bottom)
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
                                inputDataTextId, systolicFieldId, diastolicFieldId, addRecordButtonId
                            ) = createRefs()
                            //create guides here
                            val guideFromEnd35P = createGuidelineFromEnd(0.35f)
                            val guideFromEnd19P = createGuidelineFromEnd(0.19F)

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

                            //create input data text here
                            TextBoldView(
                                theme = theme,
                                dimen = dimen,
                                text = stringResource(
                                    id = R.string.input_data
                                ),
                                size = dimen.dimen_2_5,
                                color = theme.black,
                                modifier = Modifier
                                    .constrainAs(inputDataTextId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_4_25.dp
                                        )
                                        top.linkTo(
                                            lineId.bottom,
                                            dimen.dimen_2.dp
                                        )
                                    }
                            )

                            //create input systolic field here
                            PressureFieldSection(
                                dimen = dimen,
                                theme = theme,
                                hint = stringResource(
                                    id = R.string.systolic_blood_pressure
                                ),
                                input = uiState.systolicBloodPressure,
                                onClickOnOperation = onSystolicBloodPressureChanged,
                                modifier = Modifier
                                    .constrainAs(systolicFieldId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_3_75.dp
                                        )
                                        end.linkTo(guideFromEnd19P)
                                        top.linkTo(
                                            inputDataTextId.bottom,
                                            dimen.dimen_2_25.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            //create input diastolic field here
                            PressureFieldSection(
                                dimen = dimen,
                                theme = theme,
                                hint = stringResource(
                                    id = R.string.diastolic_blood_pressure
                                ),
                                input = uiState.diastolicBloodPressure,
                                onClickOnOperation = onDiastolicBloodPressureChanged,
                                modifier = Modifier
                                    .constrainAs(diastolicFieldId) {
                                        start.linkTo(systolicFieldId.start)
                                        end.linkTo(systolicFieldId.end)
                                        top.linkTo(
                                            systolicFieldId.bottom,
                                            dimen.dimen_2_25.dp
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
                                onClick = if (!uiState.addBloodPressureRecordStatus.loading) {
                                    onClickOnAddRecordButton
                                } else {
                                    {}
                                },
                                load = uiState.addBloodPressureRecordStatus.loading,
                                modifier = Modifier
                                    .constrainAs(addRecordButtonId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_2.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(
                                            diastolicFieldId.bottom,
                                            dimen.dimen_2_25.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                        }//end ConstraintLayout

                    }//end item

                }//end LazyColumn

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen


    LaunchedEffect(key1 = true) {

        daysColumnState.scrollToItem(uiState.currentDayNumber-1)

    }//end LaunchedEffect

}//end RecordBloodPressureContent