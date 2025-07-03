@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package com.example.heartprediction.presentation.uiElement.screens.record

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartprediction.presentation.uiElement.components.items.InputFieldSection
import com.example.heartprediction.presentation.uiElement.components.items.MenuFieldSection
import com.example.heartprediction.presentation.uiElement.data.MenuData
import com.example.heartprediction.presentation.uiState.state.HeartPredictionUiState
import com.example.heartprediction.presentation.uiState.viewModel.HeartPredictionViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

//function for collect state and execute actions in view model
@Composable
internal fun RecordHeartPredictionScreen(
    viewModel: HeartPredictionViewModel = hiltViewModel(),
    popRecordHeartPredictionDestination: () -> Unit,
    navigateToPredictionHeartPredictionDestination: KFunction1<Int, Unit>,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {
    val state = viewModel.state.collectAsState()
    //create focus request array have focus request to fields here for control on field any time by code
    val focusRequesters = Array(4) { FocusRequester() }

    //variable have number field is focus now

    //create lazy column state here to scroll to any field in any time by code
    val containerFieldsState = rememberLazyListState()

    val snackbarHostState = remember { SnackbarHostState() }

    val incompleteError =
        stringResource(R.string.data_is_incomplete)

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    //create components for record heart screen here
    RecordHeartPredictionContent(
        uiState = state.value,
        snackbarHostState = snackbarHostState,
        containerFieldsState = containerFieldsState,
        focusRequesters = focusRequesters,
        keyboardIsVisible = WindowInsets.isImeVisible,
        onBmiChanged = viewModel::onBmiChanged,
        onPhysicalHealthChanged = viewModel::onPhysicalHealthChanged,
        onMentalHealthChanged = viewModel::onMentalChanged,
        onSleepTimeChanged = viewModel::onSleepTimeChanged,
        onMenuExpectedChanged = viewModel::onMenuExpectedChanged,
        onChangeSexSelected = viewModel::onChangeSexSelected,
        onChangeRaceSelected = viewModel::onChangeRaceSelected,
        onChangeDiabeticSelected = viewModel::onChangeDiabeticSelected,
        onChangeAgeCategorySelected = viewModel::onChangeAgeCategorySelected,
        onChangeGenHealthSelected = viewModel::onChangeGenHealthSelected,
        onChangeSmokingSelected = viewModel::onChangeSmokingSelected,
        onChangeAlcoholDrinkingSelected = viewModel::onChangeAlcoholDrinkingSelected,
        onChangeStrokeSelected = viewModel::onChangeStrokeSelected,
        onChangeDiffWalkingSelected = viewModel::onChangeDiffWalkingSelected,
        onChangePhysicalActivitySelected = viewModel::onChangePhysicalActivitySelected,
        onChangeAsthmaSelected = viewModel::onChangeAsthmaSelected,
        onChangeKidneyDiseaseSelected = viewModel::onChangeKidneyDiseaseSelected,
        onChangeSkinCancerSelected = viewModel::onChangeSkinCancerSelected,
        onClickBack = {
            //if first field is focus now pop screen from back stack
            if (state.value.numberOfFieldIsFocus == 0) {
                popRecordHeartPredictionDestination()
            }//end if

            //if other field is focus now transport to prev field
            else {
                viewModel.onNumberOfFieldFocusChanged(
                    newValue = state.value.numberOfFieldIsFocus - 1
                )
            }//end else
        },
        onClickOnOperationButton = {
            //if field is focus before last field transport to next field
            if (state.value.numberOfFieldIsFocus < 16) {
                viewModel.onNumberOfFieldFocusChanged(
                    newValue = state.value.numberOfFieldIsFocus + 1
                )
            }//end if

            //if field is focus equal last field transport to next screen
            else {
                viewModel.onHeartDiseasePredicted()
            }//end else
        }//end onClickOnOperationButton
    )

    LaunchedEffect(
        key1 = state.value.predictHeartDiseaseStatus.success
    ) {

        if (!state.value.startRunning) {

            if (state.value.predictHeartDiseaseStatus.success) {
                navigateToPredictionHeartPredictionDestination(state.value.resultClass)
            }//end if

        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.predictHeartDiseaseStatus.internetError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = internetError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.predictHeartDiseaseStatus.serverError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = serverError
            )
        }//end if

    }//end LaunchedEffect


    LaunchedEffect(
        key1 = state.value.predictHeartDiseaseStatus.dataNotComplete
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = incompleteError
            )
        }//end if

    }//end LaunchedEffect


    LaunchedEffect(
        key1 = state.value.predictHeartDiseaseStatus.unAuthorized
    ) {

        if (
            !state.value.startRunning &&
            state.value.predictHeartDiseaseStatus.unAuthorized
        ) {
            navigateToLoginNavGraphWithPopBottomDestination()
        }//end if

    }//end LaunchedEffect


}//end RecordHeartPredictionScreen


//function for observe state and draw screen components
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun RecordHeartPredictionContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    focusRequesters: Array<FocusRequester>,
    onClickOnOperationButton: () -> Unit,
    containerFieldsState: LazyListState,
    keyboardIsVisible: Boolean,
    heightScreen: Int = LocalConfiguration.current.screenHeightDp,
    uiState: HeartPredictionUiState,
    onBmiChanged: KFunction1<String, Unit>,
    onPhysicalHealthChanged: KFunction1<String, Unit>,
    onMentalHealthChanged: KFunction1<String, Unit>,
    onSleepTimeChanged: KFunction1<String, Unit>,
    onMenuExpectedChanged: KFunction0<Unit>,
    onChangeSexSelected: KFunction2<Int, String, Unit>,
    onChangeRaceSelected: KFunction2<Int, String, Unit>,
    onChangeDiabeticSelected: KFunction2<Int, String, Unit>,
    onChangeAgeCategorySelected: KFunction2<Int, String, Unit>,
    onChangeGenHealthSelected: KFunction2<Int, String, Unit>,
    onChangeSmokingSelected: KFunction2<Int, String, Unit>,
    onChangeAlcoholDrinkingSelected: KFunction2<Int, String, Unit>,
    onChangeStrokeSelected: KFunction2<Int, String, Unit>,
    onChangeDiffWalkingSelected: KFunction2<Int, String, Unit>,
    onChangePhysicalActivitySelected: KFunction2<Int, String, Unit>,
    onChangeAsthmaSelected: KFunction2<Int, String, Unit>,
    onChangeKidneyDiseaseSelected: KFunction2<Int, String, Unit>,
    onChangeSkinCancerSelected: KFunction2<Int, String, Unit>,
    snackbarHostState: SnackbarHostState
) {

    //define navigation color and status color for system ui
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
                //create ids for components here
                val (headerId, operationButtonId, fieldsId) = createRefs()

                //create header here
                HeaderSection(
                    dimen = dimen,
                    theme = theme,
                    onClickOnBackButton = onClickBack,
                    title = stringResource(
                        id = R.string.heart_disease_prediction
                    ),
                    modifier = Modifier
                        .constrainAs(headerId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(parent.end)
                            top.linkTo(
                                parent.top,
                                dimen.dimen_2_5.dp
                            )

                            width = Dimension.fillToConstraints
                        }
                )

                //if keyboard is not visible
                if (!keyboardIsVisible) {
                    //create operation button here
                    BasicButtonView(
                        dimen = dimen,
                        theme = theme,
                        text =
                        //if field last is focus now set result text to button
                        if (uiState.numberOfFieldIsFocus == 16)
                            stringResource(
                                id = R.string.result
                            )
                        //else set next text to button
                        else
                            stringResource(
                                id = R.string.next
                            ),
                        onClick = if (!uiState.predictHeartDiseaseStatus.loading) {
                            onClickOnOperationButton
                        } else {
                            {}
                        },
                        load = uiState.predictHeartDiseaseStatus.loading,
                        modifier = Modifier
                            .constrainAs(operationButtonId) {
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_2.dp
                                )
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_2.dp
                                )

                                //if keyboard is not visible make button constraint from bottom
                                bottom.linkTo(
                                    parent.bottom,
                                    dimen.dimen_1_5.dp
                                )

                                width = Dimension.fillToConstraints
                            },
                    )

                }//end if


                //create fields container here
                LazyColumn(
                    state = containerFieldsState,
                    modifier = Modifier
                        .constrainAs(fieldsId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                headerId.bottom,
                                dimen.dimen_2.dp
                            )
                            //if keyboard is visible set this item above parent
                            if (keyboardIsVisible) {
                                bottom.linkTo(parent.bottom)
                            }
                            //if keyboard is not visible set this item above button
                            else {
                                bottom.linkTo(operationButtonId.top)
                            }
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(
                        start = dimen.dimen_2.dp,
                        end = dimen.dimen_2.dp,
                        top = dimen.dimen_0_5.dp,
                        bottom = dimen.dimen_1.dp
                    )
                ) {

                    item(
                        key = 1
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(
                                space = dimen.dimen_2.dp
                            )
                        ) {

                            InputFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(
                                    id = R.string.bmi
                                ).uppercase(),
                                hint = stringResource(R.string.your_bmi),
                                value = uiState.bmi,
                                onChange = onBmiChanged,
                                focusRequester = focusRequesters[0],
                                keyboardType = KeyboardType.Number,
                                numberField = 0,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            InputFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.physical_health),
                                hint = stringResource(R.string.your_physical_health),
                                value = uiState.physicalHealth,
                                onChange = onPhysicalHealthChanged,
                                focusRequester = focusRequesters[1],
                                keyboardType = KeyboardType.Number,
                                numberField = 1,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            InputFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.mental_health),
                                hint = stringResource(R.string.your_mental_health),
                                value = uiState.mentalHealth,
                                onChange = onMentalHealthChanged,
                                focusRequester = focusRequesters[2],
                                keyboardType = KeyboardType.Number,
                                numberField = 2,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            InputFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.sleep_time),
                                hint = stringResource(R.string.your_sleep_time),
                                value = uiState.sleepTime,
                                onChange = onSleepTimeChanged,
                                focusRequester = focusRequesters[3],
                                keyboardType = KeyboardType.Number,
                                numberField = 3,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.sex),
                                value = stringResource(R.string.your_sex),
                                numberField = 4,
                                menusExpanded = uiState.menuExpanded,
                                dataSelected = uiState.sexData,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                onClickOnMenuItem = onChangeSexSelected,
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(id = R.string.female)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(id = R.string.male)
                                    )
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.race),
                                value = stringResource(R.string.your_race),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.american_indian_alaskan_native)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.asian),
                                    ),
                                    MenuData(
                                        id = 2,
                                        name = stringResource(R.string.black)
                                    ),
                                    MenuData(
                                        id = 3,
                                        name = stringResource(R.string.hispanic)
                                    ),
                                    MenuData(
                                        id = 5,
                                        name = stringResource(R.string.white)
                                    ),
                                    MenuData(
                                        id = 4,
                                        name = stringResource(R.string.other)
                                    )
                                ),
                                onClickOnMenuItem = onChangeRaceSelected,
                                numberField = 5,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.raceData,
                            )


                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.diabetic),
                                value = stringResource(R.string.your_diabetic),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no_borderline_diabetes),
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes_during_pregnancy)
                                    )
                                ),
                                onClickOnMenuItem = onChangeDiabeticSelected,
                                numberField = 6,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.diabeticData,
                            )


                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.age_category),
                                value = stringResource(R.string.your_age_category),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string._18_24)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string._25_29),
                                    ),
                                    MenuData(
                                        id = 2,
                                        name = stringResource(R.string._30_34)
                                    ),
                                    MenuData(
                                        id = 3,
                                        name = stringResource(R.string._35_39)
                                    ),
                                    MenuData(
                                        id = 4,
                                        name = stringResource(R.string._40_44),
                                    ),
                                    MenuData(
                                        id = 5,
                                        name = stringResource(R.string._45_49)
                                    ),
                                    MenuData(
                                        id = 6,
                                        name = stringResource(R.string._50_54)
                                    ),
                                    MenuData(
                                        id = 7,
                                        name = stringResource(R.string._55_59)
                                    ),
                                    MenuData(
                                        id = 8,
                                        name = stringResource(R.string._60_64)
                                    ),
                                    MenuData(
                                        id = 9,
                                        name = stringResource(R.string._65_69)
                                    ),
                                    MenuData(
                                        id = 10,
                                        name = stringResource(R.string._70_74)
                                    ),
                                    MenuData(
                                        id = 11,
                                        name = stringResource(R.string._75_79)
                                    ),
                                    MenuData(
                                        id = 12,
                                        name = stringResource(R.string._80_or_older)
                                    ),
                                ),
                                onClickOnMenuItem = onChangeAgeCategorySelected,
                                numberField = 7,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.ageCategoryData,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.gen_health),
                                value = stringResource(R.string.your_gen_health),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.poor)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.fair),
                                    ),
                                    MenuData(
                                        id = 2,
                                        name = stringResource(R.string.good)
                                    ),
                                    MenuData(
                                        id = 3,
                                        name = stringResource(R.string.very_good)
                                    ),
                                    MenuData(
                                        id = 4,
                                        name = stringResource(R.string.excellent)
                                    )
                                ),
                                onClickOnMenuItem = onChangeGenHealthSelected,
                                numberField = 8,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.genHealthData,
                            )


                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.smoking),
                                value = stringResource(R.string.do_you_smoke),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes),
                                    ),
                                ),
                                onClickOnMenuItem = onChangeSmokingSelected,
                                numberField = 9,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.smokingData,
                            )


                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.alcohol_drinking),
                                value = stringResource(R.string.do_you_drink_alcohol),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes),
                                    ),
                                ),
                                onClickOnMenuItem = onChangeAlcoholDrinkingSelected,
                                numberField = 10,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.alcoholDrinkingData,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.stroke),
                                value = stringResource(R.string.have_you_had_a_stroke),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes),
                                    ),
                                ),
                                onClickOnMenuItem = onChangeStrokeSelected,
                                numberField = 11,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.strokeData,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.diff_walking),
                                value = stringResource(R.string.your_diff_walking),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes),
                                    ),
                                ),
                                onClickOnMenuItem = onChangeDiffWalkingSelected,
                                numberField = 12,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.diffWalkingData,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.physical_activity),
                                value = stringResource(R.string.do_you_have_physical_activity),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes),
                                    ),
                                ),
                                onClickOnMenuItem = onChangePhysicalActivitySelected,
                                numberField = 13,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.physicalActivityData,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.asthma),
                                value = stringResource(R.string.do_you_have_asthma),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes),
                                    ),
                                ),
                                onClickOnMenuItem = onChangeAsthmaSelected,
                                numberField = 14,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.asthmaData,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.kidney_disease),
                                value = stringResource(R.string.do_you_have_kidney_disease),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes),
                                    ),
                                ),
                                onClickOnMenuItem = onChangeKidneyDiseaseSelected,
                                numberField = 15,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.kidneyDiseaseData,
                            )

                            MenuFieldSection(
                                dimen = dimen,
                                theme = theme,
                                title = stringResource(R.string.skin_cancer),
                                value = stringResource(R.string.do_you_have_skin_cancer),
                                menus = listOf(
                                    MenuData(
                                        id = 0,
                                        name = stringResource(R.string.no)
                                    ),
                                    MenuData(
                                        id = 1,
                                        name = stringResource(R.string.yes),
                                    ),
                                ),
                                onClickOnMenuItem = onChangeSkinCancerSelected,
                                numberField = 16,
                                numberFieldIsFocusNow = uiState.numberOfFieldIsFocus,
                                onDropMenusExpandedChanged = onMenuExpectedChanged,
                                menusExpanded = uiState.menuExpanded,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                dataSelected = uiState.skinCancerData,
                            )

                        }//end Column

                    }//end item

                }//end LazyColumn

                //if keyboard is visible
                if (keyboardIsVisible) {
                    //create operation button here

                    Box(
                        modifier = Modifier
                            .constrainAs(operationButtonId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)

                                //if keyboard is visible make button constraint from top
                                top.linkTo(
                                    parent.top,
                                    (heightScreen - (dimen.dimen_6_5 + dimen.dimen_1_5)).dp
                                )
                                bottom.linkTo(parent.bottom)
                                height = Dimension.fillToConstraints
                                width = Dimension.fillToConstraints
                            }
                            .background(
                                color = theme.background
                            )
                            .padding(
                                horizontal = dimen.dimen_2.dp
                            ),
                    ) {

                        BasicButtonView(
                            dimen = dimen,
                            theme = theme,
                            text =
                            //if field last is focus now set result text to button
                            if (uiState.numberOfFieldIsFocus == 16)
                                stringResource(
                                    id = R.string.result
                                )
                            //else set next text to button
                            else
                                stringResource(
                                    id = R.string.next
                                ),
                            onClick = if (!uiState.predictHeartDiseaseStatus.loading) {
                                onClickOnOperationButton
                            } else {
                                {}
                            },
                            load = uiState.predictHeartDiseaseStatus.loading,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end Box

                }//end if

            }//end ConstraintLayout

        }//end scaffold

    }//end BaseScreen

    //create focus effect here
    LaunchedEffect(
        key1 = uiState.numberOfFieldIsFocus
    ) {


//        if (uiState.numberOfFieldIsFocus <= 3) {
//
//            //set focus on field is focus now
//            focusRequesters[uiState.numberOfFieldIsFocus].requestFocus()
//
//        }//end if
//
//        else if (uiState.numberOfFieldIsFocus == 4) {
//            focusRequesters[3].freeFocus()
//        }//end else

    }//end LaunchedEffect

}//end RecordHeartPredictionContent

