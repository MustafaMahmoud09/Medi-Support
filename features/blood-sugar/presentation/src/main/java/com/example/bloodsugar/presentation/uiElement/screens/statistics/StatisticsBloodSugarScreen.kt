package com.example.bloodsugar.presentation.uiElement.screens.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodsugar.presentation.uiElement.components.items.SugarLevelResultSection
import com.example.bloodsugar.presentation.uiState.state.StatisticsBloodSugarUiState
import com.example.bloodsugar.presentation.uiState.viewModel.StatisticsBloodSugarViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.ColumnChartView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.components.items.RecommendedSection
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.google.accompanist.placeholder.placeholder
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
internal fun StatisticsBloodSugarScreen(
    viewModel: StatisticsBloodSugarViewModel = hiltViewModel(),
    popStatisticsBloodSugarDestination: () -> Unit,
    navigateToRecordBloodSugarDestination: () -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    StatisticsBloodSugarContent(
        onClickOnBackButton = popStatisticsBloodSugarDestination,
        onClickOnAddRecordButton = navigateToRecordBloodSugarDestination,
        daysColumnState = rememberLazyListState(),
        uiState = state.value
    )
}//end StatisticsBloodSugarScreen


@Composable
private fun StatisticsBloodSugarContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnAddRecordButton: () -> Unit,
    daysColumnState: LazyListState,
    uiState: StatisticsBloodSugarUiState
) {

    //create base screen for define status and navigation bar color here
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
            val (headerId, recyclerContainerId, addRecordButtonId) = createRefs()

            //create header here
            HeaderSection(
                dimen = dimen,
                theme = theme,
                onClickOnBackButton = onClickOnBackButton,
                title = stringResource(
                    id = R.string.blood_suger
                ),
                modifier = Modifier
                    .constrainAs(headerId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            parent.top,
                            dimen.dimen_2_5.dp
                        )
                        width = Dimension.fillToConstraints
                    }//end constrainAs
            )

            //create add record button here
            BasicButtonView(
                dimen = dimen,
                theme = theme,
                text = stringResource(
                    id = R.string.add_record
                ),
                onClick = onClickOnAddRecordButton,
                modifier = Modifier
                    .constrainAs(addRecordButtonId) {
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

            //create column contain on all components here
            LazyColumn(
                modifier = Modifier
                    .constrainAs(recyclerContainerId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            headerId.bottom,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(addRecordButtonId.top)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    top = dimen.dimen_0_25.dp,
                    bottom = dimen.dimen_2.dp
                )
            ) {

                item(
                    key = 1
                ) {

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //create ids for screen components here
                        val (dateId, daysId, resultSectionId, chartId, recommendedId) = createRefs()

                        //create guides here
                        val guideFromEnd37P = createGuidelineFromEnd(0.37f)

                        //create date section here
                        IconTextSection(
                            theme = theme,
                            dimen = dimen,
                            text = uiState.adviceBloodSugarModel?.createdAt ?: "",
                            fontFamily = robotoMedium,
                            fontSize = dimen.dimen_2,
                            fontColor = theme.hintIconBottom,
                            icon = painterResource(
                                id = R.drawable.reminder_icon_health_care
                            ),
                            iconSize = dimen.dimen_3,
                            iconTint = theme.black,
                            spaceBetweenComponents = dimen.dimen_1,
                            modifier = Modifier
                                .constrainAs(dateId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        guideFromEnd37P,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(parent.top)
                                    width = Dimension.fillToConstraints
                                }
                                .placeholder(
                                    visible = uiState.adviceBloodSugarModel == null,
                                    color = theme.background
                                )//end constrainAs
                        )

                        //create row contain on days here
                        LazyRow(
                            state = daysColumnState,
                            contentPadding = PaddingValues(
                                horizontal = dimen.dimen_1_75.dp
                            ),
                            horizontalArrangement = Arrangement.spacedBy(
                                space = dimen.dimen_1_75.dp,
                            ),
                            modifier = Modifier
                                .constrainAs(daysId) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(
                                        dateId.bottom,
                                        dimen.dimen_3.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        ) {

                            //create days here
                            items(
                                count = uiState.monthDays.size
                            ) { count ->

                                //create single day here
                                DaySection(
                                    dimen = dimen,
                                    theme = theme,
                                    dayName = uiState.monthDays[count].name,
                                    dayNumber = "${uiState.monthDays[count].number}",
                                    toDay = uiState.monthDays[count].today
                                )

                            }//end items

                        }//end LazyRow

                        //create result section here
                        SugarLevelResultSection(
                            dimen = dimen,
                            theme = theme,
                            status = "${uiState.adviceBloodSugarModel?.type}:",
                            level = ((uiState.adviceBloodSugarModel?.level ?: 0).toString()) + " ",
                            unit = stringResource(
                                R.string.mg_gl
                            ),
                            modifier = Modifier
                                .constrainAs(resultSectionId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        daysId.bottom,
                                        dimen.dimen_4_25.dp
                                    )
                                }
                                .placeholder(
                                    visible = uiState.adviceBloodSugarModel == null,
                                    color = theme.background
                                )//end constrainAs
                        )

                        //create blood sugar chart here
                        ColumnChartView(
                            theme = theme,
                            dimen = dimen,
                            data = uiState.getBloodSugarChartStatus.dataResult,
                            maxValue = uiState.getBloodSugarChartStatus.maxValue.toFloat(),
                            xAxisData = uiState.getBloodSugarChartStatus.xAxisData.ifEmpty {
                                listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                            },
                            modifier = Modifier
                                .constrainAs(chartId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_3_5.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_3_5.dp
                                    )
                                    top.linkTo(
                                        resultSectionId.bottom,
                                        dimen.dimen_1_25.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                                .aspectRatio(1.42f)
                        )

                        //create recommended section here
                        RecommendedSection(
                            dimen = dimen,
                            theme = theme,
                            equationText = stringResource(R.string.how_to_control_blood_sugar),
                            responseText = uiState.adviceBloodSugarModel?.advice ?: "",
                            modifier = Modifier
                                .constrainAs(recommendedId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        chartId.bottom,
                                        dimen.dimen_3_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
//                                    .placeholder(
//                                    visible = uiState.adviceBloodSugarModel == null,
//                                    color = theme.background
//                                )
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

    LaunchedEffect(key1 = true) {

        daysColumnState.scrollToItem(uiState.currentDayNumber - 1)

    }//end LaunchedEffect

}//end StatisticsBloodSugarContent