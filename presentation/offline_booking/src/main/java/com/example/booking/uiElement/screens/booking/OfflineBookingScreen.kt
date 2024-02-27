package com.example.booking.uiElement.screens.booking

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booking.uiElement.components.items.BookingDatesSection
import com.example.sharedui.uiElement.components.items.BookedDoctorInformationSection
import com.example.booking.uiElement.components.items.BookingTimesSection
import com.example.booking.uiState.state.OfflineBookingUiState
import com.example.booking.uiState.viewModel.OfflineBookingViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.LoadImageView
import com.example.sharedui.uiElement.components.items.DialogMessage
import com.example.sharedui.uiElement.components.items.MessagesDialogSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
fun OfflineBookingScreen(
    viewModel: OfflineBookingViewModel = hiltViewModel(),
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    OfflineBookingContent(
        onClickOnBackButton = popBookingNavGraph,
        uiState = state.value,
        onClickOnDoneButton = navigateToBookingDetailsDestination,
        onClickOnBookingButton = viewModel::onShowBookingSuccessfullyDialog
    )

}//end OnlineBookingScreen

@Composable
private fun OfflineBookingContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    uiState: OfflineBookingUiState,
    onClickOnBookingButton: () -> Unit,
    onClickOnDoneButton: (Int) -> Unit
) {


    //create base screen to define status and navigation bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.transparent
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
                visible = uiState.bookingSuccessfullyDialogIsVisible,
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

            //create doctor profile here
            LoadImageView(
                painter = painterResource(
                    id = R.drawable.doctor_test_2,
                ),
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
                            dimen.dimen_8.dp
                        )
                    }
            )

            //create book appointment button here
            BasicButtonView(
                dimen = dimen,
                theme = theme,
                text = stringResource(
                    id = R.string.book_appointment
                ),
                onClick = onClickOnBookingButton,
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
                            doctorName = "Dr: Ahmed Mohamed",
                            jop = "Dentist",
                            price = "250",
                            currency = "EGP",
                            address = "Cairo",
                            interactionRatingValue = 3,
                            interactionRatingOnChanged = {},
                            interactionRatingTitle = stringResource(
                                id = R.string.rate
                            ),
                            doctorIsOnline = false,
                            phoneNumber = "01001156618",
                            aboutContent = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.  Lorem Ipsum has been the Lorem Ipsum is simply dummy text of the printing and ",
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
                            title = stringResource(
                                R.string.select_date
                            ),
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

        }//end ConstraintLayout

    }//end BaseScreen

}//end OnlineBookingContent