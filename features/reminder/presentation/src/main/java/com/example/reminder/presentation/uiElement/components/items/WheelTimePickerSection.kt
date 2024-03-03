package com.example.reminder.presentation.uiElement.components.items

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.reminder.presentation.uiElement.components.composable.TimerView
import com.example.sharedui.R
import com.example.sharedui.uiElement.animation.animateColorAsState
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.material4.wheel_picker.WheelTimePicker
import com.example.sharedui.uiElement.components.material4.wheel_picker.core.SelectorProperties
import com.example.sharedui.uiElement.components.material4.wheel_picker.core.TimeFormat
import com.example.sharedui.uiElement.components.material4.wheel_picker.core.WheelPickerDefaults
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun WheelTimePickerSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    onSnappedTime: (snappedTime: LocalTime) -> Unit = {},
    timeSelected: LocalTime,
    timePickerTextStyle: TextStyle = TextStyle.Default.copy(
        color = theme.black,
        fontFamily = robotoMedium,
        fontSize = dimen.dimen_3.sp
    ),
    timePickerSelectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(
        color = theme.background,
        border = BorderStroke(
            width = dimen.dimen_0.dp,
            color = theme.background
        )
    ),
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_2_5.dp
    ),
    borderColor: Color = theme.visibleGray,
    borderSize: Float = dimen.dimen_0_125,
    timePickerHeight: Float,
    timePickerWidth: Float,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .appBorder(
                borderWidth = borderSize,
                borderColor = borderColor,
                shape = shape
            )
    ) {
        //create ids for components here
        val (timePickerId) = createRefs()

        //create time picker here
        WheelTimePicker(
            timeFormat = TimeFormat.AM_PM,
            size = DpSize(
                width = timePickerWidth.dp,
                height = timePickerHeight.dp
            ),
            selectorProperties = timePickerSelectorProperties,
            textStyle = timePickerTextStyle,
            onSnappedTime = onSnappedTime,
            startTime = timeSelected,
            modifier = modifier
                .constrainAs(timePickerId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

    }//end ConstraintLayout

}//end WheelTimePickerSection


@Composable
internal fun WheelTimePickerSectionII(
    dimen: CustomDimen,
    theme: CustomTheme,
    onMinutesChanged: (Int) -> Unit,
    onHoursChanged: (Int) -> Unit,
    minutesValue: Int,
    hoursValue: Int,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_2_5.dp
    ),
    borderColor: Color = theme.visibleGray,
    borderSize: Float = dimen.dimen_0_125,
    selectedTimeTypeColor: Color = theme.black,
    unSelectedTimeTypeColor: Color = theme.grayBAB7BC,
    timeTypeSize: Float = dimen.dimen_4,
    modifier: Modifier = Modifier
) {
    //create animated pm color here
    val pmTextColor by animateColorAsState(
        targetColor = if (hoursValue in 0..11) {
            selectedTimeTypeColor
        } else {
            unSelectedTimeTypeColor
        },
        label = "bm text color"
    )

    //create am text color here
    val amTextColor by animateColorAsState(
        targetColor = if (hoursValue in 12..23) {
            selectedTimeTypeColor
        } else {
            unSelectedTimeTypeColor
        },
        label = "am text color"
    )

    //create container here
    ConstraintLayout(
        modifier = modifier
            .appBorder(
                borderWidth = borderSize,
                borderColor = borderColor,
                shape = shape
            )
    ) {
        //create ids for components here
        val (hoursId, minutesId, hoursTypeId) = createRefs()

        //create hours timer here
        TimerView(
            theme = theme,
            dimen = dimen,
            startPoint = 0,
            endPoint = 24,
            value = hoursValue,
            onChanged = onHoursChanged,
            modifier = Modifier
                .constrainAs(hoursId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_4.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        //create hours type here AM PM
        Column(
            modifier = Modifier
                .constrainAs(hoursTypeId) {
                    bottom.linkTo(
                        parent.bottom,
                        dimen.dimen_1_25.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_4.dp
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //create am text here
            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    id = R.string.am
                ),
                size = timeTypeSize,
                color = amTextColor
            )

            //create padding here
            Spacer(
                modifier = Modifier
                    .height(
                        height = dimen.dimen_1_25.dp
                    )
            )

            //create pm text here
            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    id = R.string.pm
                ),
                size = timeTypeSize,
                color = pmTextColor
            )

        }//end Column

        //create minute timer here
        TimerView(
            theme = theme,
            dimen = dimen,
            startPoint = 0,
            endPoint = 60,
            value = minutesValue,
            onChanged = onMinutesChanged,
            modifier = Modifier
                .constrainAs(minutesId) {
                    start.linkTo(hoursId.end)
                    end.linkTo(hoursTypeId.start)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                }
        )

    }//end ConstraintLayout

}//end TimeSection