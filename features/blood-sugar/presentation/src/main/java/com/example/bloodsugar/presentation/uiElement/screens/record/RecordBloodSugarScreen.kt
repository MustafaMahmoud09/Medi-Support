@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bloodsugar.presentation.uiElement.screens.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodsugar.presentation.uiElement.components.items.LazySliderSection
import com.example.bloodsugar.presentation.uiElement.components.items.StatusMenuSection
import com.example.bloodsugar.presentation.uiElement.components.items.StatusSection
import com.example.bloodsugar.presentation.uiState.state.RecordBloodSugarUiState
import com.example.bloodsugar.presentation.uiState.viewModel.RecordBloodSugarViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction0


@Composable
internal fun RecordBloodSugarScreen(
    viewModel: RecordBloodSugarViewModel = hiltViewModel(),
    navigateToStatisticsBloodSugarDestination: () -> Unit,
    popRecordBloodSugarDestination: () -> Unit
) {
    //get screen state from view model here
    val state = viewModel.state.collectAsState()

    val statusSheetState = rememberModalBottomSheetState()

    RecordBloodSugarContent(
        onClickOnBackButton = popRecordBloodSugarDestination,
        onClickOnAddRecordButton = navigateToStatisticsBloodSugarDestination,
        uiState = state.value,
        onSugarLevelChanged = viewModel::onSugarLevelChanged,
        onStatusStateReversed = viewModel::onStatusStateReversed,
        statusSheetState = statusSheetState
    )
}//end RecordBloodSugarScreen

@Composable
private fun RecordBloodSugarContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnAddRecordButton: () -> Unit,
    uiState: RecordBloodSugarUiState,
    onSugarLevelChanged: (Float) -> Unit,
    statusSheetState: SheetState,
    onStatusStateReversed: KFunction0<Unit>,
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
            val (headerId, recyclerContainerId, statusBottomSheetId) = createRefs()

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
                        val (dateId, daysId, statusSheetId, lazySliderId, addRecordButton) = createRefs()

                        //create guides here
                        val guideFromEnd37P = createGuidelineFromEnd(0.37f)

                        //create date section here
                        IconTextSection(
                            theme = theme,
                            dimen = dimen,
                            text = "22-11-2023",
                            fontFamily = robotoMedium,
                            fontSize = dimen.dimen_2,
                            fontColor = theme.hintIconBottom,
                            icon = painterResource(
                                id = com.example.sharedui.R.drawable.reminder_icon_health_care
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
                                }//end constrainAs
                        )


                        //create status sheet section here
                        StatusMenuSection(
                            theme = theme,
                            dimen = dimen,
                            icon = if (!uiState.statusState) painterResource(
                                id = R.drawable.drop_sheet_icon
                            ) else painterResource(
                                id = R.drawable.drag_sheet_icon
                            ),
                            onClick = onStatusStateReversed,
                            status = "Default",
                            modifier = Modifier
                                .constrainAs(statusSheetId) {
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    start.linkTo(guideFromEnd37P)
                                    top.linkTo(parent.top)
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
                                        statusSheetId.bottom,
                                        dimen.dimen_3.dp
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

                        //create blood sugar slider here
                        LazySliderSection(
                            dimen = dimen,
                            theme = theme,
                            unit = stringResource(
                                id = R.string.mg_gl
                            ),
                            value = uiState.sugarLevel,
                            onValueChanged = onSugarLevelChanged,
                            startPoint = 10,
                            endPoint = 400,
                            modifier = Modifier
                                .constrainAs(lazySliderId) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(
                                        daysId.bottom,
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
                                id = com.example.sharedui.R.string.add_record
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
                                    top.linkTo(
                                        lazySliderId.bottom,
                                        dimen.dimen_3.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

            //if status state equal true create modal sheet
            if (uiState.statusState) {

                //create modal bottom sheet
                ModalBottomSheet(
                    onDismissRequest = { onStatusStateReversed() },
                    sheetState = statusSheetState,
                    containerColor = theme.background
                ) {

                    //create column contain on status here
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(
                            horizontal = dimen.dimen_1_75.dp,
                            vertical = dimen.dimen_2.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(
                            space = dimen.dimen_3.dp
                        )
                    ) {

                        items(
                            count = 10
                        ){

                            StatusSection(
                                dimen = dimen,
                                theme = theme,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        }

                    }//end LazyColumn

                }//end ModalBottomSheet

            }//end if

        }//end ConstraintLayout

    }//end BaseScreen

}//end RecordBloodSugarContent