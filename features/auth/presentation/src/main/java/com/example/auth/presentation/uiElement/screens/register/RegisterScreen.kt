package com.example.auth.presentation.uiElement.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
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
import com.example.auth.presentation.uiElement.components.composable.CenterIconTextButtonView
import com.example.auth.presentation.uiElement.components.items.RememberSection
import com.example.sharedui.R
import com.example.auth.presentation.uiState.state.RegisterUiState
import com.example.auth.presentation.uiState.viewModel.RegisterViewModel
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalRedView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    popRegisterDestination: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    RegisterContent(
        onClickLogin = popRegisterDestination,
        uiState = state.value,
        onFirstNameChanged = viewModel::onFirstNameChanged,
        onLastNameChanged = viewModel::onLastNameChange,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onRememberChanged = viewModel::onRememberChanged,
        onClickOnRegisterButton = viewModel::onUserAccountCreated
    )

    LaunchedEffect(
        key1 = state.value.registerEventState.success
    ) {

        if (state.value.registerEventState.success) {

            popRegisterDestination()

        }//end if

    }//end LaunchedEffect

}//end RegisterScreen

@Composable
private fun RegisterContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickLogin: () -> Unit,
    uiState: RegisterUiState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRememberChanged: (Boolean) -> Unit,
    onClickOnRegisterButton: () -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .background(
                    color = theme.background
                )
        ) {
            val (registerTitle, container) = createRefs()

            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    id = R.string.sign_up
                ),
                size = dimen.dimen_3_75,
                modifier = Modifier
                    .constrainAs(registerTitle) {
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

            LazyColumn(
                modifier = Modifier
                    .constrainAs(container) {
                        top.linkTo(
                            registerTitle.bottom,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(
                            parent.bottom
                        )
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    bottom = dimen.dimen_2.dp,
                    top = dimen.dimen_1.dp
                )
            ) {

                item(
                    key = 1
                ) {

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        val (firstName, lastName, emailFailed, passwordFailed,
                            rememberSection, registerButton, googleButton, facebookButton,
                            line, haveAccount) = createRefs()
                        val guideLineFromStart50P = createGuidelineFromStart(.5f)

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                R.string.first_name
                            ),
                            hint = stringResource(
                                R.string.fname
                            ),
                            value = uiState.firstNameKey,
                            onChange = onFirstNameChanged,
                            enable = !uiState.registerEventState.loading,
                            modifier = Modifier
                                .constrainAs(firstName) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        guideLineFromStart50P,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(parent.top)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                R.string.last_name
                            ),
                            hint = stringResource(
                                R.string.lname
                            ),
                            value = uiState.lastNameKey,
                            onChange = onLastNameChanged,
                            enable = !uiState.registerEventState.loading,
                            modifier = Modifier
                                .constrainAs(lastName) {
                                    start.linkTo(
                                        guideLineFromStart50P,
                                        dimen.dimen_1.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(parent.top)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                R.string.email_address
                            ),
                            hint = stringResource(
                                R.string.your_email
                            ),
                            value = uiState.emailKey,
                            onChange = onEmailChanged,
                            enable = !uiState.registerEventState.loading,
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
                                        firstName.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                com.example.sharedui.R.string.password
                            ),
                            hint = stringResource(
                                com.example.sharedui.R.string.your_password
                            ),
                            value = uiState.passwordKey,
                            fieldIsPassword = true,
                            enable = !uiState.registerEventState.loading,
                            onChange = onPasswordChanged,
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
                                        emailFailed.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        RememberSection(
                            dimen = dimen,
                            theme = theme,
                            fontColor = theme.black,
                            checked = uiState.rememberKey,
                            onCheckedChange = onRememberChanged,
                            enable = !uiState.registerEventState.loading,
                            modifier = Modifier
                                .constrainAs(rememberSection) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        passwordFailed.bottom,
                                        dimen.dimen_2.dp
                                    )
                                }
                        )

                        BasicButtonView(
                            dimen = dimen,
                            theme = theme,
                            text = stringResource(
                                id = R.string.sign_up
                            ),
                            onClick = if (!uiState.registerEventState.loading) {
                                onClickOnRegisterButton
                            } else {
                                {}
                            },
                            fontSize = dimen.dimen_2_5,
                            modifier = Modifier
                                .constrainAs(registerButton) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        rememberSection.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        CenterIconTextButtonView(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = com.example.auth.presentation.R.drawable.google
                            ),
                            text = stringResource(
                                R.string.log_in_with_google
                            ),
                            onClick = if (!uiState.registerEventState.loading) {
                                {}
                            } else {
                                {}
                            },
                            modifier = Modifier
                                .constrainAs(googleButton) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        registerButton.bottom,
                                        dimen.dimen_4.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        CenterIconTextButtonView(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = com.example.auth.presentation.R.drawable.facebook
                            ),
                            text = stringResource(
                                R.string.log_in_with_facebook
                            ),
                            onClick = if (!uiState.registerEventState.loading) {
                                {}
                            } else {
                                {}
                            },
                            modifier = Modifier
                                .constrainAs(facebookButton) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        googleButton.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        LineView(
                            dimen = dimen,
                            theme = theme,
                            modifier = Modifier
                                .constrainAs(line) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        facebookButton.bottom,
                                        dimen.dimen_4.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        TextNormalRedView(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                R.string.already_have_an_account
                            ),
                            size = dimen.dimen_1_75,
                            modifier = Modifier
                                .constrainAs(haveAccount) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(
                                        line.bottom,
                                        dimen.dimen_4.dp
                                    )
                                }
                                .clickableWithoutHover { onClickLogin() }
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

}//end RegisterContent