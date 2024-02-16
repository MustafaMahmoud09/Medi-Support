@file:OptIn(ExperimentalFoundationApi::class)

package com.example.booking.uiElement.screens.doctors

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booking.uiElement.components.items.SearchSection
import com.example.booking.uiElement.screens.doctors.child.TopDoctorsScreen
import com.example.booking.uiElement.screens.doctors.child.SearchScreen
import com.example.booking.uiElement.screens.doctors.child.TotalDoctorsScreen
import com.example.booking.uiState.state.doctors.HomeUiState
import com.example.booking.uiState.viewModel.doctors.HomeViewModel
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.sharedui.R

//function for collect state and execute action from view model
@Composable
internal fun DoctorsScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToAddReminderDestination: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit
) {

    //collect state here
    val state = viewModel.state.collectAsState()

    //ui state
    val uiState = state.value

    val pagerState = rememberPagerState(initialPage = 0)

    //coroutine scope to call call suspend function
    val coroutineScope = rememberCoroutineScope()

    val focusRequester = remember { FocusRequester() }

    DoctorsContent(
        pagerState = pagerState,
        onClickOnReminder = navigateToAddReminderDestination,
        navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
        navigateToBmiNavGraph = navigateToBmiNavGraph,
        navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
        navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
        focusRequester = focusRequester,
        coroutineScope = coroutineScope,
        onClickOnSearchField = viewModel::onFocusOnSearchField,
        navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
        uiState = uiState,
        navigateToBookingNavGraph = navigateToBookingNavGraph,
        onSearchKeyChanged = viewModel::onSearchKeyChanged,
        onClickSeeAll = {

            //change current page and prev page
            viewModel.onScrollToTotalDoctorsPage()

            //execute scroll to see all doctor screen
            coroutineScope.launch {
                pagerState.scrollToPage(
                    page = 2
                )
            }//end launch

        },//end onClickSeeAll
        onClickBack = {

            coroutineScope.launch {

                //get prev page here
                val prevPage = uiState.prevPage

                //change current page and prev page
                viewModel.onBack()

                //on back scroll to prev page
                pagerState.scrollToPage(
                    page = prevPage
                )

            }//end launch

        },//end onClickBack
    )

}//end DoctorSearchScreen

//function for observe state and draw components
@Composable
private fun DoctorsContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    pagerState: PagerState,
    onClickSeeAll: () -> Unit,
    onClickBack: () -> Unit,
    focusRequester: FocusRequester,
    navigateToHeartPredictionNavGraph: () -> Unit,
    onClickOnSearchField: () -> Unit,
    coroutineScope: CoroutineScope,
    onClickOnReminder: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    uiState: HomeUiState,
    onSearchKeyChanged: (String) -> Unit,
    headerHeight: Float = dimen.dimen_2_5 + dimen.dimen_5_5,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit,
) {

    //create container here
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        //create ids to screen components
        val (
            buttonBackId, searchId, reminderButtonId,
            searchPagerId, notificationButtonId
        ) = createRefs()

        //create reminder button here
        IconButtonView(
            icon = painterResource(
                id = R.drawable.reminder_icon
            ),
            dimen = dimen,
            theme = theme,
            onClick = onClickOnReminder,
            tint  = theme.hintIconBottom,
            modifier = Modifier
                .constrainAs(reminderButtonId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(searchId.top)
                    bottom.linkTo(searchId.bottom)
                }
        )

        //create notification button here
        IconButtonView(
            icon = painterResource(
                id = R.drawable.notification_icon
            ),
            dimen = dimen,
            theme = theme,
            onClick = {},
            tint = theme.hintIconBottom,
            size =
            /**if exist in page 0**/
            if (uiState.currentPage == 0) {
                dimen.dimen_3_5
            }
            /**if exist in page 1 or 2**/
            else {
                dimen.dimen_0
            },
            modifier = Modifier
                .constrainAs(notificationButtonId) {
                    end.linkTo(
                        reminderButtonId.start,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(searchId.top)
                    bottom.linkTo(searchId.bottom)
                }
        )

        //create search field here
        SearchSection(
            dimen = dimen,
            theme = theme,
            key = uiState.searchKey,
            hint = stringResource(
                R.string.search
            ),
            onChange = onSearchKeyChanged,
            isFocus = uiState.focusOnSearch,
            focusRequester = focusRequester,
            onClick = onClickOnSearchField,
            modifier = Modifier
                .constrainAs(searchId) {
                    start.linkTo(
                        parent.start,
                        //if exist in page 0
                        if (uiState.currentPage == 0) {
                            dimen.dimen_2.dp
                        }//end if
                        //if exist in page 1 or 2
                        else {
                            dimen.dimen_6_25.dp
                        }//end else
                    )
                    end.linkTo(
                        parent.end,
                        //if exist in page 0
                        if (uiState.currentPage == 0) {
                            dimen.dimen_10_75.dp
                        }//end if
                        //if exist in page 1 or 2
                        else {
                            dimen.dimen_6_25.dp
                        }
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create back button here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickBack,
            size =
            /**if exist in page 0**/
            if (uiState.currentPage == 0) {
                dimen.dimen_0
            }
            /**if exist in page 1 or 2**/
            else {
                dimen.dimen_3_5
            },
            modifier = Modifier
                .constrainAs(buttonBackId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(searchId.top)
                    bottom.linkTo(searchId.bottom)
                }
        )


        //create horizontal pager here
        HorizontalPager(
            state = pagerState,
            pageCount = 3,
            userScrollEnabled = false,
            modifier = Modifier
                .constrainAs(searchPagerId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(searchId.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }//end constrainAs
        ) { page ->
            //condition for show pages
            when (page) {
                //if page is 0 show home screen
                0 -> {

                    TopDoctorsScreen(
                        dimen = dimen,
                        theme = theme,
                        navigateToBookingNavGraph = navigateToBookingNavGraph,
                        onClickSeeAll = onClickSeeAll,
                        navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
                        navigateToBmiNavGraph = navigateToBmiNavGraph,
                        navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
                        navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
                        navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
                        headerHeight = headerHeight
                    )
                }//end case
                //if page is 1 show search screen
                1 -> {

                    SearchScreen(
                        dimen = dimen,
                        theme = theme
                    )
                }//end case
                //if page is 2 show see all doctor screen
                2 -> {

                    TotalDoctorsScreen(
                        dimen = dimen,
                        theme = theme,
                        headerHeight = headerHeight
                    )
                }//end case

            }//end when

        }//end HorizontalPager

    }//end ConstraintLayout


    LaunchedEffect(
        key1 = uiState.focusOnSearch
    ) {

        //if value equal true scroll to search screen
        if (uiState.focusOnSearch) {

            //focus on search field
            focusRequester.requestFocus()

            //scroll to search screen
            coroutineScope.launch {
                pagerState.scrollToPage(
                    page = 1
                )
            }

        }//end if

    }//end LaunchedEffect

}//end DoctorSearchContent