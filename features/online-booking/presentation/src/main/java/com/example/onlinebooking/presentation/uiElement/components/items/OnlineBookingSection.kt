package com.example.onlinebooking.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.online.booking.domain.model.OnlineBookingModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.WorkerView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.google.accompanist.placeholder.placeholder

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
    onClickOnVideoCallButton: (Long) -> Unit,
    onlineBooking: OnlineBookingModel,
    placeHolderState: Boolean = false,
    placeHolderColor: Color = theme.grayLight,
    itemIdLoad: Long = 0,
    loadState: Boolean = false,
    modifier: Modifier = Modifier,
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
        val (doctorNameId, messageId, statusId, videoCallId, loadBar) = createRefs()

        //create guides here
        val guideFromStart67P = createGuidelineFromStart(0.65f)
        val guideFromStart60P = createGuidelineFromStart(0.6f)

        //create doctor name here
        WorkerView(
            dimen = dimen,
            theme = theme,
            workerName = onlineBooking.doctorName,
            workerNameSize = doctorNameSize,
            workerNameColor = doctorNameColor,
            work = onlineBooking.specialization,
            workSize = jopSize,
            workColor = jopColor,
            doctorIsOnline = onlineBooking.activeStatus,
            spacingBetweenContent = dimen.dimen_0_5,
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
                .placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )
        )

        //create message here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = message,
            size = messageSize,
            fontColor = messageColor,
            modifier = Modifier
                .constrainAs(messageId) {
                    start.linkTo(doctorNameId.start)
                    end.linkTo(guideFromStart67P)
                    top.linkTo(
                        doctorNameId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
                .placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )
        )

        //create status section here
        BookingStatusSection(
            dimen = dimen,
            theme = theme,
            status = when (onlineBooking.bookingStatus) {
                0 -> {
                    stringResource(R.string.waiting)
                }

                2 -> {
                    stringResource(R.string.complected)
                }

                1 -> {
                    stringResource(R.string.accept)
                }

                else -> {
                    stringResource(R.string.finished)
                }
            },
            statusColor = when (onlineBooking.bookingStatus) {
                0 -> {
                    theme.blue1DA1F2
                }

                2 -> {
                    theme.green33A351
                }

                1 -> {
                    theme.green33A351
                }

                else -> {
                    theme.redDark
                }
            },
            background = when (onlineBooking.bookingStatus) {
                0 -> {
                    theme.blue94C2FF
                }

                2 -> {
                    theme.green8CFFAB
                }

                1 -> {
                    theme.green8CFFAB
                }

                else -> {
                    theme.redFF9696
                }
            },
            modifier = Modifier
                .constrainAs(statusId) {
                    start.linkTo(messageId.start)
                    end.linkTo(guideFromStart60P)
                    top.linkTo(
                        messageId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
                .placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )
        )

        if (onlineBooking.bookingStatus in 1..2) {

            //create video call button here
            IconButtonView(
                dimen = dimen,
                theme = theme,
                onClick = {
                    onClickOnVideoCallButton(onlineBooking.id)
                },
                size = videoCallSize,
                icon = videoCallIcon,
                tint = videoCallTint,
                modifier = Modifier
                    .constrainAs(videoCallId) {
                        start.linkTo(
                            statusId.end,
                            dimen.dimen_1_25.dp
                        )
                        top.linkTo(statusId.top)
                        bottom.linkTo(statusId.bottom)
                    }
                    .placeholder(
                        visible = placeHolderState,
                        color = placeHolderColor
                    )
            )

        }//end if

        if (
            loadState &&
            itemIdLoad == onlineBooking.id
        ) {

            Box(
                modifier = Modifier
                    .constrainAs(loadBar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .background(
                        color = theme.transparent
                    ),//end constrainAs
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    color = theme.grayLight,
                    trackColor = theme.background,
                    strokeWidth = (dimen.dimen_0_25 + dimen.dimen_0_125).dp,
                    modifier = Modifier
                        .size(
                            size = dimen.dimen_3.dp
                        )
                )

            }//end Box

        }//end if

    }//end ConstraintLayout

}//end OnlineBookingSection