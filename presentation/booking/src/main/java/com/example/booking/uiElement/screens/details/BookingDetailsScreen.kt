@file:OptIn(ExperimentalFoundationApi::class)

package com.example.booking.uiElement.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharedui.uiElement.containers.pager.data.TabData
import com.example.booking.uiElement.components.items.TabsSection
import com.example.booking.uiElement.screens.details.childs.OfflineDetailsScreen
import com.example.booking.uiElement.screens.details.childs.OnlineDetailsScreen
import com.example.booking.uiState.state.details.BookingDetailsUiState
import com.example.booking.uiState.viewModel.details.BookingDetailsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.containers.pager.animateScrollToPage
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@Composable
internal fun BookingDetailsScreen(
    viewModel: BookingDetailsViewModel = hiltViewModel(),
    popBookingDetailsDestination: KFunction0<Unit>,
    navigateToChatNavGraph: () -> Unit,
    navigateToOnlineRoomNavGraph: () -> Unit
) {
    //collect state here
    val state = viewModel.state.collectAsState()
    val uiState = state.value

    //create pager state here
    val pagerState = rememberPagerState(
        initialPage = uiState.currentBookingDetailsPage
    )

    //create coroutine scope
    val coroutineScope = rememberCoroutineScope()

    BookingDetailsContent(
        pagerState = pagerState,
        onClickOnBackButton = popBookingDetailsDestination,
        navigateToChatNavGraph = navigateToChatNavGraph,
        uiState = uiState,
        onCurrentBookingDetailsPageChanged = viewModel::onCurrentBookingDetailsPageChanged,
        navigateToOnlineRoomNavGraph = navigateToOnlineRoomNavGraph,
        bookingOnlineTabData = TabData(
            title = stringResource(
                id = R.string.doctors_online
            ),
            onClick = {

                //make scroll here
                pagerState.animateScrollToPage(
                    coroutineScope = coroutineScope,
                    page = 0
                )

                //change current booking details page here
                viewModel.onCurrentBookingDetailsPageChanged(
                    newPage = 0
                )

            }//end onClick
        ),
        bookingOfflineTabData = TabData(
            title = stringResource(
                id = R.string.doctors_offline
            ),
            onClick = {

                //make scroll here
                pagerState.animateScrollToPage(
                    coroutineScope = coroutineScope,
                    page = 1
                )

                //change current booking details page here
                viewModel.onCurrentBookingDetailsPageChanged(
                    newPage = 1
                )

            }//end onClick
        ),
    )

}//end BookingDetailsScreen

@Composable
private fun BookingDetailsContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    pagerState: PagerState,
    onClickOnBackButton: KFunction0<Unit>,
    navigateToChatNavGraph: () -> Unit,
    bookingOfflineTabData: TabData,
    bookingOnlineTabData: TabData,
    uiState: BookingDetailsUiState,
    onCurrentBookingDetailsPageChanged: KFunction1<Int, Unit>,
    navigateToOnlineRoomNavGraph: () -> Unit,
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
                currentItem = uiState.currentBookingDetailsPage,
                tabs = arrayOf(bookingOnlineTabData, bookingOfflineTabData),
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
                            dimen.dimen_2.dp
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
                            theme = theme,
                            navigateToOnlineRoomNavGraph = navigateToOnlineRoomNavGraph
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

    //on pager state changed
    LaunchedEffect(
        key1 = pagerState.currentPage
    ) {

        //change current booking details page here
        onCurrentBookingDetailsPageChanged(pagerState.currentPage)

    }//end LaunchedEffect

}//end BookingDetailsContent