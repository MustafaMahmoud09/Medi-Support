package com.example.auth.presentation.uiElement.screens.login

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.CircleProgressView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.auth.presentation.uiElement.components.composable.CenterIconTextButtonView
import com.example.auth.presentation.uiElement.components.items.MultiTextColorSection
import com.example.auth.presentation.uiElement.components.items.RememberSection
import com.example.sharedui.R
import com.example.auth.presentation.uiState.state.LoginUiState
import com.example.auth.presentation.uiState.viewModel.LoginViewModel
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalGrayDarkView
import com.example.sharedui.uiElement.components.composable.TextNormalRedView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.screen.BaseScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToRegisterDestination: () -> Unit,
    navigateToForgotPasswordNavGraph: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    //get google client token
    val googleClientToken = stringResource(
        id = R.string.google_client_token
    )

    ///google sign in launcher
    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = viewModel::handleGoogleSignInResult
    )

    //facebook login launcher
    val facebookLauncher = rememberLauncherForActivityResult(
        contract = state.value.facebookLoginManager!!
            .createLogInActivityResultContract(
                state.value.facebookCallbackManager,
                null
            )
    ) {}


    //get errors messages here
    val emailError =
        stringResource(R.string.the_email_entered_is_not_an_email_type)

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val unAuthorizeError =
        stringResource(R.string.there_is_an_error_in_the_email_or_password)

    val inputsError =
        stringResource(R.string.there_is_a_problem_with_the_entered_data)

    val alreadyEmailExist =
        stringResource(R.string.you_have_already_registered_with_this_email)

    val currentContext = LocalContext.current

    LoginContent(
        onClickCreateAccount = {
            viewModel.onFirstRunningStateChanged(true)
            navigateToRegisterDestination()
        },
        onClickForgotPassword = {
            viewModel.onFirstRunningStateChanged(true)
            navigateToForgotPasswordNavGraph()
        },
        onClickLogin = viewModel::onExecuteLoginWithEmail,
        uiState = state.value,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        snackbarHostState = snackbarHostState,
        onRememberChanged = viewModel::onRememberChanged,
        onClickOnGoogleLoginButton = {

            //get gso
            val gso = GoogleSignInOptions
                .Builder()
                .requestIdToken(googleClientToken)
                .requestEmail()
                .build()
            //get google sign in intent from google client here
            val googleClient =
                GoogleSignIn.getClient(currentContext, gso)
            //make sign out after make new sign in
            googleClient.signOut()
            //get sign in intent here
            val signIntent = googleClient.signInIntent

            //launch google sign in intent here
            //and wait for result
            //from result get access token
            googleLauncher.launch(signIntent)

        },//end onClickOnGoogleLoginButton
        onClickOnFacebookLoginButton = {

            //get facebook login manager
            val facebookLoginManger = state.value.facebookLoginManager

            //logout before new login
            facebookLoginManger?.logOut()

            //make facebook sign in intent here
            facebookLauncher.launch(listOf("public_profile", "email"))

        }//end onClickOnGoogleLoginButton
    )

    //on register event state is success
    //navigate to home screen
    LaunchedEffect(
        key1 = state.value.loginEventStatus.success
    ) {

        if (state.value.loginEventStatus.success) {

            navigateToBottomDestination()

        }//end if

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.loginEventStatus.dataNotValid,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = emailError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.loginEventStatus.internetError,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = internetError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.loginEventStatus.unAuthorize,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = unAuthorizeError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.loginEventStatus.serverError,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = serverError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.loginEventStatus.dataNotFound,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = inputsError
            )

        }//end if

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.loginEventStatus.alreadyAuthorized,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = alreadyEmailExist
            )

        }//end if

    }//end LaunchedEffect

}//end LoginScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
    onRememberChanged: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState,
    onClickOnGoogleLoginButton: () -> Unit,
    onClickOnFacebookLoginButton: () -> Unit
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
                                    enable = !uiState.loginEventStatus.loading,
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
                                    enable = !uiState.loginEventStatus.loading,
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
                                    enable = !uiState.loginEventStatus.loading,
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
                                        .clickableWithoutHover { onClickForgotPassword() }
                                )

                                BasicButtonView(
                                    dimen = dimen,
                                    theme = theme,
                                    text = stringResource(
                                        id = R.string.log_in
                                    ),
                                    onClick = if (!uiState.loginEventStatus.loading) {
                                        onClickLogin
                                    } else {
                                        {}
                                    },
                                    load = uiState.loginEventStatus.loading &&
                                            uiState.loginEventStatus.loadingType == 1,
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

                                CenterIconTextButtonView(
                                    theme = theme,
                                    dimen = dimen,
                                    icon = painterResource(
                                        id = com.example.auth.presentation.R.drawable.google
                                    ),
                                    text = stringResource(
                                        R.string.log_in_with_google
                                    ),
                                    onClick = if (!uiState.loginEventStatus.loading) {
                                        onClickOnGoogleLoginButton
                                    } else {
                                        {}
                                    },
                                    loadingState = uiState.loginEventStatus.loading &&
                                            uiState.loginEventStatus.loadingType == 2,
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

                                CenterIconTextButtonView(
                                    theme = theme,
                                    dimen = dimen,
                                    icon = painterResource(
                                        id = com.example.auth.presentation.R.drawable.facebook
                                    ),
                                    text = stringResource(
                                        R.string.log_in_with_facebook
                                    ),
                                    onClick = if (!uiState.loginEventStatus.loading) {
                                        onClickOnFacebookLoginButton
                                    } else {
                                        {}
                                    },
                                    loadingState = uiState.loginEventStatus.loading &&
                                            uiState.loginEventStatus.loadingType == 3,
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

            }//end Scaffold

        }//end AnimatedVisibility

    }//end BaseScreen

}//end LoginContent



