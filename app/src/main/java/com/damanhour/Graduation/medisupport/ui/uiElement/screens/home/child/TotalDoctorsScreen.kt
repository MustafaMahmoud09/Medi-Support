@file:OptIn(ExperimentalFoundationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.screens.home.child

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharedui.uiElement.navigation.data.TabData
import com.damanhour.Graduation.medisupport.ui.uiElement.components.items.TabsSection
import com.example.booking.uiElement.screens.TotalOfflineDoctorsScreen
import com.example.online_booking.uiElement.screens.TotalOnlineDoctorsScreen
import com.damanhour.Graduation.medisupport.ui.uiState.state.TotalDoctorsUiState
import com.damanhour.Graduation.medisupport.ui.uiState.viewModel.TotalDoctorsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.navigation.transitions.animateScrollToPage
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlin.reflect.KFunction1

//function for collect state and execute action from view model
@Composable
internal fun TotalDoctorsScreen(
    viewModel: TotalDoctorsViewModel = hiltViewModel(),
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToOnlineBookingNavGraph: (Int) -> Unit,
    navigateToOfflineBookingDestination: (Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    val state = viewModel.state.collectAsState()

    //call see all doctor content function
    TotalDoctorsContent(
        dimen = dimen,
        theme = theme,
        navigateToOnlineBookingNavGraph = navigateToOnlineBookingNavGraph,
        pagerState = pagerState,
        uiState = state.value,
        onCurrentDoctorsPageChanged = viewModel::onCurrentDoctorsPageChanged,
        navigateToOfflineBookingDestination = navigateToOfflineBookingDestination,
        doctorsOnlineTabData = TabData(
            title = stringResource(
                id = R.string.doctors_online
            ),
            onClick = {

                //make scroll here
                pagerState.animateScrollToPage(
                    coroutineScope = coroutineScope,
                    page = 0
                )

                //change current doctors page here
                viewModel.onCurrentDoctorsPageChanged(
                    newPage = 0
                )

            }//end onClick
        ),
        doctorsOfflineTabData = TabData(
            title = stringResource(
                id = R.string.doctors_offline
            ),
            onClick = {

                //make scroll here
                pagerState.animateScrollToPage(
                    coroutineScope = coroutineScope,
                    page = 1
                )

                //change current doctors page here
                viewModel.onCurrentDoctorsPageChanged(
                    newPage = 1
                )

            }//end onClick
        )
    )
}//end SeeAllDoctorScreen

//function for observe state and draw components
@Composable
private fun TotalDoctorsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    pagerState: PagerState,
    screenHeight: Int = LocalConfiguration.current.screenHeightDp,
    navigateToOnlineBookingNavGraph: (Int) -> Unit,
    doctorsOnlineTabData: TabData,
    doctorsOfflineTabData: TabData,
    uiState: TotalDoctorsUiState,
    onCurrentDoctorsPageChanged: KFunction1<Int, Unit>,
    navigateToOfflineBookingDestination: (Int) -> Unit
) {
    //create lazy column here
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
            .padding(
                top = dimen.dimen_1.dp
            ),
        contentPadding = PaddingValues(
            top = dimen.dimen_1.dp
        )
    ) {
        //create all doctors title item here
        stickyHeader(
            key = 1
        ) {
            //create all doctors title here
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimen.dimen_2.dp
                    )
            ) {

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        id = R.string.all_doctors
                    ),
                    size = dimen.dimen_2_25,
                    color = theme.black
                )

            }//end Box

        }//end item

        //item for create tabs item
        stickyHeader(
            key = 2
        ) {

            //create tabs items here
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = theme.background
                    )
                    .padding(
                        top = dimen.dimen_0_5.dp,
                        start = dimen.dimen_2.dp,
                        end = dimen.dimen_2.dp
                    ),
            ) {

                TabsSection(
                    theme = theme,
                    dimen = dimen,
                    tabs = arrayOf(doctorsOnlineTabData, doctorsOfflineTabData),
                    currentItem = uiState.currentDoctorsPage,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end Box

        }//end item

        //create doctors pager item here
        item(
            key = 3
        ) {

            //create doctors pager here
            HorizontalPager(
                pageCount = 2,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .heightIn(
                        min = dimen.dimen_0.dp,
                        max = screenHeight.dp
                    )
            ) { page ->

                //if page is 0 create doctors online page else create doctor offline page
                when (page) {

                    //create doctors online page here
                    0 -> {

                        //create total online doctors screen
                        TotalOnlineDoctorsScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToBookingNavGraph = navigateToOnlineBookingNavGraph,
                        )

                    }//end online doctors case

                    //create doctors offline page here
                    1 -> {

                        //create total offline doctors screen
                        TotalOfflineDoctorsScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToBookingNavGraph = navigateToOfflineBookingDestination
                        )

                    }//end offline doctors case

                }//end when

            }//end HorizontalPager

        }//end item

    }//end LazyColumn

    //on pager state changed
    LaunchedEffect(
        key1 = pagerState.currentPage
    ) {

        //change current doctors page here
        onCurrentDoctorsPageChanged(pagerState.currentPage)

    }//end LaunchedEffect

}//end SeeAllDoctorContent