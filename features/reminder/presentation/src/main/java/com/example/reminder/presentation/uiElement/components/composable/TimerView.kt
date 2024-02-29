package com.example.reminder.presentation.uiElement.components.composable

import com.example.sharedui.uiElement.animation.animateFloatAsState
import com.example.sharedui.uiElement.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun TimerView(
    theme: CustomTheme,
    dimen: CustomDimen,
    startPoint: Int,
    endPoint: Int,
    value: Int,
    onChanged: (Int) -> Unit,
    width: Float = dimen.dimen_8_75,
    modifier: Modifier = Modifier
) {

    //must end point greater than start point for execute this function
    if (endPoint >= startPoint) {

        //create calculate center state here
        var calculateCenterState by rememberSaveable {
            mutableStateOf(true)
        }

        //create lazy column state here
        val timerState = rememberLazyListState(
            initialFirstVisibleItemIndex = Int.MAX_VALUE / 2 + (value - 4)
        )

        //create container here
        LazyColumn(
            state = timerState,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(
                vertical = dimen.dimen_1_25.dp
            ),
            modifier = modifier
                .width(
                    width = width.dp
                )
        ) {

            items(
                count = Int.MAX_VALUE,
                key = { key -> key }
            ) { key ->

                //create single time here
                TimeView(
                    dimen = dimen,
                    theme = theme,
                    key = key % (endPoint - startPoint),
                    value = value
                )

            }//end items

        }//end LazyColumn


        //this scope for calculate center time visible
        LaunchedEffect(
            key1 = calculateCenterState
        ) {

            //for infinite loop
            calculateCenterState = !calculateCenterState

            //get times visible here
            val timesVisible = timerState.layoutInfo.visibleItemsInfo

            //check times visible is not empty to execute this scope
            if (timesVisible.isNotEmpty()) {

                val size = timesVisible.size

                //get selected time value here
                val selectedTime = timesVisible[
                    //check size is odd or no
                    if (size % 2 != 0) {
                        size / 2
                    }//end if
                    //if size is even get center time by height performance
                    else {
                        size / 2 - 1
                    }//end else
                ].key as Int

                if (selectedTime != value) {

                    //change selected time here
                    onChanged((selectedTime) % (endPoint - startPoint))

                }

            }//end if

        }//end LaunchEffect

    }//end if

}//end Time

@Composable
private fun TimeView(
    dimen: CustomDimen,
    theme: CustomTheme,
    unselectedTextSize: Float = dimen.dimen_4_5,
    unselectedTextColor: Color = theme.grayBAB7BC,
    selectedTextColor: Color = theme.black,
    selectedTextSize: Float = dimen.dimen_7_5,
    width: Float = dimen.dimen_8_75,
    unselectedHeight: Float = dimen.dimen_5_25,
    selectedHeight: Float = dimen.dimen_8_75,
    key: Int,
    value: Int,
    modifier: Modifier = Modifier
) {
    //create animated size here
    val height by animateFloatAsState(
        targetFloat = if (value == key) {
            selectedHeight
        } else {
            unselectedHeight
        },
        label = "animated size"
    )

    //create time size here
    val timeSize by animateFloatAsState(
        targetFloat = if (value == key) {
            selectedTextSize
        } else {
            unselectedTextSize
        },
        label = "animated time size"
    )

    //create animated time color here
    val timeColor by animateColorAsState(
        targetColor = if (value == key) {
            selectedTextColor
        } else {
            unselectedTextColor
        },
        label = "animated time color"
    )

    //create container here
    Box(
        modifier = modifier
            .width(
                width = width.dp
            )
            .height(
                height = height.dp
            ),
        contentAlignment = Alignment.Center
    ) {

        //create time here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = if ("$key".length == 1) "0$key" else "$key",
            size = timeSize,
            fontColor = timeColor,
        )

    }//end Box

}//end TimeView