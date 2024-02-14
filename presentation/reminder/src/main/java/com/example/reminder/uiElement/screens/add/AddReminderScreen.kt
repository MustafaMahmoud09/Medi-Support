package com.example.reminder.uiElement.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun AddReminderScreen(
    popAddReminderDestination: () -> Unit,
    navigateToReminderRecordsDestination: () -> Unit
) {

    AddReminderContent(
        onClickOnBack = popAddReminderDestination,
        onClickOnReminderButton = navigateToReminderRecordsDestination
    )
}//end AddReminderScreen

@Composable
private fun AddReminderContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBack: () -> Unit,
    onClickOnReminderButton: () -> Unit
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
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp,
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
                    val (medicineNameFieldId, dayFieldId, timeFieldId, addAlarmButtonId) = createRefs()

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
                        value = "",
                        onChange = {},
                        modifier = Modifier
                            .constrainAs(medicineNameFieldId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
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
                        value = "",
                        onChange = {},
                        enable = false,
                        onClick = {},
                        fontSize = dimen.dimen_2_5,
                        modifier = Modifier
                            .constrainAs(dayFieldId) {
                                start.linkTo(parent.start)
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
                        value = "",
                        onChange = {},
                        enable = false,
                        onClick = {},
                        fontSize = dimen.dimen_2_5,
                        modifier = Modifier
                            .constrainAs(timeFieldId) {
                                end.linkTo(parent.end)
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
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(
                                    timeFieldId.bottom,
                                    dimen.dimen_3.dp
                                )
                                width = Dimension.fillToConstraints
                            }
                    )

                }//end ConstraintLayout

            }//end item

        }//end LazyColumn

    }//end ConstraintLayout

}//end AddReminderContent