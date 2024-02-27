package com.example.reminder.uiElement.components.items

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun DatePickerSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        topStart = dimen.dimen_2_5.dp,
        topEnd = dimen.dimen_2_5.dp
    ),
    borderColor: Color = theme.redDark,
    borderWidth: Float = dimen.dimen_0_125,
    timePickerHeight: Float = dimen.dimen_19_5,
    containerWidth: Int,
    onClickOnSaveButton: () -> Unit,
    onClickOnCancelButton: () -> Unit,
    buttonBackground: Color = theme.transparent,
    textButtonColor: Color = theme.redDark,
    textButtonSize: Float = dimen.dimen_2,
    onSnappedTime: (snappedTime: LocalTime) -> Unit = {},
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .padding(
                top = borderWidth.dp
            )
            .width(
                width = containerWidth.dp
            )
    ) {
        //create ids for components here
        val (timePickerId, daysPickerId, okButtonId, cancelButtonId, borderId) = createRefs()

        //create box to create border
        Box(
            modifier = Modifier
                .constrainAs(borderId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        ) {

            //create border here
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .appBorder(
                        shape = shape,
                        borderWidth = borderWidth,
                        borderColor = borderColor
                    )
                    .height(
                        height = dimen.dimen_2_5.dp
                    )
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = shape
                    )
                    .padding(
                        top = (dimen.dimen_2_25 + dimen.dimen_0_125).dp,
                    )
                    .height(
                        height = borderWidth.dp
                    )
                    .background(
                        color = theme.background
                    )
            )

        }//end Box

        //create time picker here
        WheelTimePickerSection(
            dimen = dimen,
            theme = theme,
            timePickerHeight = timePickerHeight,
            timePickerWidth = containerWidth - dimen.dimen_2,
            onSnappedTime = onSnappedTime,
            modifier = Modifier
                .constrainAs(timePickerId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_3.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create days section here
        DayPickerSection(
            dimen = dimen,
            theme = theme,
            title = stringResource(
                id = R.string.repeat
            ),
            modifier = Modifier
                .constrainAs(daysPickerId) {
                    start.linkTo(parent.start)
                    top.linkTo(
                        timePickerId.bottom,
                        dimen.dimen_2_5.dp
                    )
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

        //create ok button here
        BasicButtonView(
            dimen = dimen,
            theme = theme,
            text = stringResource(
                id = R.string.ok
            ),
            color = buttonBackground,
            fontColor = textButtonColor,
            onClick = onClickOnSaveButton,
            height = dimen.dimen_5,
            fontSize = textButtonSize,
            modifier = Modifier
                .constrainAs(okButtonId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        daysPickerId.bottom,
                        dimen.dimen_3.dp
                    )
                }
                .widthIn(
                    min = dimen.dimen_10_5.dp,
                    max = dimen.dimen_12_5.dp
                )
        )

        //create cancel button here
        BasicButtonView(
            dimen = dimen,
            theme = theme,
            text = stringResource(
                id = R.string.cancel
            ),
            color = buttonBackground,
            fontColor = textButtonColor,
            onClick = onClickOnCancelButton,
            fontSize = textButtonSize,
            height = dimen.dimen_5,
            modifier = Modifier
                .constrainAs(cancelButtonId) {
                    end.linkTo(
                        okButtonId.start,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        daysPickerId.bottom,
                        dimen.dimen_3.dp
                    )
                }
                .widthIn(
                    min = dimen.dimen_10_5.dp,
                    max = dimen.dimen_12_5.dp
                )
        )

    }//end ConstraintLayout

}//end DatePickerSection