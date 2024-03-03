package com.example.reminder.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.reminder.domaim.domain.model.Day
import com.example.reminder.presentation.uiElement.components.composable.DayView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun DayPickerSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnDay: (Long) -> Unit,
    title: String,
    titleColor: Color = theme.black,
    titleSize: Float = dimen.dimen_2,
    weekDays: List<Day>,
    daysSelected: List<Long>,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (daysTitleId, daysId) = createRefs()

        //create title here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            fontColor = titleColor,
            modifier = Modifier
                .constrainAs(daysTitleId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(parent.top)
                }
        )

        LazyRow(
            modifier = Modifier
                .constrainAs(daysId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        daysTitleId.bottom,
                        dimen.dimen_1_25.dp
                    )
                    width = Dimension.fillToConstraints
                },
            contentPadding = PaddingValues(
                horizontal = dimen.dimen_2.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_0_75.dp
            )
        ) {

            items(
                count = weekDays.size
            ) {

                DayView(
                    dimen = dimen,
                    theme = theme,
                    day = weekDays[it],
                    onClickOnDay = onClickOnDay,
                    daysSelected = daysSelected
                )

            }//end items

        }//end LazyRow

    }//end ConstraintLayout

}//end DaysSection