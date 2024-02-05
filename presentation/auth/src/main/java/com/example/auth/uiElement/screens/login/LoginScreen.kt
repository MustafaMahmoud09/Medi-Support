package com.example.auth.uiElement.screens.login


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.CircleProgressView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.auth.R
import com.example.auth.uiElement.components.composable.IconStartButtonView
import com.example.auth.uiElement.components.items.MultiTextColorSection
import com.example.auth.uiElement.components.items.RememberSection
import com.example.auth.uiState.state.LoginUiState
import com.example.auth.uiState.viewModel.LoginViewModel
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalGrayDarkView
import com.example.sharedui.uiElement.components.composable.TextNormalRedView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.screen.BaseScreen

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToRegisterDestination: () -> Unit,
    navigateToForgotPasswordNavGraph: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    LoginContent(
        onClickCreateAccount = navigateToRegisterDestination,
        onClickForgotPassword = navigateToForgotPasswordNavGraph,
        onClickLogin = navigateToBottomDestination,
        uiState = state.value,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onRememberChanged = viewModel::onRememberChanged
    )
}//end LoginScreen

@Composable
private fun LoginContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickCreateAccount: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickLogin: () -> Unit,
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRememberChanged: (Boolean) -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        AnimatedVisibility(
            visible = uiState.pageLoad,
            enter = fadeIn(
                animationSpec = tween(150)
            ),
            exit = fadeOut(
                animationSpec = tween(150)
            )
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .background(
                        color = theme.background
                    )
            ) {
                val (progress) = createRefs()

                CircleProgressView(
                    theme = theme,
                    dimen = dimen,
                    modifier = Modifier
                        .constrainAs(progress) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                )

            }//end ConstraintLayout

        }//end AnimatedVisibility

        AnimatedVisibility(
            visible = !uiState.pageLoad,
            enter = fadeIn(
                animationSpec = tween(150)
            ),
            exit = fadeOut(
                animationSpec = tween(150)
            )
        ) {


            ConstraintLayout(
                modifier = Modifier
                    .safeDrawingPadding()
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
            ) {
                val (loginTitle, container) = createRefs()

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.log_in
                    ),
                    size = dimen.dimen_3_75,
                    modifier = Modifier
                        .constrainAs(loginTitle) {
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
                                loginTitle.bottom,
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
                            val (emailFailed, passwordFailed, textPermission, rememberSection,
                                forgotPassword, loginButton, googleButton, facebookButton, line, donHaveAccount
                            ) = createRefs()

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
                                        top.linkTo(parent.top)
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

                            TextNormalGrayDarkView(
                                theme = theme,
                                dimen = dimen,
                                text = stringResource(
                                    R.string.login_permission_text
                                ),
                                size = dimen.dimen_1_5,
                                modifier = Modifier
                                    .constrainAs(textPermission) {
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
                                            dimen.dimen_1.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            RememberSection(
                                dimen = dimen,
                                theme = theme,
                                fontColor = theme.redIcon,
                                checked = uiState.rememberKey,
                                onCheckedChange = onRememberChanged,
                                modifier = Modifier
                                    .constrainAs(rememberSection) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(
                                            textPermission.bottom,
                                            dimen.dimen_2.dp
                                        )
                                    }
                            )

                            TextNormalRedView(
                                theme = theme,
                                dimen = dimen,
                                text = stringResource(
                                    R.string.forgot_password
                                ),
                                fontColor = theme.redMedium,
                                size = dimen.dimen_1_75,
                                modifier = Modifier
                                    .constrainAs(forgotPassword) {
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(rememberSection.top)
                                        bottom.linkTo(rememberSection.bottom)
                                    }
                                    .clickable(
                                        interactionSource = remember {
                                            MutableInteractionSource()
                                        },
                                        indication = null
                                    ) { onClickForgotPassword() }
                            )

                            BasicButtonView(
                                dimen = dimen,
                                theme = theme,
                                text = stringResource(
                                    id = R.string.log_in
                                ),
                                onClick = onClickLogin,
                                fontSize = dimen.dimen_2_5,
                                modifier = Modifier
                                    .constrainAs(loginButton) {
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

                            IconStartButtonView(
                                theme = theme,
                                dimen = dimen,
                                icon = painterResource(
                                    id = R.drawable.google
                                ),
                                text = stringResource(
                                    R.string.log_in_with_google
                                ),
                                onClick = { /*TODO*/ },
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
                                            loginButton.bottom,
                                            dimen.dimen_5.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            IconStartButtonView(
                                theme = theme,
                                dimen = dimen,
                                icon = painterResource(
                                    id = R.drawable.facebook
                                ),
                                text = stringResource(
                                    R.string.log_in_with_facebook
                                ),
                                onClick = { /*TODO*/ },
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
                                            dimen.dimen_5.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            MultiTextColorSection(
                                theme = theme,
                                dimen = dimen,
                                textOne = stringResource(
                                    R.string.don_t_have_account
                                ),
                                textTwo = stringResource(
                                    R.string.sign_up
                                ),
                                colorOne = theme.black,
                                colorTwo = theme.redDark,
                                onClick = onClickCreateAccount,
                                modifier = Modifier
                                    .constrainAs(donHaveAccount) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_2.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(
                                            line.bottom,
                                            dimen.dimen_4_5.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                        }//end ConstraintLayout

                    }//end item

                }//end LazyColumn

            }//end ConstraintLayout

        }//end AnimatedVisibility

    }//end BaseScreen

}//end LoginContent