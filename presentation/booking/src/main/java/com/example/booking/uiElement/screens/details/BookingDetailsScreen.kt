@file:OptIn(ExperimentalFoundationApi::class)

package com.example.booking.uiElement.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booking.uiElement.components.items.TabsSection
import com.example.booking.uiElement.screens.details.childs.OfflineDetailsScreen
import com.example.booking.uiElement.screens.details.childs.OnlineDetailsScreen
import com.example.booking.uiState.viewModel.details.BookingDetailsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction0

@Composable
internal fun BookingDetailsScreen(
    viewModel: BookingDetailsViewModel = hiltViewModel(),
    popBookingDetailsDestination: KFunction0<Unit>,
    navigateToChatNavGraph: () -> Unit
) {
    //collect state here
    val state = viewModel.state.collectAsState()
    val uiState = state.value

    //create pager state here
    val pagerState = rememberPagerState(
        initialPage = uiState.bookingDetailsPage
    )

    //create coroutine scope
    val coroutineScope = rememberCoroutineScope()

    BookingDetailsContent(
        pagerState = pagerState,
        onClickOnBackButton = popBookingDetailsDestination,
        navigateToChatNavGraph = navigateToChatNavGraph,
        onClickOnBookingsOnline = {

            coroutineScope.launch {
                //if not exist in doctors online page scroll to it
                if (pagerState.currentPage != 0) {

                    //execute scroll here
                    pagerState.animateScrollToPage(
                        page = 0
                    )

                }//end if

            }//end launch
        },
        onClickOnBookingsOffline = {

            coroutineScope.launch {
                //if not exist in doctors offline page scroll to it
                if (pagerState.currentPage != 1) {

                    //execute scroll here
                    pagerState.animateScrollToPage(
                        page = 1
                    )

                }//end if

            }//end launch
        }
    )

}//end BookingDetailsScreen

@Composable
private fun BookingDetailsContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    pagerState: PagerState,
    onClickOnBookingsOnline: () -> Unit,
    onClickOnBookingsOffline: () -> Unit,
    onClickOnBackButton: KFunction0<Unit>,
    navigateToChatNavGraph: () -> Unit
) {

    //create base screen to define navigation and status color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        //create container here
        ConstraintLayout(
            modifier = Modifier
                .appDefaultContainer(
                    color = theme.background
                )
        ) {
            //create ids for screen components here
            val (headerId, tabsId, pagerId) = createRefs()

            //create header here
            HeaderSection(
                dimen = dimen,
                theme = theme,
                onClickOnBackButton = onClickOnBackButton,
                title = stringResource(
                    id = R.string.details_of_booking_doctor
                ),
                modifier = Modifier
                    .constrainAs(headerId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(parent.end)
                        top.linkTo(
                            parent.top,
                            (dimen.dimen_3_5 + dimen.dimen_0_125).dp
                        )

                        width = Dimension.fillToConstraints
                    }
            )

            //create pager tabs here
            TabsSection(
                theme = theme,
                dimen = dimen,
                titles = arrayOf(
                    stringResource(
                        id = R.string.doctors_online
                    ),
                    stringResource(
                        id = R.string.doctors_offline
                    )
                ),
                selectedItem = pagerState.currentPage,
                onClickOnTab = arrayOf(onClickOnBookingsOnline, onClickOnBookingsOffline),
                modifier = Modifier
                    .constrainAs(tabsId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            headerId.bottom,
                            dimen.dimen_3.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            //create pager contain on online and offline bookings here
            HorizontalPager(
                pageCount = 2,
                state = pagerState,
                modifier = Modifier
                    .constrainAs(pagerId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(tabsId.bottom)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            ) { page ->

                //if page equal 0 create online screen else create offline screen
                when (page) {
                    0 -> {

                        //create online screen here
                        OnlineDetailsScreen(
                            dimen = dimen,
                            theme = theme
                        )
                    }//end online case

                    1 -> {

                        //create offline screen here
                        OfflineDetailsScreen(
                            dimen = dimen,
                            theme = theme,
                            navigateToChatNavGraph = navigateToChatNavGraph
                        )
                    }//end offline case

                }//end when

            }//end HorizontalPager

        }//end ConstraintLayout

    }//end BaseScreen

}//end BookingDetailsContent