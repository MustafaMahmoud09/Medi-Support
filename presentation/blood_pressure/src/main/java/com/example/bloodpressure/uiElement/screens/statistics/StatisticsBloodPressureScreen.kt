package com.example.bloodpressure.uiElement.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.bloodpressure.uiElement.components.items.TypeStateSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.bloodpressure.uiElement.components.items.BloodPressureLineChartSection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.components.items.RecommendedSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.component.shape.dashedShape
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
internal fun StatisticsBloodPressureScreen(
    popStatisticsBloodPressureDestination: () -> Unit,
    navigateToRecordBloodPressureDestination: () -> Unit
) {

    StatisticsBloodPressureContent(
        onClickOnBackButton = popStatisticsBloodPressureDestination,
        onClickOnAddRecordButton = navigateToRecordBloodPressureDestination
    )
}//end StatisticsBloodPressureScreen

@Composable
private fun StatisticsBloodPressureContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnAddRecordButton: () -> Unit,
) {

    //create base screen for define status bar color and navigation bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        //create container here
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .background(
                    color = theme.background
                )
        ) {
            //create ids for screen components here
            val (headerId, recyclerContainerId, addRecordButton) = createRefs()

            //create header section here
            HeaderSection(
                dimen = dimen,
                theme = theme,
                onClickOnBackButton = onClickOnBackButton,
                title = stringResource(
                    id = R.string.blood_pressure
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
                    }
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
                    .constrainAs(addRecordButton) {
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
                        bottom.linkTo(addRecordButton.top)
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
                        val (dateId, averageId, typeStateId, daysId, lineId,
                            bloodPressureChartId, recommendedId) = createRefs()
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
                            parentText = "${stringResource(R.string.average)} 140/90 mmHG",
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

                        //create type state user section here
                        TypeStateSection(
                            theme = theme,
                            dimen = dimen,
                            type = "Normal",
                            modifier = Modifier
                                .constrainAs(typeStateId) {
                                    top.linkTo(
                                        dateId.bottom,
                                        dimen.dimen_2_5.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2_5.dp
                                    )
                                    start.linkTo(guideFromEnd35P)
                                    width = Dimension.fillToConstraints
                                }//end constrainAs
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
                                        typeStateId.bottom,
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

                        //create line separate here
                        LineView(
                            dimen = dimen,
                            theme = theme,
                            height = .77f,
                            color = theme.gray939393,
                            modifier = Modifier
                                .constrainAs(lineId) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(
                                        daysId.bottom,
                                        dimen.dimen_1_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create blood pressure chart here
                        BloodPressureLineChartSection(
                            theme = theme,
                            dimen = dimen,
                            xAxisData = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
                            firstData = entryModelOf(105f, 110f, 90f, 120f, 100f, 85f, 100f),
                            maxValueForFirstData = 330f,
                            secondData = entryModelOf(60f, 60f, 70f, 80f, 75f, 60f, 80f),
                            maxValueForSecondData = 330f,
                            titleForFirstChart = stringResource(
                                R.string.upper_bound
                            ),
                            titleForSecondChart = stringResource(
                                R.string.lower_bound
                            ),
                            unit = stringResource(
                                R.string.mmhg
                            ),
                            bottomAxisVisibility = false,
                            endAxisVisibility = false,
                            guideLine = axisGuidelineComponent(
                                color = theme.blackLight353535,
                                strokeColor = theme.blackLight353535,
                                shape = Shapes.dashedShape(
                                    shape = Shapes.roundedCornerShape(90),
                                    dashLength = dimen.dimen_0_25.dp,
                                    gapLength = dimen.dimen_1_25.dp,
                                    fitStrategy = DashedShape.FitStrategy.Fixed
                                ),
                                margins = emptyDimensions(),
                            ),
                            maxYLinesForFirstChart = 4,
                            maxYLinesForSecondChart = 3,
                            modifier = Modifier
                                .constrainAs(bloodPressureChartId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_1_5.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_1_5.dp
                                    )
                                    top.linkTo(
                                        lineId.bottom,
                                        dimen.dimen_1_75.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create recommended section here
                        RecommendedSection(
                            dimen = dimen,
                            theme = theme,
                            equationText = "How to loss Sugar?",
                            responseText = "printing and typesetting industry.  Lorem Ipsum has been the industry's Lorem Ipsum is simply dummy text of the printing and typesetting industry.  Lorem Ipsum has been the industry's Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
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
                                        bloodPressureChartId.bottom,
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

}//end StatisticsBloodPressureContent