package com.example.offlinebooking.presentation.uiElement.screens.booking

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
import androidx.compose.runtime.State
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
import com.example.offlinebooking.presentation.uiElement.components.items.BookingDatesSection
import com.example.offlinebooking.presentation.uiElement.components.items.BookingTimesSection
import com.example.offlinebooking.presentation.uiState.state.OfflineBookingUiState
import com.example.offlinebooking.presentation.uiState.viewModel.OfflineBookingViewModel
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
import com.google.accompanist.placeholder.placeholder
import kotlin.reflect.KFunction1

@Composable
fun OfflineBookingScreen(
    viewModel: OfflineBookingViewModel = hiltViewModel(),
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

    val appointmentNotValid =
        stringResource(R.string.this_appointment_is_not_available_and_has_already_been_booked)

    val appointmentDataNotSelected =
        stringResource(R.string.you_must_chose_a_booking_date)

    OfflineBookingContent(
        onClickOnBackButton = popBookingNavGraph,
        uiState = state.value,
        onClickOnDoneButton = navigateToBookingDetailsDestination,
        dateIdSelectedState = state.value.dateIdSelected.collectAsState(),
        onClickOnDate = viewModel::onChangeDateId,
        onClickOnTime = viewModel::onChangeTimeId,
        snackbarHostState = snackbarHostState,
        onClickOnBookingButton = viewModel::onBookOfflineAppointment,
        onRateDoctor = viewModel::onRateOfflineDoctor
    )

    LaunchedEffect(
        key1 = state.value.bookOfflineAppointmentStatus.internetError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = internetError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.bookOfflineAppointmentStatus.serverError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = serverError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.bookOfflineAppointmentStatus.appointmentNotValid
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = appointmentNotValid
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.bookOfflineAppointmentStatus.appointNotSelected
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = appointmentDataNotSelected
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.offlineDoctorDetailsStatus.doctorDeleted
    ) {

        if (state.value.offlineDoctorDetailsStatus.doctorDeleted) {
            popBookingNavGraph()
        }//end if

    }//end LaunchedEffect

}//end OnlineBookingScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun OfflineBookingContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    uiState: OfflineBookingUiState,
    onClickOnBookingButton: () -> Unit,
    onClickOnDoneButton: (Int) -> Unit,
    dateIdSelectedState: State<Long>,
    onClickOnDate: KFunction1<Long, Unit>,
    onClickOnTime: KFunction1<Long, Unit>,
    snackbarHostState: SnackbarHostState,
    onRateDoctor: KFunction1<Int, Unit>
) {


    //create base screen to define status and navigation bar color
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
                val (doctorProfileId, successDialogId, infoDoctorId,
                    bookAppointmentId, backButtonId) = createRefs()

                //create guides here
                val guideLineFromTop44P = createGuidelineFromTop(0.44875f)
                val guideLineFromTop48P = createGuidelineFromTop(0.48375f)

                AnimatedVisibility(
                    visible = uiState.bookOfflineAppointmentStatus.success,
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
                        buttonTitleSize = dimen.dimen_2_25,
                        buttonTitleColor = theme.background,
                        buttonBackground = theme.redDark,
                        onClickOnButton = { onClickOnDoneButton(1) },
                        messages = arrayOf(
                            DialogMessage(
                                message = stringResource(
                                    id = R.string.your_appointment_booking_completed
                                ),
                                color = theme.hintIconBottom,
                                size = (dimen.dimen_1_75 + dimen.dimen_0_125),
                                paddingTop = dimen.dimen_0,
                                paddingHorizontal = dimen.dimen_4,
                            ),
                            DialogMessage(
                                message = "Dr.Ahmed ${
                                    stringResource(
                                        id = R.string.will_message_you_soon
                                    )
                                }",
                                color = theme.hintIconBottom,
                                size = (dimen.dimen_1_75 + dimen.dimen_0_125),
                                paddingTop = dimen.dimen_2_25,
                                paddingHorizontal = dimen.dimen_6,
                            )
                        ),
                        horizontalMargin = dimen.dimen_1_5,
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


                if (uiState.offlineDoctorDetailsStatus.loading) {

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

                }//end else

                else {

                    //create doctor profile here
                    ServerLoadImageView(
                        theme = theme,
                        dimen = dimen,
                        imageUrl = uiState.offlineDoctorDetailsStatus.data?.image ?: "",
                        modifier = Modifier
                            .constrainAs(doctorProfileId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(guideLineFromTop48P)
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
                        onClick = if (!uiState.bookOfflineAppointmentStatus.loading) {
                            onClickOnBookingButton
                        } else {
                            {}
                        },
                        load = uiState.bookOfflineAppointmentStatus.loading,
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
                                top.linkTo(guideLineFromTop44P)
                                bottom.linkTo(bookAppointmentId.top)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            }
                            .clip(
                                shape = RoundedCornerShape(
                                    topStart = dimen.dimen_2_25.dp,
                                    topEnd = dimen.dimen_2_25.dp
                                )
                            )
                            .drawBehind {
                                drawRoundRect(
                                    color = theme.blackTR25
                                )
                            }
                            .background(
                                color = theme.background
                            )
                            .placeholder(
                                visible = uiState.offlineDoctorDetailsStatus.loading,
                                color = theme.background
                            ),
                        contentPadding = PaddingValues(
                            top = dimen.dimen_3_5.dp,
                            bottom = dimen.dimen_2.dp
                        )
                    ) {

                        //create item contain on doctor info here
                        item(
                            key = 1
                        ) {

                            //create container here
                            ConstraintLayout(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                //create ids for components here
                                val (doctorInfoId, dateId, timeId) = createRefs()

                                //create doctor info here
                                BookedDoctorInformationSection(
                                    dimen = dimen,
                                    theme = theme,
                                    doctorName = uiState.offlineDoctorDetailsStatus.data?.name
                                        ?: "",
                                    jop = uiState.offlineDoctorDetailsStatus.data?.specialization
                                        ?: "",
                                    price = uiState.offlineDoctorDetailsStatus.data?.price ?: "",
                                    currency = stringResource(R.string.egp),
                                    address = uiState.offlineDoctorDetailsStatus.data?.clinicLocation
                                        ?: "",
                                    interactionRatingValue = (uiState.offlineDoctorDetailsStatus.data?.userRating
                                        ?: 0).toInt(),
                                    interactionRatingOnChanged = onRateDoctor,
                                    rating = uiState.offlineDoctorDetailsStatus.data?.rating ?: 0f,
                                    interactionRatingTitle = stringResource(
                                        id = R.string.rate
                                    ),
                                    doctorIsOnline = false,
                                    phoneNumber = uiState.offlineDoctorDetailsStatus.data?.phone
                                        ?: "",
                                    aboutContent = uiState.offlineDoctorDetailsStatus.data?.bio
                                        ?: "",
                                    modifier = Modifier
                                        .constrainAs(doctorInfoId) {
                                            start.linkTo(
                                                parent.start,
                                                dimen.dimen_2.dp
                                            )
                                            end.linkTo(
                                                parent.end,
                                                dimen.dimen_3_75.dp
                                            )
                                            top.linkTo(parent.top)
                                            width = Dimension.fillToConstraints
                                        }
                                )

                                //create date section here
                                BookingDatesSection(
                                    dimen = dimen,
                                    theme = theme,
                                    dateSelected = dateIdSelectedState.value,
                                    title = stringResource(
                                        R.string.select_date
                                    ),
                                    onClickOnDate = if (!uiState.bookOfflineAppointmentStatus.loading) {
                                        onClickOnDate
                                    } else {
                                        {}
                                    },
                                    dates = uiState.offlineDoctorDetailsStatus.data?.dates
                                        ?: emptyList(),
                                    modifier = Modifier
                                        .constrainAs(dateId) {
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                            top.linkTo(
                                                doctorInfoId.bottom,
                                                dimen.dimen_1_5.dp
                                            )
                                            width = Dimension.fillToConstraints
                                        }
                                )

                                //create time section here
                                BookingTimesSection(
                                    dimen = dimen,
                                    theme = theme,
                                    title = stringResource(
                                        id = R.string.select_a_time
                                    ),
                                    timesStatus = uiState.dateTimeStatus,
                                    timeSelectedId = uiState.timeIdSelected,
                                    onClickOnTime = if (!uiState.bookOfflineAppointmentStatus.loading) {
                                        onClickOnTime
                                    } else {
                                        {}
                                    },
                                    modifier = Modifier
                                        .constrainAs(timeId) {
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                            top.linkTo(
                                                dateId.bottom,
                                                dimen.dimen_1_5.dp
                                            )
                                            width = Dimension.fillToConstraints
                                        }
                                )

                            }//end ConstraintLayout

                        }//end item

                    }//end LazyColumn

                }//end if

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


            }//end ConstraintLayout

        }//end scaffold

    }//end BaseScreen

}//end OnlineBookingContent