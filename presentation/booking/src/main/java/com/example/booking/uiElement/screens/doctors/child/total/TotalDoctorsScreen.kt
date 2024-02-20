@file:OptIn(ExperimentalFoundationApi::class)

package com.example.booking.uiElement.screens.doctors.child.total

import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.booking.uiElement.components.data.TabData
import com.example.booking.uiElement.components.items.TabsSection
import com.example.booking.uiElement.screens.doctors.child.total.child.TotalOfflineDoctorsScreen
import com.example.booking.uiElement.screens.doctors.child.total.child.TotalOnlineDoctorsScreen
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlinx.coroutines.launch

//function for collect state and execute action from view model
@Composable
internal fun TotalDoctorsScreen(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    //call see all doctor content function
    TotalDoctorsContent(
        dimen = dimen,
        theme = theme,
        navigateToBookingNavGraph = navigateToBookingNavGraph,
        pagerState = pagerState,
        doctorsOnlineTabData = TabData(
            title = stringResource(
                id = R.string.doctors_online
            ),
            onClick = {

                coroutineScope.launch {
                    //if not exist in doctors online page scroll to it
                    if (pagerState.currentPage != 0) {

                        //execute scroll here
                        pagerState.animateScrollToPage(
                            page = 0,
                            animationSpec = tween(
                                durationMillis = 200
                            )
                        )

                    }//end if

                }//end launch
            }
        ),
        doctorsOfflineTabData = TabData(
            title = stringResource(
                id = R.string.doctors_offline
            ),
            onClick = {

                coroutineScope.launch {
                    //if not exist in doctors online page scroll to it
                    if (pagerState.currentPage != 1) {

                        //execute scroll here
                        pagerState.animateScrollToPage(
                            page = 1,
                            animationSpec = tween(
                                durationMillis = 200
                            )
                        )

                    }//end if

                }//end launch
            }
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
    navigateToBookingNavGraph: (Boolean, Int) -> Unit,
    doctorsOnlineTabData: TabData,
    doctorsOfflineTabData: TabData
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
                    currentItem = pagerState.currentPage,
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
                            navigateToBookingNavGraph = navigateToBookingNavGraph
                        )

                    }//end online doctors case

                    //create doctors offline page here
                    1 -> {

                        //create total offline doctors screen
                        TotalOfflineDoctorsScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToBookingNavGraph = navigateToBookingNavGraph
                        )

                    }//end offline doctors case

                }//end when

            }//end HorizontalPager

        }//end item

    }//end LazyColumn

}//end SeeAllDoctorContent