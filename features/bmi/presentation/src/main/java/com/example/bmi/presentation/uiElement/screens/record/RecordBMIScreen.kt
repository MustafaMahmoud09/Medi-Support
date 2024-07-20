package com.example.bmi.presentation.uiElement.screens.record

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmi.presentation.uiElement.components.items.BMISliderSection
import com.example.bmi.presentation.uiElement.components.items.GenderSection
import com.example.bmi.presentation.uiState.state.RecordBMIUiState
import com.example.bmi.presentation.uiState.viewModel.RecordBMIViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction1

@Composable
internal fun RecordBMIScreen(
    viewModel: RecordBMIViewModel = hiltViewModel(),
    popRecordBMIDestination: () -> Unit,
    navigateToDeterminationBMIDestination: () -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    RecordBMIContent(
        onClickOnBackButton = popRecordBMIDestination,
        onClickOnButtonCalculate = viewModel::onBMIRecordAdded,
        onWeightChanged = viewModel::onWeightChanged,
        onAgeChanged = viewModel::onAgeChanged,
        onHeightChanged = viewModel::onHeightChanged,
        onGenderChanged = viewModel::onGenderChanged,
        uiSate = state.value,
        snackbarHostState = snackbarHostState,
    )

    LaunchedEffect(
        key1 = state.value.addBMIRecordStatus.success
    ) {

        if (state.value.addBMIRecordStatus.success) {
            navigateToDeterminationBMIDestination()
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.addBMIRecordStatus.internetError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = internetError
            )
        }//end if

    }//end LaunchedEffect


    LaunchedEffect(
        key1 = state.value.addBMIRecordStatus.unAuthorized
    ) {

        if (
            !state.value.startRunning &&
            state.value.addBMIRecordStatus.unAuthorized
        ) {
            navigateToLoginNavGraphWithPopBottomDestination()
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.addBMIRecordStatus.serverError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = serverError
            )
        }//end if

    }//end LaunchedEffect

}//end RecordBMIScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun RecordBMIContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickOnBackButton: () -> Unit,
    onClickOnButtonCalculate: () -> Unit,
    uiSate: RecordBMIUiState,
    onWeightChanged: (Float) -> Unit,
    onAgeChanged: (Float) -> Unit,
    onHeightChanged: (Float) -> Unit,
    onGenderChanged: KFunction1<Int, Unit>,
    snackbarHostState: SnackbarHostState
) {

    //create base screen here to set status bar color and navigation bar color
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
                    .padding(
                        top = dimen.dimen_2_5.dp
                    )
            ) {
                //create ids for screen components here
                val (headerId, containerId) = createRefs()

                //create header screen here
                HeaderSection(
                    dimen = dimen,
                    theme = theme,
                    onClickOnBackButton = onClickOnBackButton,
                    title = stringResource(
                        id = R.string.bmi
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
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints
                        }//end constrainAs
                )

                //create container here
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(containerId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                headerId.bottom,
                                dimen.dimen_2.dp
                            )
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(
                        bottom = dimen.dimen_2.dp,
                        top = dimen.dimen_0_5.dp
                    )
                ) {

                    //create item contain on all components here
                    item(
                        key = 1
                    ) {
                        //create sub container here
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            //create ids for screen components here
                            val (genderSectionId, ageSectionId, heightSectionId,
                                weightSectionId, calculateButtonId) = createRefs()

                            //create gender section here
                            GenderSection(
                                theme = theme,
                                dimen = dimen,
                                title = stringResource(
                                    id = R.string.gender
                                ),
                                firstType = stringResource(
                                    id = R.string.female
                                ),
                                secondType = stringResource(
                                    id = R.string.male
                                ),
                                typeSelected = uiSate.gender,
                                onClickOnType = onGenderChanged,
                                modifier = Modifier
                                    .constrainAs(genderSectionId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_2.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(parent.top)
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            //create age section here
                            BMISliderSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(
                                    id = R.string.age
                                ),
                                value = uiSate.age.toInt().toFloat(),
                                onValueChange = onAgeChanged,
                                unit = stringResource(
                                    id = R.string.years
                                ),
                                startPoint = 0,
                                endPoint = 150,
                                modifier = Modifier
                                    .constrainAs(ageSectionId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_1.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_1.dp
                                        )
                                        top.linkTo(
                                            genderSectionId.bottom,
                                            dimen.dimen_3.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            //create height section here
                            BMISliderSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(
                                    id = R.string.height
                                ),
                                value = uiSate.height,
                                onValueChange = onHeightChanged,
                                unit = stringResource(
                                    id = R.string.cm
                                ),
                                startPoint = 10,
                                endPoint = 300,
                                modifier = Modifier
                                    .constrainAs(heightSectionId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_1.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_1.dp
                                        )
                                        top.linkTo(
                                            ageSectionId.bottom,
                                            dimen.dimen_2_5.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            //create weight section here
                            BMISliderSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(
                                    id = R.string.weight
                                ),
                                value = uiSate.weight,
                                onValueChange = onWeightChanged,
                                unit = stringResource(
                                    id = R.string.kg
                                ),
                                startPoint = 10,
                                endPoint = 300,
                                modifier = Modifier
                                    .constrainAs(weightSectionId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_1.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_1.dp
                                        )
                                        top.linkTo(
                                            heightSectionId.bottom,
                                            dimen.dimen_2_5.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            //create calculate button here
                            BasicButtonView(
                                dimen = dimen,
                                theme = theme,
                                text = stringResource(
                                    id = R.string.calculate
                                ),
                                onClick = if (!uiSate.addBMIRecordStatus.loading) {
                                    onClickOnButtonCalculate
                                } else {
                                    {}
                                },
                                load = uiSate.addBMIRecordStatus.loading,
                                modifier = Modifier
                                    .constrainAs(calculateButtonId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_2.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(
                                            weightSectionId.bottom,
                                            dimen.dimen_3_5.dp
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

}//end RecordBMIContent