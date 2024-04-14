package com.example.auth.presentation.uiElement.screens.register

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
internal fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    popRegisterDestination: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {
    val state = viewModel.state.collectAsState()

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

    val currentContext = LocalContext.current

    //get errors messages here
    val emailNotValid =
        stringResource(R.string.you_have_already_registered_with_this_email)

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val inputsError =
        stringResource(R.string.there_is_a_problem_with_the_entered_data)

    val snackbarHostState = remember { SnackbarHostState() }

    RegisterContent(
        onClickLogin = popRegisterDestination,
        uiState = state.value,
        onFirstNameChanged = viewModel::onFirstNameChanged,
        onLastNameChanged = viewModel::onLastNameChange,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onRememberChanged = viewModel::onRememberChanged,
        onClickOnRegisterButton = viewModel::onUserAccountCreated,
        snackbarHostState = snackbarHostState,
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

        },
        onClickOnFacebookLoginButton = {

            //get facebook login manager
            val facebookLoginManger = state.value.facebookLoginManager

            //logout before new login
            facebookLoginManger?.logOut()

            //make facebook sign in intent here
            facebookLauncher.launch(listOf("public_profile", "email"))

        }//end onClickOnGoogleLoginButton

    ) //end onClickOnGoogleLoginButton

    //on register event state is success
    //navigate to login screen
    LaunchedEffect(
        key1 = state.value.registerEventState.success
    ) {

        //if success status is true
        if (state.value.registerEventState.success) {

            //if operation type greater than 1 navigate to home destination
            if (state.value.registerEventState.loadingType > 1) {
                navigateToBottomDestination()
            }//end if
            else {
                popRegisterDestination()
            }//end else

        }//end if

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.registerEventState.dataNotValid,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = emailNotValid
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.registerEventState.internetError,
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
        key1 = state.value.registerEventState.serverError,
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
        key1 = state.value.registerEventState.dataNotFound,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = inputsError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.registerEventState.alreadyAuthorized,
    ) {

        if (!state.value.firstRunningState) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = emailNotValid
            )

        }//end if

    }//end LaunchedEffect

}//end RegisterScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
    onClickOnRegisterButton: () -> Unit,
    snackbarHostState: SnackbarHostState,
    onClickOnGoogleLoginButton: () -> Unit,
    onClickOnFacebookLoginButton: () -> Unit
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
                                load = uiState.registerEventState.loading &&
                                        uiState.registerEventState.loadingType == 1,
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
                                    onClickOnGoogleLoginButton
                                } else {
                                    {}
                                },
                                loadingState = uiState.registerEventState.loading &&
                                        uiState.registerEventState.loadingType == 2,
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
                                    onClickOnFacebookLoginButton
                                } else {
                                    {}
                                },
                                loadingState = uiState.registerEventState.loading &&
                                        uiState.registerEventState.loadingType == 3,
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

        }//end Scaffold

    }//end BaseScreen

}//end RegisterContent