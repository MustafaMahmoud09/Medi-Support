package com.example.booking.uiElement.components.items

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.booking.uiElement.components.composable.WorkerView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun OnlineBookingSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.grayLight,
    doctorNameSize: Float = dimen.dimen_2,
    doctorNameColor: Color = theme.black,
    jopSize: Float = dimen.dimen_1_5,
    jopColor: Color = theme.grayA7A6A5,
    message: String,
    messageSize: Float = dimen.dimen_1_75,
    messageColor: Color = theme.grayDark,
    videoCallIcon: Painter = painterResource(
        id = R.drawable.video_call_icon
    ),
    videoCallSize: Float = dimen.dimen_2_5,
    videoCallTint: Color = theme.greenLight,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .appBorder(
                borderWidth = borderWidth,
                borderColor = borderColor,
                shape = shape
            )
            .padding(
                bottom = dimen.dimen_1_75.dp
            )
    ) {
        //create ids for components here
        val (doctorNameId, messageId, statusId, videoCallId) = createRefs()

        //create guides here
        val guideFromStart67P = createGuidelineFromStart(0.65f)
        val guideFromStart60P = createGuidelineFromStart(0.6f)

        //create doctor name here
        WorkerView(
            dimen = dimen,
            theme = theme,
            workerName = "Dr: Ahmed Mohamed",
            workerNameSize = doctorNameSize,
            workerNameColor = doctorNameColor,
            work = "Dentist",
            workSize = jopSize,
            workColor = jopColor,
            doctorIsOnline = true,
            spacingBetweenContent= dimen.dimen_0_5,
            modifier = Modifier
                .constrainAs(doctorNameId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_5.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_5.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create message here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = message,
            size = messageSize,
            fontColor = messageColor,
            modifier = Modifier
                .constrainAs(messageId){
                    start.linkTo(doctorNameId.start)
                    end.linkTo(guideFromStart67P)
                    top.linkTo(
                        doctorNameId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create status section here
        BookingStatusSection(
            dimen = dimen,
            theme = theme,
            status = "Accept",
            statusColor = theme.green33A351,
            background = theme.green8CFFAB,
            modifier = Modifier
                .constrainAs(statusId){
                    start.linkTo(messageId.start)
                    end.linkTo(guideFromStart60P)
                    top.linkTo(
                        messageId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create video call button here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = { /*TODO*/ },
            size = videoCallSize,
            icon = videoCallIcon,
            tint = videoCallTint,
            modifier = Modifier
                .constrainAs(videoCallId){
                    start.linkTo(
                        statusId.end,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(statusId.top)
                    bottom.linkTo(statusId.bottom)
                }
        )


    }//end ConstraintLayout

}//end OnlineBookingSection