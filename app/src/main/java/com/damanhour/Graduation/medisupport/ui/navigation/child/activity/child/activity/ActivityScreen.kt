@file:OptIn(ExperimentalFoundationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.activity

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.bmi.uiElement.screens.activities.BMIScreen
import com.example.bloodsuger.uiElement.screens.activities.BloodSugarScreen
import com.example.heartrate.uiElement.screens.activities.HeartRateScreen
import com.example.bloodpressure.uiElement.screens.activities.BloodPressureScreen
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.items.DropDownMenuPagerSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ActivityScreen(
    popActivityNavGraph: () -> Unit,
    navigateToHistoryDestination: () -> Unit
) {

    val pagerState = rememberPagerState(initialPage = 0)

    ActivityContent(
        onClickBack = popActivityNavGraph,
        pagerState = pagerState,
        navigateToHistoryDestination = navigateToHistoryDestination
    )
}//end ActivityScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ActivityContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    pagerState: PagerState,
    navigateToHistoryDestination: () -> Unit
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
                options = listOf(
                    stringResource(
                        com.example.sharedui.R.string.bmi
                    ),
                    stringResource(
                        com.example.sharedui.R.string.blood_pressure
                    ),
                    stringResource(
                        com.example.sharedui.R.string.blood_suger
                    ),
                    stringResource(
                        com.example.sharedui.R.string.heart_rate
                    )
                ),
                pagerState = pagerState,
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
                    com.example.sharedui.R.string.add_record
                ),
                onClick = { /*TODO*/ },
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
                    }.padding(
                        top = dimen.dimen_1.dp
                    )
            ) { page ->

                when (page) {
                    0 -> {
                        BMIScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToHistoryDestination = navigateToHistoryDestination
                        )
                    }

                    1 -> {
                        BloodPressureScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToHistoryDestination = navigateToHistoryDestination
                        )
                    }

                    2 -> {
                        BloodSugarScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToHistoryDestination = navigateToHistoryDestination
                        )
                    }

                    3 -> {
                        HeartRateScreen(
                            theme = theme,
                            dimen = dimen,
                            navigateToHistoryDestination = navigateToHistoryDestination
                        )
                    }
                }//end when

            }//end HorizontalPager

        }//end ConstraintLayout

    }//end BaseScreen

}//end ActivityContent
