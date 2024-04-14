package com.example.auth.presentation.uiElement.screens.forgotten.email

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharedui.R
import com.example.auth.presentation.uiState.state.ForgottenUiState
import com.example.auth.presentation.uiState.viewModel.ForgottenViewModel
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay


@Composable
internal fun EmailScreen(
    viewModel: ForgottenViewModel = hiltViewModel(),
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    val runningScreenState = rememberSaveable { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }

    //get errors messages here
    val accountNotValid =
        stringResource(R.string.there_is_no_available_account_that_owns_this_email)

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val inputsError =
        stringResource(R.string.there_is_a_problem_with_the_entered_data)

    EmailContent(
        onClickBack = popForgotPasswordNavGraph,
        onClickSendEmail = viewModel::onUserEmailSending,
        uiState = state.value,
        onEmailChanged = viewModel::onEmailChanged,
        snackbarHostState = snackbarHostState
    )


    //on email sending navigate to code screen
    LaunchedEffect(
        key1 = state.value.sendEmailEventStatus.success
    ) {

        //check if sending email event status is success
        //navigate to code screen
        if (state.value.sendEmailEventStatus.success) {
            navigateToCodeDestination()
        }//end if

    }//end LaunchedEffect


    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.sendEmailEventStatus.dataNotValid,
    ) {

        if (!runningScreenState.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = accountNotValid
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.sendEmailEventStatus.internetError,
    ) {

        if (!runningScreenState.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = internetError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.sendEmailEventStatus.serverError,
    ) {

        if (!runningScreenState.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = serverError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.sendEmailEventStatus.dataNotFound,
    ) {

        if (!runningScreenState.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = inputsError
            )

        }

    }//end LaunchedEffect


    //change running screen state after 1 second
    LaunchedEffect(
        key1 = true
    ) {

        delay(250)
        runningScreenState.value = false

    }//end LaunchedEffect

}//end EmailScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun EmailContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    onClickSendEmail: () -> Unit,
    uiState: ForgottenUiState,
    onEmailChanged: (String) -> Unit,
    snackbarHostState: SnackbarHostState
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
                    .safeDrawingPadding()
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
            ) {
                val (backButton, title, message, emailFailed, sendButton) = createRefs()
                val guideLineFromEnd44DP = createGuidelineFromEnd(dimen.dimen_5_5.dp)

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
                                dimen.dimen_2.dp
                            )
                        }
                )

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.forgotten_your_password
                    ),
                    size = dimen.dimen_2_5,
                    modifier = Modifier
                        .constrainAs(title) {
                            start.linkTo(
                                backButton.end,
                                dimen.dimen_1.dp
                            )
                            end.linkTo(
                                guideLineFromEnd44DP,
                                dimen.dimen_1.dp
                            )
                            top.linkTo(backButton.top)
                            bottom.linkTo(backButton.bottom)
                            width = Dimension.fillToConstraints
                        }
                )

                TextNormalView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.message_email_forgotten
                    ),
                    size = dimen.dimen_1_75,
                    fontColor = theme.black,
                    modifier = Modifier
                        .constrainAs(message) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_3.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_3.dp
                            )
                            top.linkTo(
                                title.bottom,
                                dimen.dimen_1_5.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(R.string.email_address),
                    hint = stringResource(
                        R.string.hint_email_forgotten
                    ),
                    value = uiState.emailKey,
                    onChange = onEmailChanged,
                    enable = !uiState.sendEmailEventStatus.loading,
                    modifier = Modifier
                        .constrainAs(emailFailed) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                message.bottom,
                                dimen.dimen_4.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = stringResource(
                        R.string.send_email
                    ),
                    onClick = if (!uiState.sendEmailEventStatus.loading) {
                        onClickSendEmail
                    } else {
                        {}
                    },
                    load = uiState.sendEmailEventStatus.loading,
                    fontSize = dimen.dimen_2_5,
                    modifier = Modifier
                        .constrainAs(sendButton) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                emailFailed.bottom,
                                dimen.dimen_2.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end EmailContent