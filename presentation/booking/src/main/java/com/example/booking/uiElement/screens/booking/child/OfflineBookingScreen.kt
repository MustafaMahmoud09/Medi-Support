package com.example.booking.uiElement.screens.booking.child


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
import com.example.booking.uiElement.components.items.BookingDateSection
import com.example.booking.uiElement.components.items.BookingDoctorInfoSection
import com.example.booking.uiElement.components.items.BookingTimeSection
import com.example.booking.uiState.viewModel.bookings.OfflineBookingViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.LoadImageView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun OfflineBookingScreen(
    viewModel: OfflineBookingViewModel = hiltViewModel(),
    dimen: CustomDimen,
    theme: CustomTheme,
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    OfflineBookingContent(
        dimen = dimen,
        theme = theme,
        onClickOnBackButton = popBookingNavGraph,
        uiState = state.value,
        onClickOnBookingButton = navigateToBookingDetailsDestination
    )

}//end OnlineBookingScreen

@Composable
private fun OfflineBookingContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnBackButton: () -> Unit,
    uiState: Int,
    onClickOnBookingButton: (Int) -> Unit
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
        val (doctorProfileId, infoDoctorId, bookAppointmentId, backButtonId) = createRefs()

        //create guides here
        val guideLineFromTop44P = createGuidelineFromTop(0.44875f)
        val guideLineFromTop48P = createGuidelineFromTop(0.48375f)

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
            onClick = { onClickOnBookingButton(1) },
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
                    BookingDoctorInfoSection(
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
                    BookingDateSection(
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
                    BookingTimeSection(
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

}//end OnlineBookingContent