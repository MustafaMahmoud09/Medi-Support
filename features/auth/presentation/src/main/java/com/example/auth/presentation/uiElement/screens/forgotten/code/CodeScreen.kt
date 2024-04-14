package com.example.auth.presentation.uiElement.screens.forgotten.code

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.example.auth.presentation.uiElement.components.composable.CodeFieldView
import com.example.sharedui.R
import com.example.auth.presentation.uiState.state.ForgottenUiState
import com.example.auth.presentation.uiState.viewModel.ForgottenViewModel
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay


@Composable
internal fun CodeScreen(
    viewModel: ForgottenViewModel = hiltViewModel(),
    navigateToNewPasswordDestination: () -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    val runningScreenState = rememberSaveable { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }

    //get errors messages here
    val codeNotValid =
        stringResource(R.string.the_code_is_invalid)

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val inputsError =
        stringResource(R.string.there_is_a_problem_with_the_entered_data)

    CodeContent(
        onClickOnVerifyButton = viewModel::onVerifyCodeSending,
        uiState = state.value,
        onFirstCodeChanged = viewModel::onFirstCodeChanged,
        onSecondCodeChanged = viewModel::onSecondCodeChanged,
        onThirdCodeChanged = viewModel::onThirdCodeChanged,
        onFourthCodeChanged = viewModel::onFourthCodeChanged,
        snackbarHostState = snackbarHostState
    )

    //on code sending navigate to new password destination
    LaunchedEffect(
        key1 = state.value.verifyCodeEventStatus.success
    ) {

        //check if sending code event status is success
        //navigate to new password screen
        if (state.value.verifyCodeEventStatus.success) {
            navigateToNewPasswordDestination()
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
                message = codeNotValid
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

}//end CodeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun CodeContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickOnVerifyButton: () -> Unit,
    uiState: ForgottenUiState,
    onFirstCodeChanged: (String) -> Unit,
    onSecondCodeChanged: (String) -> Unit,
    onThirdCodeChanged: (String) -> Unit,
    onFourthCodeChanged: (String) -> Unit,
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
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .background(
                        color = theme.background
                    )
            ) {
                val (title, message, rowCodeFields, verifyButton) = createRefs()

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.verification_code
                    ),
                    size = dimen.dimen_2_5,
                    modifier = Modifier
                        .constrainAs(title) {
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
                                dimen.dimen_2.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )


                TextNormalView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.message_forgot_code
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

                Row(
                    modifier = Modifier
                        .constrainAs(rowCodeFields) {
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
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    CodeFieldView(
                        dimen = dimen,
                        theme = theme,
                        value = uiState.firstCodeKey,
                        enabled = !uiState.verifyCodeEventStatus.loading,
                        onChange = onFirstCodeChanged
                    )

                    CodeFieldView(
                        dimen = dimen,
                        theme = theme,
                        value = uiState.secondCodeKey,
                        enabled = !uiState.verifyCodeEventStatus.loading,
                        onChange = onSecondCodeChanged
                    )

                    CodeFieldView(
                        dimen = dimen,
                        theme = theme,
                        value = uiState.thirdCodeKey,
                        enabled = !uiState.verifyCodeEventStatus.loading,
                        onChange = onThirdCodeChanged
                    )

                    CodeFieldView(
                        dimen = dimen,
                        theme = theme,
                        value = uiState.fourthCodeKey,
                        enabled = !uiState.verifyCodeEventStatus.loading,
                        onChange = onFourthCodeChanged
                    )

                }//end Row

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = stringResource(
                        R.string.verify
                    ),
                    onClick = if (!uiState.verifyCodeEventStatus.loading) {
                        onClickOnVerifyButton
                    } else {
                        {}
                    },
                    load = uiState.verifyCodeEventStatus.loading,
                    fontSize = dimen.dimen_2_5,
                    modifier = Modifier
                        .constrainAs(verifyButton) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                rowCodeFields.bottom,
                                dimen.dimen_3_5.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end CodeContent