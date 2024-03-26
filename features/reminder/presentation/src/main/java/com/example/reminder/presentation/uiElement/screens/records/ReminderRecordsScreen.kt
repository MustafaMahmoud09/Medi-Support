@file:OptIn(ExperimentalFoundationApi::class)

package com.example.reminder.presentation.uiElement.screens.records

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel
import com.example.reminder.presentation.uiElement.components.items.ReminderSection
import com.example.reminder.presentation.uiState.state.RemindersUiState
import com.example.reminder.presentation.uiState.viewModel.RemindersViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun ReminderRecordsScreen(
    viewModel: RemindersViewModel = hiltViewModel(),
    popReminderRecordsDestination: () -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    ReminderRecordsContent(
        uiState = state.value,
        onClickBack = popReminderRecordsDestination,
        onReminderStatusChanged = viewModel::onReminderStatusUpdated,
        remindersState = state.value.reminders?.collectAsLazyPagingItems(),
        reminderBackupState = state.value.remindersBackup?.collectAsLazyPagingItems(),
        onReminderBackupCreated = viewModel::onReminderBackupCreated,
    )
}//end ReminderRecordsScreen

@Composable
private fun ReminderRecordsContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    uiState: RemindersUiState,
    onReminderStatusChanged: (Boolean, Long) -> Unit,
    remindersState: LazyPagingItems<ReminderPresentationModel>?,
    onReminderBackupCreated: () -> Unit,
    reminderBackupState: LazyPagingItems<ReminderPresentationModel>?
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
        val (backButton, title, reminderButtonId, remindersContainerId) = createRefs()

        //create back button here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickBack,
            modifier = Modifier
                .constrainAs(backButton) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_3.dp
                    )
                }//end constrainAs
        )

        //create medicine reminder title here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                id = R.string.medicine_reminder
            ),
            size = dimen.dimen_2_25,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        parent.top,
                        dimen.dimen_3.dp
                    )
                }//end constrainAs
        )

        //create reminder icon here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = {},
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
                        dimen.dimen_3.dp
                    )
                }//end constrainAs
        )

        if (
            remindersState?.loadState?.refresh is LoadState.NotLoading ||
            reminderBackupState?.loadState?.refresh is LoadState.NotLoading
        ) {

            //create reminders container here
            LazyColumn(
                modifier = Modifier
                    .constrainAs(remindersContainerId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            title.bottom,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    horizontal = dimen.dimen_2.dp,
                    vertical = dimen.dimen_1_75.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = dimen.dimen_2.dp
                )
            ) {

                val state = if (remindersState?.loadState?.refresh is LoadState.NotLoading) {
                    remindersState
                } else {
                    reminderBackupState
                }

                //create reminder items here
                items(
                    count = state!!.itemCount,
                    key = { index -> state[index]!!.id }
                ) { index ->

                    //create single reminder here
                    ReminderSection(
                        dimen = dimen,
                        theme = theme,
                        reminder = state[index]!!,
                        background = if (index % 2 == 0) theme.grayECECEC else theme.redLightFF979B,
                        onCheckedChange = onReminderStatusChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement()
                    )

                }//end items

            }//end LazyColumn

        }//end if

    }//end ConstraintLayout

    LaunchedEffect(
        key1 = remindersState?.loadState?.refresh
    ) {

        if (remindersState?.loadState?.refresh is LoadState.NotLoading) {

            onReminderBackupCreated()

        }//end if

    }//end LaunchedEffect

}//end ReminderRecordsContent
