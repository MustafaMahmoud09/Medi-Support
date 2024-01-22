package com.example.activity.uiElement.screens.activity.child

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.activity.uiElement.components.items.SomeHistorySection
import com.example.sharedui.R
import com.example.activity.uiElement.components.composable.LineChartView
import com.example.activity.uiElement.components.items.SingleLineChartSection
import com.example.sharedui.uiElement.components.composable.TextNormalStartView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.patrykandpatrick.vico.core.entry.entryModelOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HeartRateScreen(
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToHistoryDestination: () -> Unit
) {

    HeartRateContent(
        theme = theme,
        dimen = dimen,
        onClickSeeAll = navigateToHistoryDestination
    )
}//end HeartRateScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HeartRateContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickSeeAll: () -> Unit
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
            .padding(
                top = dimen.dimen_1.dp
            ),
        contentPadding = PaddingValues(
            vertical = dimen.dimen_2.dp
        )
    ) {


        item(
            key = 1
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = dimen.dimen_1_5.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = dimen.dimen_1_5.dp
                    )
                ) {

                    items(
                        count = 10
                    ) {

                        DaySection(
                            dimen = dimen,
                            dayName = "Wed",
                            dayNumber = "17",
                            theme = theme
                        )

                    }//end items

                }//end LazyRow

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimen.dimen_2.dp
                        )
                        .padding(
                            top = dimen.dimen_5_5.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    SingleLineChartSection(
                        theme = theme,
                        dimen = dimen,
                        data = entryModelOf(60f, 60f, 90f, 120f, 100f, 50f, 100f),
                        maxValue = 330f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.42f)
                    )

                }//end Box

            }//end Column

        }//end item

        item(
            key = 2
        ) {

            Box(
                modifier = Modifier
                    .padding(
                        top = dimen.dimen_1_25.dp
                    )
                    .padding(
                        horizontal = dimen.dimen_2.dp
                    )
            ) {

                SomeHistorySection(
                    dimen = dimen,
                    theme = theme,
                    onClickSeeAll = onClickSeeAll,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end Box

        }//end item

        item(
            key = 3
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .padding(
                            top = dimen.dimen_1_75.dp,
                            start = dimen.dimen_2.dp,
                            end = dimen.dimen_2.dp
                        )
                ) {

                    TextSemiBoldView(
                        theme = theme,
                        dimen = dimen,
                        text = stringResource(
                            R.string.recommended_reading
                        ),
                        size = dimen.dimen_2,
                        fontColor = theme.black
                    )

                }//end Box

                Box(
                    modifier = Modifier
                        .padding(
                            top = dimen.dimen_2_25.dp,
                            start = dimen.dimen_2.dp,
                            end = dimen.dimen_2.dp
                        )
                ) {

                    TextNormalView(
                        theme = theme,
                        dimen = dimen,
                        text = "How to loss Sugar?",
                        size = dimen.dimen_2,
                        weigh = 300,
                        fontColor = theme.black
                    )

                }//end Box

                Box(
                    modifier = Modifier
                        .padding(
                            top = dimen.dimen_2_25.dp,
                            start = dimen.dimen_2.dp,
                            end = dimen.dimen_2.dp
                        )
                ) {

                    TextNormalStartView(
                        theme = theme,
                        dimen = dimen,
                        text = "printing and typesetting industry.  Lorem Ipsum has been the industry's Lorem Ipsum is simply dummy text of the printing and typesetting industry.  Lorem Ipsum has been the industry's Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        size = dimen.dimen_2,
                        fontColor = theme.black
                    )

                }//end Box

            }//end Column

        }//end item

    }//end LazyColumn


}//end HeartRateScreen