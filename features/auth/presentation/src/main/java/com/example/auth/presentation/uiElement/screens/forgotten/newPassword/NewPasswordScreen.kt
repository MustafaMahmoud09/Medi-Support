package com.example.auth.presentation.uiElement.screens.forgotten.newPassword

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharedui.R
import com.example.auth.presentation.uiState.state.ForgottenUiState
import com.example.auth.presentation.uiState.viewModel.ForgottenViewModel
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.BasicDialogSection
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay
import kotlin.reflect.KFunction0

@Composable
internal fun NewPasswordScreen(
    viewModel: ForgottenViewModel = hiltViewModel(),
    backToLoginNavGraph: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    val runningScreenState = rememberSaveable { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }

    //get errors messages here
    val passwordNotSecure =
        stringResource(R.string.the_password_is_not_secure)

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val inputsError =
        stringResource(R.string.there_is_a_problem_with_the_entered_data)

    NewPasswordContent(
        onClickBackLogin = backToLoginNavGraph,
        uiState = state.value,
        onNewPasswordChanged = viewModel::onNewPasswordChanged,
        onClickOnResetPasswordButton = viewModel::onResetUserPassword,
        onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,
        snackbarHostState = snackbarHostState
    )


    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.sendEmailEventStatus.dataNotValid,
    ) {

        if (!runningScreenState.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = passwordNotSecure
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

}//end NewPasswordScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun NewPasswordContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBackLogin: () -> Unit,
    uiState: ForgottenUiState,
    onNewPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onClickOnResetPasswordButton: KFunction0<Unit>,
    snackbarHostState: SnackbarHostState
) {

    //create title animated color here
    val titleAnimatedColor by animateColorAsState(
        targetValue = if (uiState.resetPasswordEventStatus.success) theme.redDark else theme.black,
        label = "titleAnimatedColor",
        animationSpec = snap()
    )

    //create title animated size here
    val titleAnimatedSize by animateFloatAsState(
        targetValue = if (uiState.resetPasswordEventStatus.success) dimen.dimen_2_75 else dimen.dimen_2_5,
        animationSpec = snap(),
        label = "titleAnimatedSize"
    )

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
                val (title, passwordFailed, confirmPassword, resetPasswordButton, dialog) = createRefs()

                //dialog back to login destination
                AnimatedVisibility(
                    visible = uiState.resetPasswordEventStatus.success,
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

                    BasicDialogSection(
                        onDismissRequest = {},
                        dimen = dimen,
                        theme = theme,
                        logo = painterResource(
                            id = R.drawable.success
                        ),
                        tint = theme.greenLight,
                        buttonTitle = stringResource(
                            id = R.string.go_to_back
                        ),
                        onClick = onClickBackLogin,
                        proText = stringResource(
                            id = R.string.done
                        ),
                        modifier = Modifier
                            .constrainAs(dialog) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                            }
                            .aspectRatio(.9f)
                            .padding(
                                horizontal = dimen.dimen_2.dp
                            )
                    )

                }//end if

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.new_password
                    ),
                    size = titleAnimatedSize,
                    color = titleAnimatedColor,
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

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(
                        R.string.password
                    ),
                    hint = stringResource(
                        R.string.your_password
                    ),
                    value = uiState.newPasswordKey,
                    fieldIsPassword = true,
                    onChange = onNewPasswordChanged,
                    enable = !uiState.resetPasswordEventStatus.loading,
                    modifier = Modifier
                        .constrainAs(passwordFailed) {
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
                                dimen.dimen_9_5.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(
                        R.string.confirm_password
                    ),
                    hint = stringResource(
                        R.string.your_password
                    ),
                    value = uiState.confirmNewPasswordKey,
                    fieldIsPassword = true,
                    enable = !uiState.resetPasswordEventStatus.loading,
                    onChange = onConfirmPasswordChanged,
                    modifier = Modifier
                        .constrainAs(confirmPassword) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                passwordFailed.bottom,
                                dimen.dimen_2.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = stringResource(R.string.resat_password),
                    onClick = if (!uiState.resetPasswordEventStatus.loading) {
                        onClickOnResetPasswordButton
                    } else {
                        {}
                    },
                    load = uiState.resetPasswordEventStatus.loading,
                    fontSize = dimen.dimen_2_5,
                    modifier = Modifier
                        .constrainAs(resetPasswordButton) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                confirmPassword.bottom,
                                dimen.dimen_2.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end NewPasswordContent


