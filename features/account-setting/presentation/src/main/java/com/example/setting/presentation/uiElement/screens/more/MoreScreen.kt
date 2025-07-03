@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.setting.presentation.uiElement.screens.more

import android.annotation.SuppressLint
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.setting.presentation.uiElement.components.items.CancelDialogSection
import com.example.setting.presentation.uiElement.components.items.LogoutDialogSection
import com.example.setting.presentation.uiState.state.MoreUiState
import com.example.setting.presentation.uiState.viewModel.MoreViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.EndIconButtonSection
import com.example.sharedui.uiElement.components.items.HorizontalIconButtonSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@Composable
internal fun MoreScreen(
    viewModel: MoreViewModel = hiltViewModel(),
    popMoreNavGraph: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val startRunning = remember {
        mutableStateOf(true)
    }

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    MoreContent(
        onClickBack = popMoreNavGraph,
        onClickContactUs = navigateToContactUsDestination,
        onClickAbout = navigateToAboutDestination,
        onClickOnBookingDetailsButton = navigateToBookingDetailsDestination,
        uiState = state.value,
        onDeleteDialogStateChanged = viewModel::onDeleteDialogStateChanged,
        onDeleteUserAccount = viewModel::onDeleteUserAccount,
        snackbarHostState = snackbarHostState,
        onLogoutDialogStateChanged = viewModel::onLogoutDialogStateChanged,
        onLogout = viewModel::onLogout
    )

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.deleteUserAccountStatus.success,
        key2 = state.value.logoutStatus.success
    ) {

        if (
            state.value.deleteUserAccountStatus.success ||
            state.value.logoutStatus.success
        ) {
            navigateToLoginNavGraphWithPopBottomDestination()
        }//end if

    }//end LaunchedEffect


    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.deleteUserAccountStatus.internetError,
        key2 = state.value.logoutStatus.internetError
    ) {

        if (!startRunning.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = internetError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.deleteUserAccountStatus.serverError,
        key2 = state.value.logoutStatus.serverError
    ) {

        if (!startRunning.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = serverError
            )

        }

    }//end LaunchedEffect

}//end MoreScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MoreContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    onClickContactUs: () -> Unit,
    onClickAbout: () -> Unit,
    onClickOnBookingDetailsButton: (Int) -> Unit,
    uiState: MoreUiState,
    onDeleteDialogStateChanged: KFunction1<Boolean, Unit>,
    onDeleteUserAccount: KFunction0<Unit>,
    snackbarHostState: SnackbarHostState,
    onLogoutDialogStateChanged: KFunction1<Boolean, Unit>,
    onLogout: KFunction0<Unit>
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }//end snack bar Host
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
            ) {
                val (backButton, title, buttonsId, deleteAccountDialogId, logoutDialogId) = createRefs()


                AnimatedVisibility(
                    visible = uiState.deleteDialogState,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 150
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 150
                        )
                    )
                ) {

                    CancelDialogSection(
                        onDismissRequest = { /*TODO*/ },
                        dimen = dimen,
                        theme = theme,
                        onClickOnCancel = if (!uiState.deleteUserAccountStatus.loading) {
                            { onDeleteDialogStateChanged(false) }
                        } else {
                            {}
                        },
                        logo = painterResource(
                            id = R.drawable.delete_icon
                        ),
                        title = stringResource(
                            id = R.string.delete_account
                        ),
                        message = stringResource(
                            id = R.string.the_account_will_be_completely_deleted
                        ),
                        buttonTitle = stringResource(
                            id = R.string.delete
                        ),
                        onClick = if (!uiState.deleteUserAccountStatus.loading) {
                            onDeleteUserAccount
                        } else {
                            {}
                        },
                        load = uiState.deleteUserAccountStatus.loading,
                        modifier = Modifier
                            .constrainAs(deleteAccountDialogId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                            }
                    )

                }//end AnimatedVisibility

                AnimatedVisibility(
                    visible = uiState.logoutDialogState,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 150
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 150
                        )
                    )
                ) {

                    LogoutDialogSection(
                        onDismissRequest = { /*TODO*/ },
                        dimen = dimen,
                        theme = theme,
                        onClickOnCancel = if (!uiState.logoutStatus.loading) {
                            { onLogoutDialogStateChanged(false) }
                        } else {
                            {}
                        },
                        load = uiState.logoutStatus.loading,
                        title = stringResource(R.string.log_out_of_your_account),
                        cancelButtonTitle = stringResource(R.string.cancel).uppercase(),
                        logoutButtonTitle = stringResource(R.string.log_out).uppercase(),
                        onClickOnLogout = if (!uiState.logoutStatus.loading) {
                            onLogout
                        } else {
                            {}
                        },
                        modifier = Modifier
                            .constrainAs(logoutDialogId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                            }
                    )

                }//end AnimatedVisibility

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

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.more
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

                LazyColumn(
                    modifier = Modifier
                        .constrainAs(buttonsId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                title.bottom,
                                dimen.dimen_2.dp
                            )
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(
                        start = dimen.dimen_2.dp,
                        end = dimen.dimen_2.dp,
                        bottom = dimen.dimen_2.dp,
                        top = dimen.dimen_2.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        dimen.dimen_3.dp
                    )
                ) {

                    item(
                        key = 1
                    ) {

                        EndIconButtonSection(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                R.string.details_of_booking_doctor
                            ),
                            icon = painterResource(
                                id = R.drawable.back_fw
                            ),
                            onClick = { onClickOnBookingDetailsButton(0) },
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    item(
                        key = 2
                    ) {

                        EndIconButtonSection(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                R.string.about
                            ),
                            icon = painterResource(
                                id = R.drawable.back_fw
                            ),
                            onClick = onClickAbout,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    item(
                        key = 3
                    ) {

                        EndIconButtonSection(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                R.string.contact_us
                            ),
                            icon = painterResource(
                                id = R.drawable.back_fw
                            ),
                            onClick = onClickContactUs,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    item(
                        key = 4
                    ) {

                        EndIconButtonSection(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                R.string.log_out
                            ),
                            icon = painterResource(
                                id = R.drawable.back_fw
                            ),
                            onClick = { onLogoutDialogStateChanged(true) },
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    item(
                        key = 5
                    ) {

                        HorizontalIconButtonSection(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                R.string.delete_account
                            ),
                            iconEnd = painterResource(
                                id = R.drawable.back_fw
                            ),
                            iconStart = painterResource(
                                id = R.drawable.delete_icon
                            ),
                            onClick = {
                                onDeleteDialogStateChanged(true)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                }//end LazyColumn

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end MoreContent