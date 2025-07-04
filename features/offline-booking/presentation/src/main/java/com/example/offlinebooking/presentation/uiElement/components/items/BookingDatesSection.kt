package com.example.offlinebooking.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.offline.booking.domain.model.DateModel
import com.example.offlinebooking.presentation.uiElement.components.composable.BookingDateView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun BookingDatesSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleColor: Color = theme.black,
    titleSize: Float = dimen.dimen_2,
    horizontalPadding: Float = dimen.dimen_2,
    spacingBetweenComponents: Float = dimen.dimen_2,
    dates: List<DateModel> = emptyList(),
    dateSelected: Long,
    modifier: Modifier = Modifier,
    onClickOnDate: (Long) -> Unit
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (titleId, daysId) = createRefs()

        //create title here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            fontColor = titleColor,
            modifier = Modifier
                .constrainAs(titleId) {
                    start.linkTo(
                        parent.start,
                        horizontalPadding.dp
                    )
                    top.linkTo(parent.top)
                }
        )

        //create LazyRow contain on days
        LazyRow(
            modifier = Modifier
                .constrainAs(daysId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.spacedBy(
                space = spacingBetweenComponents.dp
            ),
            contentPadding = PaddingValues(
                horizontal = horizontalPadding.dp
            )
        ) {

            //create day items
            items(
                count = dates.size
            ) {count ->

                //create day here
                BookingDateView(
                    dimen = dimen,
                    theme = theme,
                    dateModel = dates[count],
                    dateSelected = dateSelected,
                    onClick = onClickOnDate
                )

            }//end items

        }//end LazyColumn

    }//end ConstraintLayout

}//end BookingDateSection