package com.example.onlinebooking.presentation.uiElement.screens.booking

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlinebooking.presentation.uiState.state.OnlineBookingUiState
import com.example.onlinebooking.presentation.uiState.viewModel.OnlineBookingViewModel
import com.example.sharedui.uiElement.components.items.BookedDoctorInformationSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.ServerLoadImageView
import com.example.sharedui.uiElement.components.items.DialogMessage
import com.example.sharedui.uiElement.components.items.MessagesDialogSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction1

@Composable
internal fun OnlineBookingScreen(
    viewModel: OnlineBookingViewModel = hiltViewModel(),
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val doctorIsNotOnline =
        stringResource(R.string.the_doctor_is_offline_now)

    val alreadyHaveBooking =
        stringResource(R.string.you_already_have_a_booking_with)

    OnlineBookingContent(
        onClickOnBackButton = popBookingNavGraph,
        uiState = state.value,
        onClickOnBookingButton = viewModel::onBookOnlineAppointment,
        onClickOnDoneButton = navigateToBookingDetailsDestination,
        snackbarHostState = snackbarHostState,
        onRateOnlineDoctor = viewModel::onRateOfflineDoctor
    )

    LaunchedEffect(
        key1 = state.value.onlineDoctorDetailsStatus.doctorDeleted
    ) {

        if (state.value.onlineDoctorDetailsStatus.doctorDeleted) {
            popBookingNavGraph()
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.bookOnlineAppointmentStatus.internetError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = internetError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.bookOnlineAppointmentStatus.serverError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = serverError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.bookOnlineAppointmentStatus.alreadyHaveBooking
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = alreadyHaveBooking + " "
                        + (state.value.onlineDoctorDetailsStatus.data?.name ?: "")
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.bookOnlineAppointmentStatus.doctorNotOnline
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = doctorIsNotOnline
            )
        }//end if

    }//end LaunchedEffect


}//end OnlineBookingScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun OnlineBookingContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    uiState: OnlineBookingUiState,
    onClickOnBookingButton: () -> Unit,
    onClickOnDoneButton: (Int) -> Unit,
    snackbarHostState: SnackbarHostState,
    onRateOnlineDoctor: KFunction1<Int, Unit>
) {

    //create base screen for define navigation and status bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.transparent
    ) {

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }//end snack bar Host
        ) {

            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
            ) {
                //create ids for screen components here
                val (
                    doctorProfileId, successDialogId,
                    infoDoctorId, bookAppointmentId, backButtonId
                ) = createRefs()

                //create guides here
                val guideLineFromTop58P = createGuidelineFromTop(0.5725f)
                val guideLineFromTop60P = createGuidelineFromTop(0.6075f)

                AnimatedVisibility(
                    visible = uiState.bookOnlineAppointmentStatus.success,
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

                    //create booking successfully dialog here
                    MessagesDialogSection(
                        dimen = dimen,
                        theme = theme,
                        logo = painterResource(
                            id = R.drawable.success
                        ),
                        logoTint = theme.greenLight,
                        title = stringResource(
                            id = R.string.booking_successful
                        ),
                        buttonTitle = stringResource(
                            id = R.string.done
                        ),
                        titlePaddingTop = dimen.dimen_2_5,
                        logoPaddingTop = dimen.dimen_6,
                        messagesPaddingTop = dimen.dimen_2_5,
                        buttonPaddingTop = dimen.dimen_2_5,
                        buttonTitleSize = dimen.dimen_2_25,
                        buttonTitleColor = theme.background,
                        buttonBackground = theme.redDark,
                        onClickOnButton = { onClickOnDoneButton(0) },
                        messages = arrayOf(
                            DialogMessage(
                                message = stringResource(
                                    id = R.string.online_message_during_first_step_in_booking
                                ),
                                color = theme.hintIconBottom,
                                size = (dimen.dimen_1_75 + dimen.dimen_0_125),
                                paddingTop = dimen.dimen_0,
                                paddingHorizontal = dimen.dimen_3_25,
                            )
                        ),
                        horizontalMargin = dimen.dimen_1_5,
                        paddingBottom = dimen.dimen_6,
                        modifier = Modifier
                            .constrainAs(successDialogId) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                    )

                }//end AnimatedVisibility

                if (uiState.onlineDoctorDetailsStatus.loading) {

                    CircularProgressIndicator(
                        color = theme.grayLight,
                        trackColor = theme.background,
                        strokeWidth = dimen.dimen_0_5.dp,
                        modifier = Modifier
                            .constrainAs(createRef()) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                top.linkTo(parent.top)
                            }
                            .size(
                                size = dimen.dimen_3.dp
                            )
                    )

                }//end if
                else {

                    //create doctor profile here
                    ServerLoadImageView(
                        theme = theme,
                        dimen = dimen,
                        imageUrl = uiState.onlineDoctorDetailsStatus.data?.image ?: "",
                        modifier = Modifier
                            .constrainAs(doctorProfileId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(guideLineFromTop60P)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            }
                    )

                    //create book appointment button here
                    BasicButtonView(
                        dimen = dimen,
                        theme = theme,
                        text = stringResource(
                            id = R.string.book_appointment
                        ),
                        onClick = if (!uiState.bookOnlineAppointmentStatus.loading) {
                            onClickOnBookingButton
                        } else {
                            {}
                        },
                        load = uiState.bookOnlineAppointmentStatus.loading,
                        modifier = Modifier
                            .constrainAs(bookAppointmentId) {
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_2.dp
                                )
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_2.dp
                                )
                                bottom.linkTo(
                                    parent.bottom,
                                    dimen.dimen_2.dp
                                )
                                width = Dimension.fillToConstraints
                            }
                    )

                    //create info box here
                    LazyColumn(
                        modifier = Modifier
                            .constrainAs(infoDoctorId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(guideLineFromTop58P)
                                bottom.linkTo(bookAppointmentId.top)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            }
                            .clip(
                                shape = RoundedCornerShape(
                                    topStart = dimen.dimen_3_75.dp,
                                    topEnd = dimen.dimen_3_75.dp
                                )
                            )
                            .drawBehind {
                                drawRoundRect(
                                    color = theme.blackTR25
                                )
                            }
                            .background(
                                color = theme.background
                            ),
                        contentPadding = PaddingValues(
                            top = dimen.dimen_3_5.dp,
                            start = dimen.dimen_2.dp,
                            end = dimen.dimen_3_75.dp,
                            bottom = dimen.dimen_2.dp
                        )
                    ) {

                        //create item contain on doctor info here
                        item(
                            key = 1
                        ) {

                            //create doctor info here
                            BookedDoctorInformationSection(
                                dimen = dimen,
                                theme = theme,
                                doctorName = uiState.onlineDoctorDetailsStatus.data?.name ?: "",
                                jop = uiState.onlineDoctorDetailsStatus.data?.specialization ?: "",
                                price = uiState.onlineDoctorDetailsStatus.data?.price ?: "",
                                currency = stringResource(
                                    id = R.string.egp
                                ),
                                address = uiState.onlineDoctorDetailsStatus.data?.clinicLocation
                                    ?: "",
                                interactionRatingValue = (uiState.onlineDoctorDetailsStatus.data?.userRating
                                    ?: 0).toInt(),
                                interactionRatingOnChanged = onRateOnlineDoctor,
                                interactionRatingTitle = stringResource(
                                    id = R.string.rate
                                ),
                                doctorIsOnline = uiState.onlineDoctorDetailsStatus.data?.active
                                    ?: false,
                                phoneNumber = uiState.onlineDoctorDetailsStatus.data?.phone ?: "",
                                aboutContent = uiState.onlineDoctorDetailsStatus.data?.bio ?: "",
                                rating = uiState.onlineDoctorDetailsStatus.data?.rating ?: 0f,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        }//end item

                    }//end LazyColumn

                }//end else

                //create back button here
                IconButtonView(
                    dimen = dimen,
                    theme = theme,
                    onClick = onClickOnBackButton,
                    modifier = Modifier
                        .constrainAs(backButtonId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                parent.top,
                                (dimen.dimen_7_5 - dimen.dimen_0_125).dp
                            )
                        }
                )

            }//end Scaffold

        }//end ConstraintLayout

    }//end BaseScreen

}//end OnlineBookingContent