package com.example.setting.presentation.uiElement.screens.contactUs

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
import com.example.setting.presentation.uiState.state.ContactUsUiState
import com.example.setting.presentation.uiState.viewModel.ContactUsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.items.TransparentDialogSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@Composable
internal fun ContactUsScreen(
    viewModel: ContactUsViewModel = hiltViewModel(),
    popContactUsDestination: () -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val startRunning = remember {
        mutableStateOf(true)
    }

    //get errors messages here
    val emailError =
        stringResource(R.string.the_email_entered_is_not_an_email_type)


    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val inputsError =
        stringResource(R.string.there_is_a_problem_with_the_entered_data)

    ContactUsContent(
        onClickBack = popContactUsDestination,
        uiState = state.value,
        onEmailChanged = viewModel::onEmailChanged,
        onMessageChanged = viewModel::onMessageChanged,
        onUserNameChanged = viewModel::onUserNameChanged,
        onSendContactUsMessage = viewModel::onSendContactUsMessage,
        snackbarHostState = snackbarHostState
    )

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.sendContactUsEventStatus.emailError,
    ) {

        if (!startRunning.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = emailError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.sendContactUsEventStatus.internetError,
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
        key1 = state.value.sendContactUsEventStatus.serverError,
    ) {

        if (!startRunning.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = serverError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.sendContactUsEventStatus.dataError
    ) {

        if (!startRunning.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = inputsError
            )

        }

    }//end LaunchedEffect

    LaunchedEffect(key1 = true) {
        delay(250)
        startRunning.value = false
    }

}//end ContactUsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ContactUsContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    uiState: ContactUsUiState,
    onEmailChanged: KFunction1<String, Unit>,
    onMessageChanged: KFunction1<String, Unit>,
    onUserNameChanged: KFunction1<String, Unit>,
    onSendContactUsMessage: KFunction0<Unit>,
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
                    .background(
                        color = theme.background
                    )
            ) {
                val (backButton, title, fieldsId) = createRefs()

                AnimatedVisibility(
                    visible = uiState.sendContactUsEventStatus.success,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 100
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 100
                        )
                    )
                ) {

                    TransparentDialogSection(
                        onDismissRequest = { /*TODO*/ },
                        dimen = dimen,
                        theme = theme,
                        logo = painterResource(
                            id = R.drawable.success_bold
                        ),
                        tint = theme.green,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                }

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
                        R.string.contact_us_ca
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
                        .constrainAs(fieldsId) {
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

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                R.string.name
                            ),
                            hint = stringResource(
                                R.string.your_name
                            ),
                            value = uiState.userNameValue,
                            onChange = onUserNameChanged,
                            enable = !uiState.sendContactUsEventStatus.loading,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    item(
                        key = 2
                    ) {

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                R.string.email_address
                            ),
                            hint = stringResource(
                                R.string.your_email
                            ),
                            value = uiState.emailValue,
                            onChange = onEmailChanged,
                            enable = !uiState.sendContactUsEventStatus.loading,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    item(
                        key = 3
                    ) {

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                R.string.message
                            ),
                            hint = stringResource(
                                R.string.your_message
                            ),
                            fieldHeight = dimen.dimen_17_5,
                            maxLines = Int.MAX_VALUE,
                            value = uiState.messageValue,
                            contentCenter = false,
                            onChange = onMessageChanged,
                            enable = !uiState.sendContactUsEventStatus.loading,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    item(
                        key = 4
                    ) {

                        BasicButtonView(
                            dimen = dimen,
                            theme = theme,
                            text = stringResource(
                                R.string.send
                            ),
                            onClick = if (!uiState.sendContactUsEventStatus.loading) {
                                onSendContactUsMessage
                            } else {
                                {}
                            },
                            load = uiState.sendContactUsEventStatus.loading,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                }//end LazyColumn

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end ContactUsContent

