package com.example.bloodpressure.uiElement.screens.record

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
import com.example.bloodpressure.uiElement.components.items.InputPressureFieldSection
import com.example.bloodpressure.uiElement.components.items.TypeStateSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme


@Composable
internal fun RecordBloodPressureScreen(
    popBloodPressureNavGraph: () -> Unit,
    navigateToStatisticsBloodPressureDestination: () -> Unit
) {

    RecordBloodPressureContent(
        onClickOnBackButton = popBloodPressureNavGraph,
        onClickOnAddRecordButton = navigateToStatisticsBloodPressureDestination
    )
}//end RecordBloodPressureScreen

@Composable
private fun RecordBloodPressureContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnAddRecordButton: () -> Unit
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
            val (headerId, recyclerContainerId) = createRefs()

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
                            dimen.dimen_3_25.dp
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
                        bottom.linkTo(parent.bottom)
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
                            inputDataTextId, systolicFieldId, diastolicFieldId, addRecordButtonId) = createRefs()
                        //create guides here
                        val guideFromEnd35P = createGuidelineFromEnd(0.35f)
                        val guideFromEnd19P = createGuidelineFromEnd(0.19F)

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

                        //create input data text here
                        TextBoldView(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                id = R.string.input_data
                            ),
                            size = dimen.dimen_2_5,
                            color = theme.black,
                            modifier = Modifier
                                .constrainAs(inputDataTextId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_4_25.dp
                                    )
                                    top.linkTo(
                                        lineId.bottom,
                                        dimen.dimen_2.dp
                                    )
                                }
                        )

                        //create input systolic field here
                        InputPressureFieldSection(
                            dimen = dimen,
                            theme = theme,
                            hint = stringResource(
                                id = R.string.systolic_blood_pressure
                            ),
                            input = 100,
                            modifier = Modifier
                                .constrainAs(systolicFieldId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_3_75.dp
                                    )
                                    end.linkTo(guideFromEnd19P)
                                    top.linkTo(
                                        inputDataTextId.bottom,
                                        dimen.dimen_2_25.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create input diastolic field here
                        InputPressureFieldSection(
                            dimen = dimen,
                            theme = theme,
                            hint = stringResource(
                                id = R.string.diastolic_blood_pressure
                            ),
                            input = 80,
                            modifier = Modifier
                                .constrainAs(diastolicFieldId) {
                                    start.linkTo(systolicFieldId.start)
                                    end.linkTo(systolicFieldId.end)
                                    top.linkTo(
                                        systolicFieldId.bottom,
                                        dimen.dimen_2_25.dp
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
                                .constrainAs(addRecordButtonId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        diastolicFieldId.bottom,
                                        dimen.dimen_2_25.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

}//end RecordBloodPressureContent