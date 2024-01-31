package com.example.reminder.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Switch
//import androidx.compose.material3.SwitchColors
//import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.material4.Switch
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun ReminderSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    time: String,
    timeColor: Color = theme.black,
    timeSize: Float = dimen.dimen_4_5,
    timeType: String,
    timeTypeColor: Color = theme.blackTR70,
    timeTypeSize: Float = dimen.dimen_2_25,
    days: String,
    daysColor: Color = theme.black,
    daysSize: Float = dimen.dimen_1_75,
    background: Color,
    onCheckedChange: (Boolean, Int) -> Unit,
    checked: Boolean,
    switchHeight: Float = dimen.dimen_3,
    switchWidth: Float = dimen.dimen_7_5,
    uncheckedTrackColor: Color = theme.grayDark6B6B6B,
    checkedTrackColor: Color = theme.blue1DA1F2,
    uncheckedThumbColor: Color = theme.background,
    checkedThumbColor: Color = theme.background,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .clip(
                shape = shape
            )
            .background(
                color = background
            )
            .padding(
                vertical = dimen.dimen_3.dp,
                horizontal = dimen.dimen_2.dp
            )
    ) {
        //create ids for components here
        val (timeTextId, timeTypeTextId, daysTextId, switchId) = createRefs()

        //create time text here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = time,
            size = timeSize,
            fontColor = timeColor,
            modifier = Modifier
                .constrainAs(timeTextId) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }//end constrainAs
        )

        //create time type here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = timeType,
            size = timeTypeSize,
            fontColor = timeTypeColor,
            modifier = Modifier
                .constrainAs(timeTypeTextId) {
                    start.linkTo(
                        timeTextId.end,
                        dimen.dimen_0_75.dp
                    )
                    baseline.linkTo(timeTextId.baseline)
                }
        )

        //create days text here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = days,
            size = daysSize,
            fontColor = daysColor,
            modifier = Modifier
                .constrainAs(daysTextId) {
                    start.linkTo(parent.start)
                    top.linkTo(
                        timeTextId.bottom,
                        dimen.dimen_2.dp
                    )
                }//end constrainAs
        )

        //create switch to control on reminder state here
        Switch(
            checked = checked,
            onCheckedChange = { onCheckedChange(!checked, 1) },
            width = switchWidth.dp,
            height = switchHeight.dp,
            uncheckedTrackColor = uncheckedTrackColor,
            checkedTrackColor = checkedTrackColor,
            uncheckedThumbColor = uncheckedThumbColor,
            checkedThumbColor = checkedThumbColor,
            modifier = Modifier
                .constrainAs(switchId) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

    }//end ConstraintLayout

}//end ReminderSection