@file:OptIn(ExperimentalFoundationApi::class)

package com.example.activity.uiElement.screens.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.BackButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.DropDownMenuPagerSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun HistoryScreen(
    popHistoryDestination: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)

    HistoryContent(
        onClickBack = popHistoryDestination,
        pagerState = pagerState
    )
}//end HistoryScreen

@Composable
private fun HistoryContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    pagerState: PagerState
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
            val (backButtonId, titleId, dropDownMenuId) = createRefs()
            val guideFromStart136 = createGuidelineFromStart(dimen.dimen_17.dp)

            BackButtonView(
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

            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    com.example.sharedui.R.string.all_history
                ),
                size = dimen.dimen_2_5,
                color = theme.black,
                modifier = Modifier
                    .constrainAs(titleId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_2_5.dp
                        )
                    }
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
                textSelectedSize = dimen.dimen_2,
                textItemSize = dimen.dimen_1_75,
                pagerState = pagerState,
                modifier = Modifier
                    .constrainAs(dropDownMenuId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_1.dp
                        )
                        end.linkTo(guideFromStart136)
                        top.linkTo(
                            titleId.bottom,
                            dimen.dimen_4.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

        }//end ConstraintLayout

    }//end BaseScreen

}//end HistoryContent