package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.OnlineView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun WorkerView(
    dimen: CustomDimen,
    theme: CustomTheme,
    workerName: String,
    workerNameSize: Float,
    workerNameColor: Color,
    work: String,
    workSize: Float,
    workColor: Color,
    spacingBetweenContent: Float = dimen.dimen_0_75,
    doctorIsOnline: Boolean = false,
    onlineIconSize: Float = dimen.dimen_1,
    onlineIconShape: Shape = CircleShape,
    onlineIconColor: Color = theme.green75F94C,
    modifier: Modifier = Modifier
) {

    //create container here
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        //create worker name here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = workerName,
            size = workerNameSize,
            fontColor = workerNameColor,
            maxLines = 1
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .width(
                    width = spacingBetweenContent.dp
                )
        )

        //create the work here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "($work)",
            size = workSize,
            fontColor = workColor,
            maxLines = 1
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .width(
                    width = dimen.dimen_0_5.dp
                )
        )

        //if doctor is online create online icon
        if (doctorIsOnline) {

            //create online icon here
            OnlineView(
                dimen = dimen,
                theme = theme,
                iconColor = onlineIconColor,
                iconShape = onlineIconShape,
                iconSize = onlineIconSize
            )

        }//end if

    }//end Row

}//end WorkerNameView