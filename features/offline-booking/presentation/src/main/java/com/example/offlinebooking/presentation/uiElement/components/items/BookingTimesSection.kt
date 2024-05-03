package com.example.offlinebooking.presentation.uiElement.components.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.offline.booking.domain.model.TimeModel
import com.example.offlinebooking.presentation.uiElement.components.composable.BookingTimeView
import com.example.offlinebooking.presentation.uiState.state.InfiniteGetterStatus
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun BookingTimesSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleColor: Color = theme.black,
    titleSize: Float = dimen.dimen_2,
    horizontalPadding: Float = dimen.dimen_2,
    spacingBetweenComponents: Float = dimen.dimen_2,
    modifier: Modifier = Modifier,
    timeSelectedId: Long,
    onClickOnTime: (Long) -> Unit,
    timesStatus: InfiniteGetterStatus<List<TimeModel>>
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (titleId, daysId, daysPlaceHolderId) = createRefs()

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

        AnimatedVisibility(
            visible = timesStatus.loading,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            modifier = Modifier
                .constrainAs(daysPlaceHolderId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                },
        ) {

            //create LazyRow contain on days
            LazyRow(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = spacingBetweenComponents.dp
                ),
                contentPadding = PaddingValues(
                    horizontal = horizontalPadding.dp
                )
            ) {

                //create day items
                items(
                    count = 10
                ) {

                    //create time here
                    BookingTimeView(
                        dimen = dimen,
                        theme = theme,
                        placeHolderState = true,
                        timeSelectedId = -1,
                        onClick = {},
                        timeModel = TimeModel(
                            id = 0,
                            time = "2:00 pm"
                        )
                    )

                }//end items

            }//end LazyColumn

        }//end AnimatedVisibility


        AnimatedVisibility(
            visible = !timesStatus.loading,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
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
        ) {

            //create LazyRow contain on days
            LazyRow(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = spacingBetweenComponents.dp
                ),
                contentPadding = PaddingValues(
                    horizontal = horizontalPadding.dp
                )
            ) {

                //create day items
                items(
                    count = timesStatus.data?.size ?: 0
                ) { count ->

                    //create time here
                    BookingTimeView(
                        dimen = dimen,
                        theme = theme,
                        timeSelectedId = timeSelectedId,
                        onClick = onClickOnTime,
                        timeModel = timesStatus.data!![count]
                    )

                }//end items

            }//end LazyColumn

        }//end AnimatedVisibility

    }//end ConstraintLayout

}//end BookingTimeSection