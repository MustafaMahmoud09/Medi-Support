package com.example.booking.uiElement.screens.booking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booking.uiElement.screens.booking.child.OfflineBookingScreen
import com.example.booking.uiElement.screens.booking.child.OnlineBookingScreen
import com.example.booking.uiState.state.bookings.BookingUiState
import com.example.booking.uiState.viewModel.bookings.BookingViewModel
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun BookingScreen(
    viewModel: BookingViewModel = hiltViewModel(),
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {
    //collect state screen here
    val state = viewModel.state.collectAsState()

    BookingContent(
        navigateToBookingDetailsDestination = navigateToBookingDetailsDestination,
        popBookingNavGraph = popBookingNavGraph,
        uiState = state.value
    )

}//end BookingScreen

@Composable
private fun BookingContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    popBookingNavGraph: () -> Unit,
    uiState: BookingUiState,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    //create base screen to define navigation bar and status bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.transparent
    ) {

        //if booking type equal true show online booking screen
        if (uiState.bookingType) {

            //create online booking screen here
            OnlineBookingScreen(
                dimen = dimen,
                theme = theme,
                popBookingNavGraph = popBookingNavGraph,
                navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
            )

        }//end if

        //else show offline booking screen
        else {

            //create offline booking screen here
            OfflineBookingScreen(
                dimen = dimen,
                theme = theme,
                popBookingNavGraph = popBookingNavGraph,
                navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
            )

        }//end else


    }//en BaseScreen

}//end BookingContent