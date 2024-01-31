package com.example.bmi.uiElement.screens.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.bmi.uiElement.components.items.GenderSection
import com.example.bmi.uiElement.components.items.BMISliderSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun RecordBMIScreen(
    popBmiNavGraph: () -> Unit,
    navigateToDeterminationBMIDestination: () -> Unit
) {

    RecordBMIContent(
        onClickOnBackButton = popBmiNavGraph,
        onClickOnButtonCalculate = navigateToDeterminationBMIDestination
    )
}//end RecordBMIScreen

@Composable
private fun RecordBMIContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickOnBackButton: () -> Unit,
    onClickOnButtonCalculate: () -> Unit
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
            val (backButton, title, containerId) = createRefs()

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

            //create container here
            LazyColumn(
                modifier = Modifier
                    .constrainAs(containerId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            backButton.bottom,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    bottom = dimen.dimen_2.dp,
                    top = dimen.dimen_0_5.dp
                )
            ) {

                //create item contain on all components here
                item(
                    key = 1
                ) {
                    //create sub container here
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //create ids for screen components here
                        val (genderSectionId, ageSectionId, heightSectionId, weightSectionId, calculateButtonId) = createRefs()

                        //create gender section here
                        GenderSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                id = R.string.gender
                            ),
                            firstType = stringResource(
                                id = R.string.female
                            ),
                            secondType = stringResource(
                                id = R.string.male
                            ),
                            itemSelected = 0,
                            onClickOnItem = {},
                            modifier = Modifier
                                .constrainAs(genderSectionId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(parent.top)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create age section here
                        BMISliderSection(
                            dimen = dimen,
                            theme = theme,
                            title = stringResource(
                                id = R.string.age
                            ),
                            value = 13f,
                            onValueChange = {},
                            unit = stringResource(
                                id = R.string.years
                            ),
                            startPoint = 0,
                            endPoint = 100,
                            modifier = Modifier
                                .constrainAs(ageSectionId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_1.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(
                                        genderSectionId.bottom,
                                        dimen.dimen_3.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create height section here
                        BMISliderSection(
                            dimen = dimen,
                            theme = theme,
                            title = stringResource(
                                id = R.string.height
                            ),
                            value = 65f,
                            onValueChange = {},
                            unit = stringResource(
                                id = R.string.cm
                            ),
                            startPoint = 10,
                            endPoint = 300,
                            modifier = Modifier
                                .constrainAs(heightSectionId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_1.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(
                                        ageSectionId.bottom,
                                        dimen.dimen_2_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create weight section here
                        BMISliderSection(
                            dimen = dimen,
                            theme = theme,
                            title = stringResource(
                                id = R.string.weight
                            ),
                            value = 130f,
                            onValueChange = {},
                            unit = stringResource(
                                id = R.string.kg
                            ),
                            startPoint = 0,
                            endPoint = 300,
                            modifier = Modifier
                                .constrainAs(weightSectionId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_1.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(
                                        heightSectionId.bottom,
                                        dimen.dimen_2_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create calculate button here
                        BasicButtonView(
                            dimen = dimen,
                            theme = theme,
                            text = stringResource(
                                id = R.string.calculate
                            ),
                            onClick = onClickOnButtonCalculate,
                            modifier = Modifier
                                .constrainAs(calculateButtonId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(
                                        weightSectionId.bottom,
                                        dimen.dimen_3_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

}//end RecordBMIContent