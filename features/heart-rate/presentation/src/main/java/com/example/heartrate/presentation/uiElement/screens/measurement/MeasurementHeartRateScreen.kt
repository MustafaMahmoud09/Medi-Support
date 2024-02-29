package com.example.heartrate.presentation.uiElement.screens.measurement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.heartrate.presentation.uiElement.components.items.CalculateHeartRateSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay

@Composable
internal fun MeasurementHeartRateScreen(
    popMeasurementHeartRateDestination: () -> Unit,
    navigateToStatisticsHeartRateDestination: () -> Unit
) {

    MeasurementHeartRateContent(
        onClickOnBackButton = popMeasurementHeartRateDestination
    )

    LaunchedEffect(
        key1 = true
    ) {
        delay(5000)
        navigateToStatisticsHeartRateDestination()
    }//end LaunchedEffect

}//end MeasurementHeartRateScreen

@Composable
private fun MeasurementHeartRateContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit
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
            val (backButtonId, recyclerContainerId) = createRefs()

            //create back button here
            IconButtonView(
                dimen = dimen,
                theme = theme,
                onClick = onClickOnBackButton,
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
                    }
            )

            //create column contain on all components here
            LazyColumn(
                modifier = Modifier
                    .constrainAs(recyclerContainerId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            backButtonId.bottom,
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

                //create item contain on container here
                item(
                    key = 1
                ) {

                    //create container here
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //create ids for components here
                        val (heartRateIconId, calculateHeartRateId, measuringTextId, messageId) = createRefs()

                        //create guides here
                        val guideFromStart21P = createGuidelineFromStart(0.21f)
                        val guideFromEnd21P = createGuidelineFromEnd(0.21f)

                        //create heart rate calculate image here
                        Image(
                            painter = painterResource(
                                id = R.drawable.heart_rate_calculate_image
                            ),
                            contentDescription = "image",
                            modifier = Modifier
                                .constrainAs(heartRateIconId) {
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
                                .aspectRatio(
                                    ratio = 1.33f
                                )
                        )

                        //create calculate heart rate section here
                        com.example.heartrate.presentation.uiElement.components.items.CalculateHeartRateSection(
                            dimen = dimen,
                            theme = theme,
                            unit = "BPM",
                            rate = "00",
                            modifier = Modifier
                                .constrainAs(calculateHeartRateId) {
                                    start.linkTo(guideFromStart21P)
                                    end.linkTo(guideFromEnd21P)
                                    top.linkTo(
                                        heartRateIconId.bottom,
                                        dimen.dimen_5.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create measuring text here
                        TextSemiBoldView(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                id = R.string.measuring
                            ),
                            size = dimen.dimen_2,
                            fontColor = theme.black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .constrainAs(measuringTextId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_1.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(
                                        calculateHeartRateId.bottom,
                                        dimen.dimen_4_75.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create message text here
                        TextSemiBoldView(
                            theme = theme,
                            dimen = dimen,
                            text = stringResource(
                                id = R.string.calculate_heart_rate_message
                            ),
                            size = dimen.dimen_1_75,
                            fontColor = theme.black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .constrainAs(messageId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_1.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_1.dp
                                    )
                                    top.linkTo(
                                        measuringTextId.bottom,
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

}//end MeasurementHeartRateContent