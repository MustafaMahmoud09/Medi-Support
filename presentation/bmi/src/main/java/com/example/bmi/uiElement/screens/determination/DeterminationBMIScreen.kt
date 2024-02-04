package com.example.bmi.uiElement.screens.determination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.bmi.uiElement.components.items.BMIResultSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.components.items.RecommendedSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme


@Composable
internal fun DeterminationBMIScreen(
    popDeterminationBMIDestination: () -> Unit,
    navigateToRecordBMIDestination: () -> Unit
) {

    DeterminationBMIContent(
        onClickOnBackButton = popDeterminationBMIDestination,
        onClickOnAddRecordButton = navigateToRecordBMIDestination
    )
}//end DeterminationBMIDestination

@Composable
private fun DeterminationBMIContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnAddRecordButton: () -> Unit
) {

    //create base screen here to set status bar color and navigation bar color
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
                .padding(
                    top = dimen.dimen_2_5.dp
                )
        ) {
            //create ids for screen components here
            val (backButton, title, containerId, addRecordButtonId) = createRefs()

            //create back button here
            IconButtonView(
                dimen = dimen,
                theme = theme,
                onClick = onClickOnBackButton,
                modifier = Modifier
                    .constrainAs(backButton) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(parent.top)
                    }//end constrainAs
            )

            //create title screen here
            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    id = R.string.bmi
                ),
                size = dimen.dimen_2_25,
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }//end constrainAs
            )


            //create button add record here
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

            //create container here
            LazyColumn(
                modifier = Modifier
                    .constrainAs(containerId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(addRecordButtonId.top)
                        top.linkTo(
                            title.bottom,
                            dimen.dimen_2.dp
                        )
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    top = dimen.dimen_1_25.dp,
                    bottom = dimen.dimen_1_5.dp
                )
            ) {

                //create item contain on items screen here
                item(
                    key = 1
                ) {

                    //create sub container here
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //create ids for screen components here
                        val (dateId, resultSectionId, lineId, recommendedId) = createRefs()

                        //create guides here
                        val guideFromStart25P = createGuidelineFromStart(0.25f)
                        val guideFromEnd25P = createGuidelineFromEnd(0.25f)


                        //create date section here
                        IconTextSection(
                            theme = theme,
                            dimen = dimen,
                            text = "22-11-2023",
                            fontFamily = robotoMedium,
                            fontSize = dimen.dimen_2,
                            fontColor = theme.black,
                            icon = painterResource(
                                id = R.drawable.reminder_icon_health_care
                            ),
                            iconSize = dimen.dimen_3,
                            spaceBetweenComponents = dimen.dimen_1,
                            iconTint = theme.black,
                            modifier = Modifier
                                .constrainAs(dateId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_3_25.dp
                                    )
                                    top.linkTo(parent.top)
                                }//end constrainAs
                        )

                        //create result section here
                        BMIResultSection(
                            dimen = dimen,
                            theme = theme,
                            result = 22.4f,
                            typeStateUser = "Normal",
                            modifier = Modifier
                                .constrainAs(resultSectionId) {
                                    start.linkTo(guideFromStart25P)
                                    end.linkTo(guideFromEnd25P)
                                    top.linkTo(
                                        dateId.bottom,
                                        dimen.dimen_4_75.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create line separate here
                        LineView(
                            dimen = dimen,
                            theme = theme,
                            color = theme.redDark,
                            height = .77f,
                            modifier = Modifier
                                .constrainAs(lineId) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(
                                        resultSectionId.bottom,
                                        dimen.dimen_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create recommended here
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
                                        lineId.bottom,
                                        dimen.dimen_3.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

}//end DeterminationBMIContent