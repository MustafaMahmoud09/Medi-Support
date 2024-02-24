@file:OptIn(ExperimentalFoundationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.screens.activity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
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
import com.example.bmi.uiElement.screens.activities.BMIActivityScreen
import com.example.bloodsuger.uiElement.screens.activities.BloodSugarActivityScreen
import com.example.heartrate.uiElement.screens.activities.HeartRateActivityScreen
import com.example.bloodpressure.uiElement.screens.activities.BloodPressureActivityScreen
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.damanhour.Graduation.medisupport.ui.uiElement.components.items.DropDownMenuPagerSection
import com.damanhour.Graduation.medisupport.ui.uiState.state.ActivityUiState
import com.damanhour.Graduation.medisupport.ui.uiState.viewModel.ActivityViewModel
import com.example.sharedui.uiElement.navigation.data.MenuData
import com.example.sharedui.uiElement.navigation.transitions.scrollToPage
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ActivityScreen(
    viewModel: ActivityViewModel = hiltViewModel(),
    popActivityNavGraph: () -> Unit,
    navigateToHistoryDestination: (Int) -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToBmiNavGraph: () -> Unit
) {
    //get screen state from view model here
    val state = viewModel.state.collectAsState()
    val uiState = state.value

    val pagerState = rememberPagerState(initialPage = 0)

    val coroutineScope = rememberCoroutineScope()

    ActivityContent(
        onClickBack = popActivityNavGraph,
        pagerState = pagerState,
        navigateToHistoryDestination = navigateToHistoryDestination,
        uiState = uiState,
        onDropMenusExpandedChanged = viewModel::onDropMenusExpandedChanged,
        healthCareMenusData = arrayOf(
            MenuData(
                title = stringResource(
                    R.string.bmi
                ),
                onClick = {
                    //execute scroll here
                    pagerState.scrollToPage(
                        coroutineScope = coroutineScope,
                        page = 0
                    )

                    //change current page here
                    viewModel.onCurrentHealthCarePageChanged(0)
                }
            ),
            MenuData(
                title = stringResource(
                    R.string.blood_pressure
                ),
                onClick = {
                    //execute scroll here
                    pagerState.scrollToPage(
                        coroutineScope = coroutineScope,
                        page = 1
                    )

                    //change current page here
                    viewModel.onCurrentHealthCarePageChanged(1)
                }
            ),
            MenuData(
                title = stringResource(
                    R.string.blood_suger
                ),
                onClick = {
                    //execute scroll here
                    pagerState.scrollToPage(
                        coroutineScope = coroutineScope,
                        page = 2
                    )

                    //change current page here
                    viewModel.onCurrentHealthCarePageChanged(2)
                }
            ),
            MenuData(
                title = stringResource(
                    R.string.heart_rate
                ),
                onClick = {
                    //execute scroll here
                    pagerState.scrollToPage(
                        coroutineScope = coroutineScope,
                        page = 3
                    )

                    //change current page here
                    viewModel.onCurrentHealthCarePageChanged(3)
                }
            ),
        ),
        onClickOnAddRecordButton = {

            when (uiState.currentPage) {

                //if current page equal 0 navigate to bmi nav graph
                0 -> navigateToBmiNavGraph()

                //if current page equal 1 navigate to blood pressure nav graph
                1 -> navigateToBloodPressureNavGraph()

                //if current page equal 2 navigate to blood sugar nav graph
                2 -> navigateToBloodSugarNavGraph()

                //if current page equal 3 navigate to heart rate nav graph
                3 -> navigateToHeartRateNavGraph()

            }//end when

        }//end onClickOnAddRecordButton
    )
}//end ActivityScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ActivityContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    pagerState: PagerState,
    navigateToHistoryDestination: (Int) -> Unit,
    healthCareMenusData: Array<MenuData>,
    onClickOnAddRecordButton: () -> Unit,
    uiState: ActivityUiState,
    onDropMenusExpandedChanged: () -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        ConstraintLayout(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize()
                .background(
                    color = theme.background
                )
        ) {
            val (backButtonId, dropDownMenuId, buttonAddRecordId, activityPageId) = createRefs()

            val guideFromStart25P = createGuidelineFromStart(.25f)
            val guideFromEnd25P = createGuidelineFromEnd(.25f)

            IconButtonView(
                dimen = dimen,
                theme = theme,
                onClick = onClickBack,
                modifier = Modifier
                    .constrainAs(backButtonId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            parent.top,
                            dimen.dimen_2_5.dp
                        )
                    }//end constrainAs
            )

            DropDownMenuPagerSection(
                dimen = dimen,
                theme = theme,
                menus = healthCareMenusData,
                currentPage = uiState.currentPage,
                menusExpanded = uiState.menusExpanded,
                onDropMenusExpandedChanged = onDropMenusExpandedChanged,
                modifier = Modifier
                    .constrainAs(dropDownMenuId) {
                        start.linkTo(guideFromStart25P)
                        end.linkTo(guideFromEnd25P)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_2_5.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            BasicButtonView(
                dimen = dimen,
                theme = theme,
                text = stringResource(
                    R.string.add_record
                ),
                onClick = onClickOnAddRecordButton,
                modifier = Modifier
                    .constrainAs(buttonAddRecordId) {
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
                            dimen.dimen_1_5.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            HorizontalPager(
                state = pagerState,
                pageCount = 4,
                userScrollEnabled = false,
                modifier = Modifier
                    .constrainAs(activityPageId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(dropDownMenuId.bottom)
                        bottom.linkTo(buttonAddRecordId.top)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                    .padding(
                        top = dimen.dimen_1.dp
                    )
            ) { page ->

                when (page) {
                    0 -> {
                        BMIActivityScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToHistoryDestination = { navigateToHistoryDestination(0) }
                        )
                    }

                    1 -> {
                        BloodPressureActivityScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToHistoryDestination = { navigateToHistoryDestination(1) }
                        )
                    }

                    2 -> {
                        BloodSugarActivityScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToHistoryDestination = { navigateToHistoryDestination(2) }
                        )
                    }

                    3 -> {
                        HeartRateActivityScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToHistoryDestination = { navigateToHistoryDestination(3) }
                        )
                    }
                }//end when

            }//end HorizontalPager

        }//end ConstraintLayout

    }//end BaseScreen

}//end ActivityContent
