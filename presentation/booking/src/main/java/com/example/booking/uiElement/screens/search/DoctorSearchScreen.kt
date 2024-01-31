@file:OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)

package com.example.booking.uiElement.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.booking.uiElement.components.items.SearchSection
import com.example.booking.uiElement.screens.search.child.HomeScreen
import com.example.booking.uiElement.screens.search.child.SearchScreen
import com.example.booking.uiElement.screens.search.child.SeeAllDoctorScreen
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//function for collect state and execute action from view model
@Composable
internal fun DoctorSearchScreen(
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToAddReminderDestination: () -> Unit,
    navigateToBmiNavGraph: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    val isFocused = rememberSaveable {
        mutableStateOf(false)
    }
    val focusRequester = remember { FocusRequester() }

    DoctorSearchContent(
        pagerState = pagerState,
        onClickOnReminder = navigateToAddReminderDestination,
        navigateToBmiNavGraph = navigateToBmiNavGraph,
        onClickSeeAll = {
            //execute scroll to see all doctor screen
            coroutineScope.launch {
                pagerState.scrollToPage(2)
            }//end launch

        },//end onClickSeeAll
        onClickBack = {

            coroutineScope.launch {
                //when back if current page is search make is focus equal false to make search field is not enable
                if (pagerState.currentPage == 1) {
                    isFocused.value = false
                }

                //on back scroll to home screen
                pagerState.scrollToPage(0)

            }//end launch

        },//end onClickBack
        focusOnSearch = isFocused.value,
        focusRequester = focusRequester,
        coroutineScope = coroutineScope,
        onClickOnSearchField = {
            //if not exist in search screen scroll to search screen
            if (pagerState.currentPage != 1) {

                isFocused.value = true

            }//end if

        },//end onClickOnSearchField
        navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph
    )

}//end DoctorSearchScreen

//function for observe state and draw components
@Composable
private fun DoctorSearchContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    pagerState: PagerState,
    onClickSeeAll: () -> Unit,
    onClickBack: () -> Unit,
    focusOnSearch: Boolean,
    focusRequester: FocusRequester,
    navigateToHeartPredictionNavGraph: () -> Unit,
    onClickOnSearchField: () -> Unit,
    coroutineScope: CoroutineScope,
    onClickOnReminder: () -> Unit,
    navigateToBmiNavGraph: () -> Unit
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
        val (buttonBackId, searchId, reminderButtonId, searchPagerId) = createRefs()

        //create search field here
        SearchSection(
            dimen = dimen,
            theme = theme,
            key = "",
            hint = stringResource(
                com.example.sharedui.R.string.search
            ),
            onChange = {},
            isFocus = focusOnSearch,
            focusRequester = focusRequester,
            onClick = onClickOnSearchField,
            modifier = Modifier
                .constrainAs(searchId) {
                    start.linkTo(
                        parent.start,
                        //if exist in page 0
                        if (pagerState.currentPage == 0) {
                            dimen.dimen_2.dp
                        }//end if
                        //if exist in page 1 or 2
                        else {
                            dimen.dimen_6_25.dp
                        }//end else
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_6_25.dp
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
            if (pagerState.currentPage == 0) {
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

        //create reminder button here
        IconButtonView(
            icon = painterResource(
                id = com.example.sharedui.R.drawable.reminder_icon
            ),
            dimen = dimen,
            theme = theme,
            onClick = onClickOnReminder,
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

                    HomeScreen(
                        dimen = dimen,
                        theme = theme,
                        onClickSeeAll = onClickSeeAll,
                        navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
                        navigateToBmiNavGraph = navigateToBmiNavGraph
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

                    SeeAllDoctorScreen(
                        dimen = dimen,
                        theme = theme
                    )
                }//end case

            }//end when

        }//end HorizontalPager

    }//end ConstraintLayout


    LaunchedEffect(focusOnSearch) {

        //if value equal true scroll to search screen
        if (focusOnSearch) {

            //focus on search field
            focusRequester.requestFocus()

            //scroll to search screen
            coroutineScope.launch {
                pagerState.scrollToPage(1)
            }

        }//end if


    }//end LaunchedEffect

}//end DoctorSearchContent