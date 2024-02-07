package com.example.heartrate.uiElement.screens.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.heartrate.uiElement.components.items.HeartRateLineChartSection
import com.example.heartrate.uiElement.components.items.StatusResultSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
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
import com.patrykandpatrick.vico.core.entry.entryModelOf


@Composable
internal fun StatisticsHeartRateScreen(
    popStatisticsHeartRateDestination: () -> Unit,
    navigateToMeasurementHeartRateDestination: () -> Unit
) {

    StatisticsHeartRateContent(
        onClickOnBackButton = popStatisticsHeartRateDestination,
        onClickOnAddRecordButton = navigateToMeasurementHeartRateDestination
    )
}//end StatisticsHeartRateScreen

@Composable
private fun StatisticsHeartRateContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnAddRecordButton: () -> Unit,
) {

    //create base screen to set navigation and status bar color here
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
                    id = R.string.heart_rate
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
                    }//end constrainAs
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
                        val (dateId, averageId, daysId, statusSectionId, chartId, recommendedId) = createRefs()
                        //create guides here
                        val guideFromEnd35P = createGuidelineFromEnd(0.35f)

                        //create date section here
                        IconTextSection(
                            theme = theme,
                            dimen = dimen,
                            text = "22-11-2023",
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
                                    top.linkTo(parent.top)
                                }//end constrainAs
                        )

                        //create average section here
                        MultiColorTextView(
                            dimen = dimen,
                            theme = theme,
                            parentText = "${stringResource(R.string.average)} 90 BPM",
                            subTexts = arrayOf(
                                stringResource(
                                    R.string.average
                                )
                            ),
                            subTextsColors = arrayOf(theme.redDark),
                            parentTextColor = theme.black,
                            fontFamily = robotoMedium,
                            textSize = dimen.dimen_2_25,
                            textAlign = null,
                            modifier = Modifier
                                .constrainAs(averageId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        guideFromEnd35P,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(
                                        dateId.bottom,
                                        dimen.dimen_2_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create row contain on days here
                        LazyRow(
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
                                        averageId.bottom,
                                        dimen.dimen_2_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        ) {

                            //create days here
                            items(
                                count = 7
                            ) {

                                //create single day here
                                DaySection(
                                    dimen = dimen,
                                    dayName = "Wed",
                                    dayNumber = "17",
                                    theme = theme
                                )

                            }//end items

                        }//end LazyRow

                        //create status result section here
                        StatusResultSection(
                            dimen = dimen,
                            theme = theme,
                            status = "NORMAL",
                            result = "95",
                            unit = "BPM",
                            modifier = Modifier
                                .constrainAs(statusSectionId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        daysId.bottom,
                                        dimen.dimen_1_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create heart rate chart here
                        HeartRateLineChartSection(
                            theme = theme,
                            dimen = dimen,
                            data = entryModelOf(60f, 60f, 90f, 120f, 100f, 50f, 100f),
                            maxValue = 330f,
                            xAxisData = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
                            bottomLabel = null,
                            modifier = Modifier
                                .constrainAs(chartId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        statusSectionId.bottom,
                                        dimen.dimen_1_25.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                                .aspectRatio(1.53f)
                        )

                        //create recommended section here
                        RecommendedSection(
                            dimen = dimen,
                            theme = theme,
                            equationText = "How to loss Sugar?",
                            responseText = "printing and typesetting industry.  Lorem Ipsum has been the industry's Lorem Ipsum is simply dummy text of the printing and typesetting industry.  Lorem Ipsum has been the industry's Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                            modifier = Modifier
                                .constrainAs(recommendedId) {
                                    top.linkTo(
                                        chartId.bottom,
                                        dimen.dimen_1_75.dp
                                    )
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

}//end StatisticsHeartRateContent