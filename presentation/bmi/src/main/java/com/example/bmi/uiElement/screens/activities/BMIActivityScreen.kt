package com.example.bmi.uiElement.screens.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.items.SomeHistorySection
import com.example.sharedui.uiElement.components.composable.ColumnChartView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.components.items.RecommendedSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.core.entry.entryModelOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BMIActivityScreen(
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToHistoryDestination: () -> Unit
) {

    BMIActivityContent(
        theme = theme,
        dimen = dimen,
        onClickSeeAll = navigateToHistoryDestination
    )
}//end BMIScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun BMIActivityContent(
    theme: CustomTheme,
    dimen: CustomDimen,
    onClickSeeAll: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            ),
        contentPadding = PaddingValues(
            vertical = dimen.dimen_2.dp
        )
    ) {

        //create container item contain on all screen components
        item(
            key = 1
        ) {

            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //create ids for screen components here
                val (daysId, chartId, lineId, someHistoryId, recommendedId) = createRefs()

                //create row contain on days here
                LazyRow(
                    contentPadding = PaddingValues(
                        horizontal = dimen.dimen_1_5.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = dimen.dimen_1_5.dp,
                    ),
                    modifier = Modifier
                        .constrainAs(daysId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
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

                //create bmi chart here
                ColumnChartView(
                    theme = theme,
                    dimen = dimen,
                    data = entryModelOf(50f, 60f, 80f, 90f, 100f, 330f, 320f),
                    maxValue = 330f,
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
                                daysId.bottom,
                                dimen.dimen_2.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                        .aspectRatio(1.42f)
                )

                //create separate line here
                LineView(
                    dimen = dimen,
                    theme = theme,
                    color = theme.redDarkTR77,
                    modifier = Modifier
                        .constrainAs(lineId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                chartId.bottom,
                                dimen.dimen_4.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                //create some history section here
                SomeHistorySection(
                    dimen = dimen,
                    theme = theme,
                    onClickSeeAll = onClickSeeAll,
                    modifier = Modifier
                        .constrainAs(someHistoryId) {
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
                                dimen.dimen_1_5.dp
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
                                someHistoryId.bottom,
                                dimen.dimen_1_75.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

            }//end ConstraintLayout

        }//end item

    }//end LazyColumn

}//end BMIContent