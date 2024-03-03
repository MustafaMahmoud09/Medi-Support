package com.example.reminder.presentation.uiElement.screens.add

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.reminder.presentation.uiElement.components.items.DatePickerSection
import com.example.reminder.presentation.uiState.state.AddReminderUiState
import com.example.reminder.presentation.uiState.viewModel.AddReminderViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.example.sharedui.uiState.viewModel.child.BottomNavigationViewModel
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun AddReminderScreen(
    bottomNavigationViewModel: BottomNavigationViewModel = hiltViewModel(),
    addReminderViewModel: AddReminderViewModel = hiltViewModel(),
    popAddReminderDestination: () -> Unit,
    navigateToReminderRecordsDestination: () -> Unit
) {
    //collect screen state here
    val state = addReminderViewModel.state.collectAsState()

    AddReminderContent(
        uiState = state.value,
        onClickOnDay = addReminderViewModel::onWeekDaySelected,
        onMedicamentNameChanged = addReminderViewModel::onMedicamentNameChanged,
        onTimeValueChanged = addReminderViewModel::onTimeValueChanged,
        onClickOnDatePickerCancel = {
            //change bottom navigation visibility here
            bottomNavigationViewModel.onBottomNavigationVisibilityChanged(
                show = true
            )

            //change date picker visibility here
            addReminderViewModel.onDatePickerVisibilityChanged(
                show = false
            )

            //change days selected state
            addReminderViewModel.onWeekDaysSelectedCanceled()

            //change time selected state
            addReminderViewModel.onTimeSelectedCanceled()
        },//end onClickOnDatePickerCancel
        onClickOnDatePickerConfirm = {
            //change bottom navigation visibility here
            bottomNavigationViewModel.onBottomNavigationVisibilityChanged(
                show = true
            )

            //change date picker visibility here
            addReminderViewModel.onDatePickerVisibilityChanged(
                show = false
            )

            //change days selected state
            addReminderViewModel.onWeekDaysSelectedConfirm()

            //change time selected state
            addReminderViewModel.onTimeSelectedConfirm()
        },//end onClickOnDatePickerConfirm
        onClickOnBack = {

            //pop reminder destination here
            popAddReminderDestination()

            //change bottom navigation visibility here
            bottomNavigationViewModel.onBottomNavigationVisibilityChanged(
                show = true
            )

            //change date picker visibility here
            addReminderViewModel.onDatePickerVisibilityChanged(
                show = false
            )

        },//end onClickOnBack
        onClickOnTimeSelectorButton = {
            //change bottom navigation visibility here
            bottomNavigationViewModel.onBottomNavigationVisibilityChanged(
                show = false
            )

            //change date picker visibility here
            addReminderViewModel.onDatePickerVisibilityChanged(
                show = true
            )
        },//end onClickOnTimeSelectorButton
        onClickOnReminderButton = {

            //navigate to reminder records destination here
            navigateToReminderRecordsDestination()

            //change bottom navigation visibility here
            bottomNavigationViewModel.onBottomNavigationVisibilityChanged(
                show = true
            )

            //change date picker visibility here
            addReminderViewModel.onDatePickerVisibilityChanged(
                show = false
            )

        }//end onClickOnReminderButton
    )

}//end AddReminderScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun AddReminderContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBack: () -> Unit,
    onClickOnReminderButton: () -> Unit,
    screenWidth: Int = LocalConfiguration.current.screenWidthDp,
    onClickOnTimeSelectorButton: () -> Unit,
    uiState: AddReminderUiState,
    onMedicamentNameChanged: (String) -> Unit,
    onClickOnDatePickerCancel: () -> Unit,
    onClickOnDay: (Long) -> Unit,
    onClickOnDatePickerConfirm: () -> Unit,
    onTimeValueChanged: (LocalTime) -> Unit
) {

    //create container here
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        //create ids for screen components here
        val (headerId, reminderButtonId, contentId) = createRefs()

        //create header here
        HeaderSection(
            dimen = dimen,
            theme = theme,
            onClickOnBackButton = onClickOnBack,
            title = stringResource(
                id = R.string.medicine_reminder
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
                        dimen.dimen_4.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create reminder button here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickOnReminderButton,
            icon = painterResource(
                id = R.drawable.reminder_icon
            ),
            tint = theme.redDark,
            modifier = Modifier
                .constrainAs(reminderButtonId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_4.dp
                    )
                }//end constrainAs
        )

        //create column contain on screen components here
        LazyColumn(
            modifier = Modifier
                .constrainAs(contentId) {
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
                top = dimen.dimen_3.dp,
                bottom = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_2.dp
            )
        ) {

            //create item contain on all components here
            item(
                key = 1
            ) {

                //create container here
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    //create ids for components here
                    val (medicineNameFieldId, dayFieldId,
                        timeFieldId, addAlarmButtonId, datePickerId) = createRefs()

                    //create guides here
                    val guideFromStart50P = createGuidelineFromStart(.50f)

                    //create medicament field here
                    BasicFieldSection(
                        theme = theme,
                        dimen = dimen,
                        title = stringResource(
                            R.string.medicament_name
                        ),
                        hint = stringResource(
                            R.string.your_medicament_name
                        ),
                        value = uiState.medicamentName,
                        onChange = onMedicamentNameChanged,
                        modifier = Modifier
                            .constrainAs(medicineNameFieldId) {
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

                    //create day field here
                    BasicFieldSection(
                        theme = theme,
                        dimen = dimen,
                        title = stringResource(
                            R.string.day
                        ),
                        hint = stringResource(
                            R.string.every_day
                        ),
                        value = uiState.daysValue,
                        onChange = {},
                        enable = false,
                        onClick = onClickOnTimeSelectorButton,
                        hintSize = dimen.dimen_2_5,
                        inputSize = dimen.dimen_1_25,
                        modifier = Modifier
                            .constrainAs(dayFieldId) {
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_2.dp
                                )
                                end.linkTo(
                                    guideFromStart50P,
                                    dimen.dimen_0_75.dp
                                )
                                top.linkTo(
                                    medicineNameFieldId.bottom,
                                    dimen.dimen_2_5.dp
                                )
                                width = Dimension.fillToConstraints
                            }
                    )

                    //create time field here
                    BasicFieldSection(
                        theme = theme,
                        dimen = dimen,
                        title = stringResource(
                            R.string.time
                        ),
                        hint = stringResource(
                            R.string._7_00_am
                        ),
                        value = uiState.timeValue,
                        onChange = {},
                        enable = false,
                        onClick = onClickOnTimeSelectorButton,
                        inputSize = dimen.dimen_2_5,
                        modifier = Modifier
                            .constrainAs(timeFieldId) {
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_2.dp
                                )
                                start.linkTo(
                                    guideFromStart50P,
                                    dimen.dimen_0_75.dp
                                )
                                top.linkTo(dayFieldId.top)
                                width = Dimension.fillToConstraints
                            }
                    )

                    //create add alarm button here
                    BasicButtonView(
                        dimen = dimen,
                        theme = theme,
                        text = stringResource(
                            id = R.string.add_alarm
                        ),
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .constrainAs(addAlarmButtonId) {
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_2.dp
                                )
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_2.dp
                                )
                                top.linkTo(
                                    timeFieldId.bottom,
                                    dimen.dimen_3.dp
                                )
                                width = Dimension.fillToConstraints
                            }
                    )

                    //check date picker is visible or no
                    AnimatedVisibility(
                        visible = uiState.isDatePickerVisible,
                        enter = fadeIn(
                            animationSpec = tween(
                                delayMillis = 50
                            )
                        ),
                        exit = fadeOut(
                            animationSpec = tween(
                                delayMillis = 50
                            )
                        ),
                        modifier = Modifier
                            .constrainAs(datePickerId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                top.linkTo(
                                    addAlarmButtonId.bottom,
                                    dimen.dimen_7.dp
                                )
                            }
                    ) {

                        //create date picker here
                        DatePickerSection(
                            dimen = dimen,
                            theme = theme,
                            containerWidth = screenWidth,
                            onClickOnSaveButton = onClickOnDatePickerConfirm,
                            onClickOnCancelButton = onClickOnDatePickerCancel,
                            onSnappedTime = onTimeValueChanged,
                            weekDays = uiState.weekDays,
                            daysSelected = uiState.daysSelected,
                            onClickOnDay = onClickOnDay,
                            timeSelected = uiState.timeSelected,
                        )
                    }//end if

                }//end ConstraintLayout

            }//end item

        }//end LazyColumn

    }//end ConstraintLayout

}//end AddReminderContent